package com.xy.pojo;

/**
 * @Auther: LinXiaoKai
 * @Date: 2019/5/21 10:08
 * @Description:
 */
public class ArrayPojo {

    private String roleId;

    @Override
    public String toString() {
        return "ArrayPojo{" +
                "roleId='" + roleId + '\'' +
                '}';
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
