package com.xy.service;

import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.pojo.BiyeComment;
import com.xy.pojo.BiyeScomment;
import com.xy.pojo.SCommentPojo;
import com.xy.pojo.UniquePojo;

import java.util.List;

/**
 * @Auther: xyh
 * @Date: 2019/5/14 19:31
 * @Description:
 */
public interface BiyeCommentService {

    //获取所有评论
    EasyUIDataGridResult selectall(int page, int rows);

    //根据commentid查看子评论
    List<BiyeScomment> selectSComment(String commentId);

    //根据id删除评论
    void deletebyid(String commentId);

    //对添加评论逻辑 lxk
    E3Result addComment(UniquePojo uniquePojo);

    //添加子评论逻辑 lxk
    E3Result addSComment(SCommentPojo sCommentPojo);

    EasyUIDataGridResult selectByNewId(String newsId, int page, int rows);

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 一级评论点赞
     * @Date 20:13 2019/6/10
     * @Param [commentId]
     **/
    E3Result dianZhan(String commentId);

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 二级评论点赞
     * @Date 20:13 2019/6/10
     * @Param [scommentId]
     **/
    E3Result erJidianZhan(String scommentId);
}
