package com.xy.mapper;

import com.xy.pojo.ArrayPojo;
import com.xy.pojo.BiyeAdmin;
import com.xy.pojo.BiyeAdminRole;
import com.xy.pojo.BiyeRoles;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * @Auther: LinXiaoKai
 * @Date: 2019/5/14 16:41
 * @Description:
 */
public interface RolesMapper {

    //查看全部的权限
    List<BiyeRoles> selectAll();

    //给管理员赋予管理权限
    void giveSoles(BiyeAdminRole biyeAdminRole);

    //查看xx管理员是否有xx权限
    List<BiyeRoles> selectRoleById(String adminId);

    //查看所有管理员 以及所拥有的权限
    List<BiyeAdmin> selectAdminRoles();

    //获取admin所拥有的权限的id
    List<ArrayPojo> selectQuanxian(String adminId);

    //添加权限
    void addQuanxian(@Param(value = "roleId") String roleId, @Param(value = "adminId") String adminId);

    //删除权限
    void deleteQuanxian(@Param(value = "roleId") String roleId, @Param(value = "adminId") String adminId);

}

