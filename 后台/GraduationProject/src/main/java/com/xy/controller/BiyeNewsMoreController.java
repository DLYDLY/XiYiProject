package com.xy.controller;

import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.service.BiyeNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GuoZhengYing
 * @ClassName BiyeNewsMoreController
 * @Description TDDO
 * @Date 2019/5/28 18:41
 * @Version 1.0
 */
@RestController
@Api("后台新闻管理接口")
@RequestMapping("/backstage")
public class BiyeNewsMoreController {

    @Autowired
    private BiyeNewsService biyeNewsService;


    @ApiOperation("后台显示新闻")
    @RequestMapping(value = "/seeNews", method = RequestMethod.POST)
    public EasyUIDataGridResult seeNews(@ApiParam(value = "当前页数", required = true) @RequestParam("page") int page,
                                        @ApiParam(value = "要显示条数", required = true) @RequestParam("rows") int rows,
                                        @ApiParam("搜索框内容") @RequestParam(value = "newsTitle", required = false) String newsTitle) {
        EasyUIDataGridResult result = biyeNewsService.seeTopews(page, rows, newsTitle);
        return result;
    }


    @ApiOperation("新闻置顶操作")
    @RequestMapping(value = "/newsStick/{newsId}", method = RequestMethod.GET)
    public E3Result moreTopNews(@ApiParam(value = "新闻ID", required = true) @PathVariable String newsId) {
        E3Result result = biyeNewsService.topNews(newsId);
        return result;
    }


    @ApiOperation("分页查看所有置顶新闻：start 0小程序 ， 1是后台")
    @RequestMapping(value = "/TopNews/{page}/{rows}/{start}", method = RequestMethod.GET)
    public EasyUIDataGridResult seeTopNews(@ApiParam(value = "当前页数", required = true) @PathVariable int page,
                                           @ApiParam(value = "要显示条数", required = true) @PathVariable int rows,
                                           @ApiParam(value = "小程序与后台的识别码", required = true) @PathVariable int start) {
        EasyUIDataGridResult result = biyeNewsService.checkTopNews(page, rows, start);
        return result;
    }


    @ApiOperation("下架新闻")
    @RequestMapping(value = "/shallDown/{newsId}", method = RequestMethod.GET)
    public E3Result xiaJiaNews(@ApiParam(value = "新闻ID", required = true) @PathVariable String newsId) {
        E3Result result = biyeNewsService.shallDown(newsId);
        return result;
    }


    @ApiOperation("取消置顶新闻")
    @RequestMapping(value = "/abrogate/{newsId}", method = RequestMethod.GET)
    public E3Result quxiaNews(@ApiParam(value = "新闻ID", required = true) @PathVariable String newsId) {
        E3Result result = biyeNewsService.abrogateNews(newsId);
        return result;
    }
}
