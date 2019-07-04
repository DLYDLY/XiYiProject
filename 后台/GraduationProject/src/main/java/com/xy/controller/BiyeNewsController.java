package com.xy.controller;

import com.xy.common.utils.E3Result;
import com.xy.pojo.BiyeNews;
import com.xy.pojo.BiyeNewsImage;
import com.xy.service.BiyeNewsService;
import io.swagger.annotations.*;
import org.omg.CORBA.StringHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Auther: xyh
 * @Date: 2019/5/15 19:37
 * @Description:
 */
@RestController
@RequestMapping("BiyeNews")
@Api("根据新闻类别id添加新闻及获取api")
public class BiyeNewsController {

    @Autowired
    private BiyeNewsService biyeNewsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping(value = "/api/{channelId}/{page}")
    @ApiOperation(value = "根据新闻类别id及页数请求获取api", notes = "根据新闻类别id及页数请求获取新闻信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channelId", value = "新闻类别id", paramType = "path", required = true, dataType =
                    "String"),
            @ApiImplicitParam(name = "page", value = "请求页数", paramType = "path", required = true, dataType = "Int")
    })
    public List<String> redisapi(@ApiParam("page") @PathVariable Integer page, @PathVariable @Valid String channelId) {
        List<String> redisapi = biyeNewsService.redisapi(channelId, page);
        return redisapi;
    }

    @PostMapping(value = "/selectnews/{newsTitle}/{page}")
    @ApiOperation(value = "根据新闻标题模糊查询新闻(搜索框查询新闻)", notes = "根据新闻标题模糊查询新闻")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newsTitle", value = "新闻标题", paramType = "path", required = true, dataType =
                    "String"),
            @ApiImplicitParam(name = "page", value = "请求页数", paramType = "path", required = true, dataType = "Int")
    })
    public List<String> selectnews(@ApiParam("page") @PathVariable Integer page, @PathVariable @Valid String newsTitle) {
        List<String> select = biyeNewsService.select(newsTitle, page);
        return select;
    }

    @PostMapping(value = "/selectnews/{redisid}")
    @ApiOperation(value = "根据新闻id查询新闻", notes = "根据新闻id查询新闻")
    @ApiImplicitParam(name = "redisid", value = "新闻id", paramType = "path", required = true, dataType = "String")
    public String selectnewsbyid(@PathVariable @Valid String redisid) {
        String getredis = biyeNewsService.getredis(redisid);
        return getredis;
    }

    @PostMapping(value = "/userinsert")
    @ApiOperation(value = "用户自定义插入新闻", notes = "用户插入新闻")
    @ApiImplicitParam(name = "biyeNews", value = "新闻内容", paramType = "BiyeNews", required = true, dataType = "BiyeNews")
    public E3Result userinsert(@RequestBody BiyeNews biyeNews) {
        List<BiyeNewsImage> biyeNewsImages = biyeNews.getBiyeNewsImages();
        biyeNewsService.useraddnews(biyeNewsImages, biyeNews);
        return E3Result.ok("插入新闻成功！");
    }


}
