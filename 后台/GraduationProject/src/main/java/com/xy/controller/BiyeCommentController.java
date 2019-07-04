package com.xy.controller;

import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.pojo.*;
import com.xy.service.BiyeCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * @Auther: xyh
 * @Date: 2019/5/14 19:41
 * @Description:
 */

@RestController
@RequestMapping("BiyeComment")
@Api("新闻评论查询与删除")
public class BiyeCommentController {


    @Autowired
    private BiyeCommentService biyeCommentService;


    /**
     * 添加评论信息
     *
     * @return
     */
    @PostMapping(value = "/addComment")
    @ApiOperation(value = "添加评论", notes = "添加评论")
    public E3Result addComment(@RequestBody UniquePojo uniquePojo) {
        E3Result result = biyeCommentService.addComment(uniquePojo);
        return result;
    }


    /**
     * 添加子评论
     *
     * @return
     */
    @PostMapping(value = "/addSComment")
    @ApiOperation(value = "添加子评论", notes = "添加子评论")
    public E3Result addSComment(@RequestBody SCommentPojo sCommentPojo) {
        E3Result e3Result = biyeCommentService.addSComment(sCommentPojo);
        return e3Result;
    }

    //获取全部评论
    @RequestMapping(value = "/selectall/{page}/{rows}", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部评论", notes = "查询全部评论")
    public EasyUIDataGridResult selectall(@ApiParam(value = "当前页", required = true) @PathVariable int page,
                                          @ApiParam(value = "显示条数", required = true) @PathVariable int rows) {
        EasyUIDataGridResult selectall = biyeCommentService.selectall(page, rows);
        return selectall;
    }

    //根据id删除评论
    @PostMapping(value = "/delete/{commentId}")
    @ApiOperation(value = "根据id删除评论", notes = "根据id删除评论")
    @ApiImplicitParam(name = "commentId", value = "评论id", paramType = "path", required = true, dataType = "string")
    public E3Result deletebyid(@PathVariable @Valid String commentId) {
        biyeCommentService.deletebyid(commentId);
        return E3Result.ok();
    }

    //根据新闻id查询评论
    @PostMapping(value = "/selectall/{newsId}/{page}/{rows}")
    @ApiOperation(value = "根据新闻id查评论", notes = "根据新闻id查评论")
    public EasyUIDataGridResult selectByNewsId(@ApiParam(value = "新闻id", required = true) @PathVariable String newsId
            , @ApiParam(value = "当前页", required = true) @PathVariable int page, @ApiParam(value = "显示条数", required =
            true) @PathVariable int rows) {
        EasyUIDataGridResult list = biyeCommentService.selectByNewId(newsId, page, rows);

        return list;
    }

    @PostMapping(value = "/selectSComment/{commentId}")
    @ApiOperation(value = "根据一级评论查二级评论", notes = "根据一级评论查二级评论")
    public List<BiyeScomment> selectSComment(@ApiParam(value = "一级评论的id", required = true) @PathVariable String commentId) {
        List<BiyeScomment> list = biyeCommentService.selectSComment(commentId);
        for (BiyeScomment biyeScomment : list) {
            System.out.println(list);
        }
        return list;
    }

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 一级评论点赞
     * @Date 20:10 2019/6/10
     * @Param [commentId]
     **/
    @ApiOperation("评论点赞")
    @RequestMapping(value = "/commentGiveLike/{commentId}", method = RequestMethod.GET)
    public E3Result dianzhan(@ApiParam("评论id") @PathVariable String commentId) {
        E3Result result = biyeCommentService.dianZhan(commentId);
        return result;
    }

    @ApiOperation("二级评论点赞")
    @RequestMapping(value = "/scommentLike/{scommentId}", method = RequestMethod.GET)
    public E3Result erjiDianZhan(@ApiParam(value = "二级评论Id", required = true) @PathVariable String scommentId) {
        E3Result result = biyeCommentService.erJidianZhan(scommentId);
        return result;
    }

}
