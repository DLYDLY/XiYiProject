package com.xy.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BiyeScomment {

  private String scommentId;
  private String scommentDesc;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date scommentDate;
  private long del;
  private Integer likeNum;
  private String userId;

  private BiyeUser biyeUser;


  public BiyeUser getBiyeUser() {
    return biyeUser;
  }

  public void setBiyeUser(BiyeUser biyeUser) {
    this.biyeUser = biyeUser;
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

  public String getScommentDesc() {
    return scommentDesc;
  }

  public void setScommentDesc(String scommentDesc) {
    this.scommentDesc = scommentDesc;
  }

  public Date getScommentDate() {
    return scommentDate;
  }

  public void setScommentDate(Date scommentDate) {
    this.scommentDate = scommentDate;
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
}
