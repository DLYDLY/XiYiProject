package com.xy.pojo;


public class BiyeRoles {

  private String roleId;
  private String roleName;


  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }

  @Override
  public String toString() {
    return "BiyeRoles{" +
            "roleId='" + roleId + '\'' +
            ", roleName='" + roleName + '\'' +
            '}';
  }
}
