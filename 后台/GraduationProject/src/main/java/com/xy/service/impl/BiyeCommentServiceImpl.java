package com.xy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.common.utils.SensitiveFilter;
import com.xy.mapper.BiyeCommentMapper;
import com.xy.pojo.*;
import com.xy.service.BiyeCommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


/**
 * @Auther: xyh
 * @Date: 2019/5/14 19:31
 * @Description:
 */

@Service
public class BiyeCommentServiceImpl implements BiyeCommentService {


    @Autowired
    private BiyeCommentMapper biyeCommentMapper;

    //获取所有评论
    @Override
    public EasyUIDataGridResult selectall(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);

        List<BiyeComment> biyeComments = biyeCommentMapper.selectall();

        PageInfo<BiyeComment> pageInfo = new PageInfo<>(biyeComments);

        EasyUIDataGridResult EasyUIDataGridResult = new EasyUIDataGridResult();
        EasyUIDataGridResult.setTotal(pageInfo.getTotal());
        EasyUIDataGridResult.setRows(biyeComments);
        return EasyUIDataGridResult;

    }

    @Override
    public List<BiyeScomment> selectSComment(String commentId) {
        List<BiyeScomment> list = biyeCommentMapper.selectSComment(commentId);
        return list;
    }

    //根据id删除评论
    @Override
    public void deletebyid(String commentId) {
        biyeCommentMapper.deletebyid(commentId);
    }

    //添加评论的逻辑 lxk
    @Override
    public E3Result addComment(UniquePojo uniquePojo) {
        SensitiveFilter filter = SensitiveFilter.DEFAULT;
        try {
            //先对评论内容进行关键词屏蔽
            String comm = uniquePojo.getCommentDesc();
            System.out.println("关键词屏蔽之前:" + comm);
            String comm1 = filter.filter(comm, '*');
            System.out.println("关键词屏蔽后:" + comm1);
            uniquePojo.setCommentDesc(comm1);
            uniquePojo.setCommentId(UUID.randomUUID().toString().replaceAll("-", ""));

            //再对评论表进行插入
            biyeCommentMapper.addComment(uniquePojo);

            //再插入用户-评论表
            biyeCommentMapper.addCommentUser(uniquePojo);
            System.out.println("插入成功！");
        } catch (Exception e) {
            System.out.println("程序出错！");
        }
        return E3Result.ok();
    }

    @Override
    public E3Result addSComment(SCommentPojo sCommentPojo) {
        SensitiveFilter filter = SensitiveFilter.DEFAULT;
        try {
            //先对子评论内容进行关键词屏蔽
            String comm = sCommentPojo.getScommentDesc();
            System.out.println("关键词屏蔽之前:" + comm);
            String comm1 = filter.filter(comm, '*');
            System.out.println("关键词屏蔽后:" + comm1);
            sCommentPojo.setScommentDesc(comm1);
            sCommentPojo.setScommentId(UUID.randomUUID().toString().replaceAll("-", ""));

            //再对评论表进行插入
            biyeCommentMapper.addSComment(sCommentPojo);

            //再插入用户-评论表
            biyeCommentMapper.addSComment2(sCommentPojo);
            System.out.println("插入成功！");
        } catch (Exception e) {
            System.out.println("程序出错！");
        }
        return E3Result.ok();
    }

    @Override
    public EasyUIDataGridResult selectByNewId(String newsId, int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);

        List<BiyeComment2> list = biyeCommentMapper.selectCommentByNewId(newsId);

        PageInfo<BiyeComment2> pageInfo = new PageInfo<>(list);

        EasyUIDataGridResult EasyUIDataGridResult = new EasyUIDataGridResult();
        EasyUIDataGridResult.setTotal(pageInfo.getTotal());
        EasyUIDataGridResult.setRows(list);
        return EasyUIDataGridResult;
    }

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 添加点赞数
     * @Date 19:31 2019/6/10
     * @Param [commentId]
     **/
    @Override
    public E3Result dianZhan(String commentId) {
        if (StringUtils.isBlank(commentId)) {
            return E3Result.build(400, "评论ID不能为空！");
        }
        biyeCommentMapper.giveALike(commentId);
        return E3Result.ok("点赞成功！");
    }

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 二级评论点赞
     * @Date 20:15 2019/6/10
     * @Param [scommentId]
     **/
    @Override
    public E3Result erJidianZhan(String scommentId) {
        if (StringUtils.isBlank(scommentId)) {
            return E3Result.build(400, "二级评论ID不能为空！");
        }
        biyeCommentMapper.dianzhan(scommentId);
        return E3Result.ok("点赞成功!");
    }

}
