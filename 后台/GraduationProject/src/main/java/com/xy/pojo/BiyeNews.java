package com.xy.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class BiyeNews {

    private String newsId;
    private String newsTitle;
    private String newsContent;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date newsDate;
    private String newsTypeId;
    private long newslsTop;
    private long state;
    private String userId;

    private List<BiyeNewsImage> biyeNewsImages;


    public BiyeNews() {
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public String getNewsTypeId() {
        return newsTypeId;
    }

    public void setNewsTypeId(String newsTypeId) {
        this.newsTypeId = newsTypeId;
    }

    public long getNewslsTop() {
        return newslsTop;
    }

    public void setNewslsTop(long newslsTop) {
        this.newslsTop = newslsTop;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<BiyeNewsImage> getBiyeNewsImages() {
        return biyeNewsImages;
    }

    public void setBiyeNewsImages(List<BiyeNewsImage> biyeNewsImages) {
        this.biyeNewsImages = biyeNewsImages;
    }


}


