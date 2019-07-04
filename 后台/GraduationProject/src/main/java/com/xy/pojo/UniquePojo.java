package com.xy.pojo;

import java.io.Serializable;

/**
 * @Auther: LinXiaoKai
 * @Date: 2019/5/17 15:07
 * @Description:
 */
public class UniquePojo  implements Serializable {
    private String commentId;
    private String commentDesc;
    private String newsId;
    private String userId;

    public UniquePojo() {
    }

    public UniquePojo(String commentId, String commentDesc, String newsId, String userId) {
        this.commentId = commentId;
        this.commentDesc = commentDesc;
        this.newsId = newsId;
        this.userId = userId;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
