package com.xy.mapper;

import com.xy.pojo.*;

import java.util.List;

/**
 * @Auther: xyh
 * @Date: 2019/5/14 19:26
 * @Description:
 */
public interface BiyeCommentMapper {

    //获取所有评论
    List<BiyeComment> selectall();

    //根据id删除评论
    void deletebyid(String commentId);

    //根据commentid查看子评论
    List<BiyeScomment> selectSComment(String commentId);

    //添加评论 lxk
    void addComment(UniquePojo uniquePojo);

    //添加用户——评论 lxk
    void addCommentUser(UniquePojo uniquePojo);

    //添加子评论
    void addSComment(SCommentPojo sCommentPojo);

    //添加评论--子评论
    void addSComment2(SCommentPojo sCommentPojo);

    //根据新闻id查评论
    List<BiyeComment2> selectCommentByNewId(String newsId);

    /**
     * @return void
     * @Author GuoZhengYing
     * @Description //TODO 点赞
     * @Date 19:31 2019/6/10
     * @Param [commentId]
     **/
    void giveALike(String commentId);

    /**
     * @return void
     * @Author GuoZhengYing
     * @Description //TODO 二级评论点赞
     * @Date 20:11 2019/6/10
     * @Param [scommentId]
     **/
    void dianzhan(String scommentId);
}
