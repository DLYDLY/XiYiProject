package com.xy.controller;

import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.pojo.BiyeCollectNews;
import com.xy.pojo.NumPojo;
import com.xy.service.BiyeColletNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author GuoZhengYing
 * @ClassName ColletNewsController
 * @Description TDDO
 * @Date 2019/6/3 15:42
 * @Version 1.0
 */
@Api("收藏接口")
@RestController
@RequestMapping("/collectNews")
public class ColletNewsController {

    @Autowired
    private BiyeColletNewsService biyeColletNewsService;


    @ApiOperation(value = "收藏", notes = "用户添加收藏")
    @RequestMapping(value = "/AddcolltNEws", method = RequestMethod.POST)
    public E3Result AddColletNews(@RequestBody BiyeCollectNews biyeCollectNews) {
        E3Result result = biyeColletNewsService.addColletNews(biyeCollectNews);
        return result;
    }


    @ApiOperation(value = "取消收藏")
    @RequestMapping(value = "/deleteNEws", method = RequestMethod.POST)
    public E3Result delNews(@RequestBody BiyeCollectNews biyeCollectNews) {
        E3Result result = biyeColletNewsService.deleteColletNews(biyeCollectNews);
        return result;
    }

    @ApiOperation("查询用户所收藏的新闻")
    @RequestMapping(value = "/selectNews/{page}/{rows}/{userId}", method = RequestMethod.GET)
    public EasyUIDataGridResult checkAll(@ApiParam(value = "当前页数", required = true) @PathVariable int page,
                                         @ApiParam(value = "要显示条数", required = true) @PathVariable int rows,
                                         @ApiParam(value = "用户ID", required = true) @PathVariable String userId) {
        EasyUIDataGridResult gridResult = biyeColletNewsService.selectNewsByUser(page, rows, userId);
        return gridResult;
    }


    @GetMapping("/selectNum")
    @ApiOperation(value = "新闻条数，用户条数", notes = "新闻条数，用户条数")
    public NumPojo selectNum() {
        NumPojo numPojo = biyeColletNewsService.selectNum();
        return numPojo;
    }

    @ApiOperation("查询用户是否收藏新闻")
    @RequestMapping(value = "/selectOne/{newID}/{userId}", method = RequestMethod.GET)
    public E3Result show(@ApiParam(value = "新闻ID", required = true) @PathVariable String newID,
                         @ApiParam(value = "用户Id", required = true) @PathVariable String userId) {
        E3Result result = biyeColletNewsService.selectOne(newID, userId);
        return result;
    }
}
