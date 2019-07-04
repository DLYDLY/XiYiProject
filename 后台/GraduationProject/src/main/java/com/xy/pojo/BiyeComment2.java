package com.xy.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BiyeComment2 {

    private String commentId;
    private String commentDesc;
    private String newsId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentDate;
    private long del;
    private Integer likeNum;

    private BiyeUser biyeUser;


    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentDesc() {
        return commentDesc;
    }

    public void setCommentDesc(String commentDesc) {
        this.commentDesc = commentDesc;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public long getDel() {
        return del;
    }

    public void setDel(long del) {
        this.del = del;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public BiyeUser getBiyeUser() {
        return biyeUser;
    }

    public void setBiyeUser(BiyeUser biyeUser) {
        this.biyeUser = biyeUser;
    }

    @Override
    public String toString() {
        return "BiyeComment2{" +
                "commentId='" + commentId + '\'' +
                ", commentDesc='" + commentDesc + '\'' +
                ", newsId='" + newsId + '\'' +
                ", commentDate=" + commentDate +
                ", del=" + del +
                ", likeNum=" + likeNum +
                ", biyeUser=" + biyeUser +
                '}';
    }
}
