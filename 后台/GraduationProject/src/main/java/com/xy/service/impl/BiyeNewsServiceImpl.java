package com.xy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.api.gateway.demo.util.HttpUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.common.utils.JacksonUtils;
import com.xy.mapper.BiyeNewsMapper;
import com.xy.pojo.ApiResult;
import com.xy.pojo.BiyeNews;
import com.xy.pojo.BiyeNewsImage;
import com.xy.service.BiyeNewsService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: xyh
 * @Date: 2019/5/15 12:04
 * @Description:
 */
@Service
public class BiyeNewsServiceImpl implements BiyeNewsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BiyeNewsMapper biyeNewsMapper;

    //数据库已存在的个数
    int exist = 0;
    //已插入数据库条数
    int rm1 = 0;

    @Override
    //给前台提供api
    public List<String> redisapi(String channelId, Integer page){
        //返回页数计算
        int start = 0, end = 9;
        start += (page - 1) * 10;
        end = page * 10 - 1;
        List<String> redisapi = new ArrayList<>();
        List<String> api = redisTemplate.opsForList().range(channelId, start, end);
        List<String> api2 = redisTemplate.opsForList().range(channelId, page * 10, page * 10 + 9);
        int a2 = 0;
        try {
            for (String redisid : api) {
                if (getredis(redisid) != null) {
                    redisapi.add(getredis(redisid));
                } else {
                    a2++;
                }
            }
            for (int a3 = 0; a3 < a2; a3++) {
                if (getredis(api2.get(a3))!=null){
                    redisapi.add(getredis(api2.get(a3)));
                }else {
                    a2++;
                }
//            redisapi.add(getredis(api2.get(a3)));
            }
            return redisapi;

        }catch (Exception e){
            return null;
        }

    }

    //用户插入新闻
    @Override
    public void useraddnews(List<BiyeNewsImage> biyeNewsImage, BiyeNews biyeNews) {
        //插入新闻表内容
        String newsId = useradd(biyeNews);
        //插入新闻图片
        List<String> imagePathlist = addimg(biyeNewsImage, newsId);
        //获得插入的图片路径
        List<String> imagepathlist = biyeNewsMapper.selectimagePathByNewsid(newsId);
        //拼接json对象
        ApiResult jsonobj = jsonobj(biyeNewsMapper.selectonenews(newsId), imagepathlist);
        System.out.println(jsonobj);
        //插入redis
        userinsertredis(jsonobj);

    }

    //用户新闻插入redis
    public void userinsertredis(ApiResult apiResult) {
        redisTemplate.opsForValue().set(apiResult.getId(), apiResult.getScoreClass());
        redisTemplate.opsForList().leftPush(apiResult.getChannelId(), apiResult.getId());

    }

    //插入redis和mysql
    @Override
    public void insert(List<ApiResult> apiResults) {
        for (ApiResult apiResult : apiResults) {
            if (redisTemplate.hasKey(apiResult.getId())) {
                exist += 1;
            } else {
                redisTemplate.opsForValue().set(apiResult.getId(), apiResult.getScoreClass());
                redisTemplate.opsForList().leftPush(apiResult.getChannelId(), apiResult.getId());
                BiyeNews biyeNews = new BiyeNews();
                biyeNews.setNewsId(apiResult.getId());
                biyeNews.setNewsTitle(apiResult.getTitle());
                biyeNewsMapper.insert(biyeNews);
                rm1 += 1;
            }
        }
    }


    //模糊查询新闻
    @Override
    public List<String> select(String newsTitle, Integer page) {
        //返回页数计算
        int start = 0, end = 9;
        start += (page - 1) * 10;
        end = page * 10 - 1;
        List<String> news = new ArrayList<>();
        List<String> selectnews = biyeNewsMapper.selectnews(newsTitle);
        try {
            if (selectnews.size() > 10) {
                for (int p = start; p < end + 1; p++) {
                    String redisnews = selectnews.get(p);
                    //为空新闻删除，且end+1补全十条
                    if (getredis(redisnews) == null) {
                        end += 1;
                    }
                    if (getredis(redisnews) != null) {
                        news.add(getredis(redisnews));
                    }
                }
            } else {
                for (String selectnew : selectnews) {
                    if (getredis(selectnew) != null) {
                        news.add(getredis(selectnew));
                    }
                }
            }
        } catch (Exception e) {
            return news;
        }

        return news;
    }

    @Override
    //api添加新闻到redis、mysql
    public int addnews(String channelId) {
        //调用提供api方法
        List<ApiResult> apiResult = api(channelId);
        //插入redis和mysql
        insert(apiResult);
        //返回数据库已存在的条数
        return exist;
    }

    //用户插入新闻表
    @Override
    public String useradd(BiyeNews biyeNews) {
        biyeNewsMapper.userinsert(biyeNews);
        return (biyeNewsMapper.selectnews(biyeNews.getNewsTitle())).get(0);
    }

    //用户插入新闻图片
    @Override
    public List<String> addimg(List<BiyeNewsImage> biyeNewsImage, String newsId) {
        List<String> imagePathlist = new ArrayList<>();
        for (BiyeNewsImage newsImage : biyeNewsImage) {
            BiyeNewsImage biyeNewsImage1 = new BiyeNewsImage(newsImage.getImagePath(), newsId);
            biyeNewsMapper.addimg(biyeNewsImage1);
        }
        List<String> imageIdlist = biyeNewsMapper.selectimageId(biyeNewsImage.get(0).getNewsId());
        for (String s : imageIdlist) {
            String s1 = biyeNewsMapper.selectimagePath(s);
            imagePathlist.add(s1);
        }
        return imagePathlist;
    }


    @Override
    public List<String> selectimageId(String newsId) {
        List<String> selectimageId = biyeNewsMapper.selectimageId(newsId);
        System.out.println("imageId" + selectimageId);
        return selectimageId;
    }

    //拼接新闻json
    @Override
    public ApiResult jsonobj(BiyeNews biyeNews, List<String> imagepathlist) {
        System.out.println("路径" + imagepathlist);
        List<String> biyeNewsImage = new ArrayList<>();
        for (String s : imagepathlist) {
            String a = "{\"url\":\"" + s + "\"}";
            biyeNewsImage.add(a);
        }
        List<String> biyeNewsImage2 = biyeNewsImage;
        if (biyeNewsImage.size()>1){
            biyeNewsImage.remove(0);
        }
        ApiResult apiResult = new ApiResult();
        apiResult.setId(biyeNews.getNewsId());
        apiResult.setChannelId(biyeNews.getNewsTypeId());
        apiResult.setTitle(biyeNews.getNewsTitle());
        String lastjson =
                ("{") + ("\"allList\":["+(biyeNewsImage2.get(0) + ",\"") + biyeNews.getNewsContent() + "\"],") + ("\"title\":\"" + biyeNews.getNewsTitle() + "\",")
                        + ("\"id\":\"" + biyeNews.getNewsId() + "\",") + ("\"pubDate\":\"" + biyeNews.getNewsDate() + "\",") +
                        ("\"imageurls\":" + biyeNewsImage + ",") + ("\"channelName\":\"" + "null" + "\"")
                        + ("}");
        apiResult.setScoreClass(lastjson);
        System.out.println("lastjson" + lastjson);
        return apiResult;
    }

    /**
     * @return com.xy.common.uti ls.EasyUIDataGridResult
     * @Author GuoZhengYing
     * @Description //TODO 后台查看新闻
     * @Date 11:51 2019/5/29
     * @Param [page, rows, newsTitle]
     **/
    @Override
    public EasyUIDataGridResult seeTopews(int page, int rows, String newsTitle) {
        //设置分页信息
        PageHelper.startPage(page, rows);

        //执行查询
        if (StringUtils.isNotBlank(newsTitle)) {
            List<BiyeNews> list = biyeNewsMapper.searchBox(newsTitle);

            //取分页信息
            PageInfo<BiyeNews> pageInfo = new PageInfo<>(list);

            //创建返回结果对象
            EasyUIDataGridResult result = new EasyUIDataGridResult();
            result.setTotal(pageInfo.getTotal());
            result.setRows(list);
            return result;

        }

        List<BiyeNews> list = biyeNewsMapper.seeTopNews();

        //取分页信息
        PageInfo<BiyeNews> pageInfo = new PageInfo<>(list);

        //创建返回结果对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);

        return result;
    }

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 新闻置顶
     * @Date 19:58 2019/5/28
     * @Param [newsId]
     **/
    @Override
    public E3Result topNews(String newsId) {
        biyeNewsMapper.stickNews(newsId);
        return E3Result.ok("置顶成功！");
    }

    /**
     * @return com.xy.common.utils.EasyUIDataGridResult
     * @Author GuoZhengYing
     * @Description //TODO 查询所有置顶新闻
     * @Date 17:27 2019/5/31
     * @Param [page, rows, start]
     **/
    @Override
    public EasyUIDataGridResult checkTopNews(int page, int rows, int start) {
        PageHelper.startPage(page, rows);

        if (start == 0) {
            //前台
            List<String> resultList = new ArrayList<String>();

            List<String> list = biyeNewsMapper.checkTopNews();
            for (int i = 0; i < list.size(); i++) {
                String data = (String) redisTemplate.opsForValue().get(list.get(i));
                resultList.add(data);
            }

            List<String> redisapi = redisapi("5572a108b3cdc86cf39001cd", page);
            for (String s : redisapi) {
                resultList.add(s);
            }

            //取分页信息
            PageInfo<String> pageInfo = new PageInfo<>(resultList);

            //创建返回结果对象
            EasyUIDataGridResult result = new EasyUIDataGridResult();
            result.setTotal(resultList.size());
            result.setRows(resultList);
            return result;

        } else if (start == 1) {
            //后台
            List<BiyeNews> list = biyeNewsMapper.lookTopNews();

            //取分页信息
            PageInfo<BiyeNews> pageInfo = new PageInfo<>(list);
            //创建返回结果对象
            EasyUIDataGridResult result = new EasyUIDataGridResult();
            result.setTotal(pageInfo.getTotal());
            result.setRows(list);

            return result;
        }

        return null;
    }

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 新闻下架
     * @Date 11:25 2019/5/30
     * @Param [newsId]
     **/
    @Override
    public E3Result shallDown(String newsId) {
        redisTemplate.delete(newsId);
        biyeNewsMapper.soldOut(newsId);
        return E3Result.ok("下架成功！");
    }

    @Override
    public E3Result abrogateNews(String newsId) {
        biyeNewsMapper.cancelNew(newsId);
        return E3Result.ok("取消置顶成功！");
    }

    //调用提供api方法
    @Override
    public List<ApiResult> api(String channelId) {
        String host = "http://ali-news.showapi.com";
        String path = "/newsList";
        String method = "GET";
        String appcode = "6105ab4210ff4df38ec7924acdcf06cd";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        //新闻类别id
        querys.put("channelId", channelId);
        querys.put("channelName", null);
        querys.put("maxResult", "20");
        querys.put("needAllList", "1");
        querys.put("needContent", "0");
        querys.put("needHtml", "0");
        querys.put("page", "20");
        querys.put("title", null);
        List<ApiResult> apiResults = new ArrayList<>();
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            String data = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = JSON.parseObject(data);
            //json内容
            String scoreClass = JacksonUtils.getInstance().fetchValue(data, "showapi_res_body:pagebean:contentlist");
            JSONArray json = JSONArray.parseArray(scoreClass);
            if (json.size() > 0) {
                for (int i = 0; i < json.size(); i++) {
                    // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    JSONObject job = json.getJSONObject(i);
                    //判断内容是否为空，为空则不插入
                    String allList = "" + job.get("allList");
                    String id = "" + job.get("id");
                    String title = "" + job.get("title");
                    String imageurls = "" + job.get("imageurls");
                    int count = 0;
                    Matcher m = Pattern.compile("url").matcher(imageurls);
                    while (m.find()) {
                        count++;
                    }
                    if (allList.length() > 10) {
                        if (title.contains("\"") == false && count > 0) {
                            String lastjson =
                                    ("{") + ("\"allList\":" + job.get("allList") + ",") + ("\"title\":\"" + job.get(
                                            "title") + "\",")
                                            + ("\"id\":\"" + job.get("id") + "\",") + ("\"pubDate\":\"" + job.get(
                                            "pubDate") + "\",") +
                                            ("\"imageurls\":" + job.get("imageurls") + ",") + ("\"channelName\":\"" + job.get("channelName") + "\"") + ("}");
                            ApiResult apiResult = new ApiResult();
                            apiResult.setId((String) job.get("id"));
                            apiResult.setChannelId((String) job.get("channelId"));
                            apiResult.setChannelName((String) job.get("channelName"));
                            apiResult.setTitle((String) job.get("title"));
                            apiResult.setScoreClass(lastjson);
                            apiResults.add(apiResult);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiResults;
    }


    @Override
    public String getredis(String redisid) {
        String apinews = (String) redisTemplate.opsForValue().get(redisid);
        return apinews;
    }


    //获得已插入条数
    @Override
    public int rm() {
        return rm1;
    }


}
