package com.xy.pojo;


public class BiyeNewsType {

  private String typeId;
  private String typeName;


  public String getTypeId() {
    return typeId;
  }

  public void setTypeId(String typeId) {
    this.typeId = typeId;
  }


  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  @Override
  public String toString() {
    return "BiyeNewsType{" +
            "typeId='" + typeId + '\'' +
            ", typeName='" + typeName + '\'' +
            '}';
  }
}
