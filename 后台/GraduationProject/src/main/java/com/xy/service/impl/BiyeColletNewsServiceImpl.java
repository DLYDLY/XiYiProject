package com.xy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.mapper.BiyeColletNewsMapper;
import com.xy.pojo.BiyeCollectNews;
import com.xy.pojo.NumPojo;
import com.xy.service.BiyeColletNewsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GuoZhengYing
 * @ClassName BiyeColletNewsServiceImpl
 * @Description TDDO
 * @Date 2019/6/3 14:41
 * @Version 1.0
 */
@Service
public class BiyeColletNewsServiceImpl implements BiyeColletNewsService {

    @Autowired
    private BiyeColletNewsMapper biyeColletNewsMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public E3Result addColletNews(BiyeCollectNews biyeCollectNews) {
        biyeColletNewsMapper.userAddColletNews(biyeCollectNews);
        return E3Result.ok("收藏成功！");
    }

    @Override
    public E3Result deleteColletNews(BiyeCollectNews biyeCollectNews) {
        if (StringUtils.isNotBlank(biyeCollectNews.getUserId()) & StringUtils.isNotBlank(biyeCollectNews.getUserId())) {
            Long collectId = biyeColletNewsMapper.selectByNews(biyeCollectNews);
            biyeColletNewsMapper.deleteCollectNews(collectId);
            return E3Result.ok("收藏删除");
        }

        long id = biyeCollectNews.getCollectId();
        if (id != 0) {
            biyeColletNewsMapper.deleteCollectNews(id);
            return E3Result.ok("收藏删除！");
        }

        return E3Result.build(500, "收藏失败！");
    }

    @Override
    public E3Result selectOne(String newID, String userId) {
        BiyeCollectNews biyeCollectNews = biyeColletNewsMapper.selectOne(newID, userId);
        if (biyeCollectNews == null) {
            return E3Result.build(400, "该用户没有收藏新闻");
        }
        return E3Result.ok("该用户已收藏该新闻");
    }


    @Override
    public EasyUIDataGridResult selectNewsByUser(int page, int rows, String userId) {
        PageHelper.startPage(page, rows);

        List<String> result = new ArrayList<>();
        List<String> list = biyeColletNewsMapper.selectCollectNews(userId);
        for (int i = 0; i < list.size(); i++) {
            String date = (String) redisTemplate.opsForValue().get(list.get(i));
            result.add(date);
        }

        PageInfo<String> info = new PageInfo<>(list);

        EasyUIDataGridResult gridResult = new EasyUIDataGridResult();
        gridResult.setTotal(info.getTotal());
        gridResult.setRows(result);

        return gridResult;
    }

    @Override
    public NumPojo selectNum() {
        NumPojo numPojo = new NumPojo();
        numPojo.setNewsNum(biyeColletNewsMapper.selectNewsNum());
        numPojo.setUserNum(biyeColletNewsMapper.selectUserNum());
        return numPojo;
    }


}
