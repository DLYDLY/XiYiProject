package com.xy.service;

import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.pojo.ApiResult;
import com.xy.pojo.BiyeNews;
import com.xy.pojo.BiyeNewsImage;

import java.util.List;

/**
 * @Auther: xyh
 * @Date: 2019/5/15 10:47
 * @Description:
 */
public interface BiyeNewsService {


    //添加新闻
    int addnews(String channelId);

    //提供api
    List<ApiResult> api(String channelId);

    //插入redis和mysql
    void insert(List<ApiResult> apiResults);

    //给前台提供api
    List<String> redisapi(String channelId, Integer page);

    //redis数据获取
    String getredis(String redisid);

    //获得已插入条数
    int rm();

    //搜索新闻
    List<String> select(String newsTitle, Integer page);

    //用户添加新闻内容
    String useradd(BiyeNews biyeNews);

    //插入图片
    List<String> addimg(List<BiyeNewsImage> biyeNewsImage, String newsId);

    //插入新闻到redis和mysql
    void useraddnews(List<BiyeNewsImage> biyeNewsImage, BiyeNews biyeNews);

    //查询新闻图片id
    List<String> selectimageId(String newsId);

    //拼接json对象
    ApiResult jsonobj(BiyeNews biyeNews, List<String> imagepathlist);

    /**
     * @return java.util.List<com.xy.pojo.BiyeNews>
     * @Author GuoZhengYing
     * @Description //TODO 查询显示新闻
     * @Date 11:18 2019/5/29
     * @Param [newsName]
     **/
    EasyUIDataGridResult seeTopews(int page, int rows, String newsTitle);

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 新闻置顶
     * @Date 19:57 2019/5/28
     * @Param [newsId]
     **/
    E3Result topNews(String newsId);

    /**
     * @Author GuoZhengYing 
     * @Description //TODO 查询所有置顶新闻
     * @Date 9:59 2019/5/30
     * @Param []
     * @return java.util.List<java.lang.String>
     **/
    EasyUIDataGridResult checkTopNews(int page, int rows,int start);



    /**
     * @Author GuoZhengYing
     * @Description //TODO 新闻下架
     * @Date 11:25 2019/5/30
     * @Param [newsId]
     * @return com.xy.common.utils.E3Result
     **/
    E3Result shallDown(String newsId);


    /**
     * @Author GuoZhengYing 
     * @Description //TODO 取消置顶
     * @Date 16:59 2019/5/31
     * @Param [newsId]
     * @return com.xy.common.utils.E3Result
     **/
    E3Result abrogateNews(String newsId);
}
