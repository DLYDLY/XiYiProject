package com.xy.pojo;


public class BiyeGrace {

  private long gId;
  private String graceName;

  @Override
  public String toString() {
    return "BiyeGrace{" +
            "gId=" + gId +
            ", graceName='" + graceName + '\'' +
            '}';
  }

  public long getGId() {
    return gId;
  }

  public void setGId(long gId) {
    this.gId = gId;
  }


  public String getGraceName() {
    return graceName;
  }

  public void setGraceName(String graceName) {
    this.graceName = graceName;
  }

}
