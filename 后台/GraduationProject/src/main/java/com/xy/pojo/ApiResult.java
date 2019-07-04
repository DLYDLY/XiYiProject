package com.xy.pojo;

/**
 * @Auther: xyh
 * @Date: 2019/5/15 19:00
 * @Description:
 */

public class ApiResult {

    String title;
    String id;
    String scoreClass;
    String channelId;
    String channelName;

    public ApiResult(String title, String id, String scoreClass, String channelId, String channelName) {
        this.title = title;
        this.id = id;
        this.scoreClass = scoreClass;
        this.channelId = channelId;
        this.channelName = channelName;
    }


    public ApiResult() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScoreClass() {
        return scoreClass;
    }

    public void setScoreClass(String scoreClass) {
        this.scoreClass = scoreClass;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "title='" + title + '\'' +
                ", id='" + id + '\'' +
                ", scoreClass='" + scoreClass + '\'' +
                ", channelId='" + channelId + '\'' +
                ", channelName='" + channelName + '\'' +
                '}';
    }
}
