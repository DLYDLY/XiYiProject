package com.xy.pojo;

/**
 * @Auther: LinXiaoKai
 * @Date: 2019/5/21 14:32
 * @Description:
 */
public class AdminIdPojo {
    private String roleId1;
    private String roleId2;
    private String roleId3;
    private String roleId4;

    @Override
    public String toString() {
        return "AdminIdPojo{" +
                "roleId1='" + roleId1 + '\'' +
                ", roleId2='" + roleId2 + '\'' +
                ", roleId3='" + roleId3 + '\'' +
                ", roleId4='" + roleId4 + '\'' +
                '}';
    }

    public String getRoleId1() {
        return roleId1;
    }

    public void setRoleId1(String roleId1) {
        this.roleId1 = roleId1;
    }

    public String getRoleId2() {
        return roleId2;
    }

    public void setRoleId2(String roleId2) {
        this.roleId2 = roleId2;
    }

    public String getRoleId3() {
        return roleId3;
    }

    public void setRoleId3(String roleId3) {
        this.roleId3 = roleId3;
    }

    public String getRoleId4() {
        return roleId4;
    }

    public void setRoleId4(String roleId4) {
        this.roleId4 = roleId4;
    }
}
