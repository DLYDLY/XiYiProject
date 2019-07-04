package com.xy.pojo;

/**
 * @Auther: LinXiaoKai
 * @Date: 2019/6/4 10:17
 * @Description:
 */
public class NumPojo {

    private int newsNum;
    private int userNum;

    @Override
    public String toString() {
        return "NumPojo{" +
                "newsNum=" + newsNum +
                ", userNum=" + userNum +
                '}';
    }

    public int getNewsNum() {
        return newsNum;
    }

    public void setNewsNum(int newsNum) {
        this.newsNum = newsNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }
}
