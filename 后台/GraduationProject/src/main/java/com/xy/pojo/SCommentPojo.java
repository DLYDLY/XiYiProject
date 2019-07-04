package com.xy.pojo;

import java.io.Serializable;

/**
 * @Auther: LinXiaoKai
 * @Date: 2019/6/2 14:39
 * @Description:
 * 子评论的pojo
 */
public class SCommentPojo implements Serializable {
    private String scommentId;
    private String commentId;

    private String scommentDesc;
    private String userId;


    @Override
    public String toString() {
        return "SCommentPojo{" +
                "scommentId='" + scommentId + '\'' +
                ", commentId='" + commentId + '\'' +
                ", scommentDesc='" + scommentDesc + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScommentId() {
        return scommentId;
    }

    public void setScommentId(String scommentId) {
        this.scommentId = scommentId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getScommentDesc() {
        return scommentDesc;
    }

    public void setScommentDesc(String scommentDesc) {
        this.scommentDesc = scommentDesc;
    }
}
