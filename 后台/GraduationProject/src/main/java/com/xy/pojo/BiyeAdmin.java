package com.xy.pojo;


import java.util.List;

public class BiyeAdmin {

  private String adminId;
  private String adminName;
  private String adminPassword;

  private List<BiyeRoles> roles;

  public List<BiyeRoles> getRoles() {
    return roles;
  }

  public void setRoles(List<BiyeRoles> roles) {
    this.roles = roles;
  }

  public String getAdminId() {
    return adminId;
  }

  public void setAdminId(String adminId) {
    this.adminId = adminId;
  }


  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }


  public String getAdminPassword() {
    return adminPassword;
  }

  public void setAdminPassword(String adminPassword) {
    this.adminPassword = adminPassword;
  }

  @Override
  public String toString() {
    return "BiyeAdmin{" +
            "adminId='" + adminId + '\'' +
            ", adminName='" + adminName + '\'' +
            ", adminPassword='" + adminPassword + '\'' +
            ", roles=" + roles +
            '}';
  }
}
