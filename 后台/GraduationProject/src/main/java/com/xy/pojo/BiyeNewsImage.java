package com.xy.pojo;


public class BiyeNewsImage {

  private String imageId;
  private String imagePath;
  private String newsId;

  public BiyeNewsImage(String imageId, String imagePath, String newsId) {
    this.imageId = imageId;
    this.imagePath = imagePath;
    this.newsId = newsId;
  }

  public BiyeNewsImage(String imagePath, String newsId) {
    this.imagePath = imagePath;
    this.newsId = newsId;
  }

  public BiyeNewsImage() {
  }

  public String getImageId() {
    return imageId;
  }

  public void setImageId(String imageId) {
    this.imageId = imageId;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }

  public String getNewsId() {
    return newsId;
  }

  public void setNewsId(String newsId) {
    this.newsId = newsId;
  }

  @Override
  public String toString() {
    return "BiyeNewsImage{" +
            "imageId='" + imageId + '\'' +
            ", imagePath='" + imagePath + '\'' +
            ", newsId='" + newsId + '\'' +
            '}';
  }
}
