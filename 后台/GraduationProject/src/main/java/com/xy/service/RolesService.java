package com.xy.service;

import com.xy.common.utils.E3Result;
import com.xy.pojo.*;

import java.util.List;

/**
 * @Auther: LinXiaoKai
 * @Date: 2019/5/14 16:47
 * @Description:
 */
public interface RolesService {


    //查看全部的权限
    List<BiyeRoles> selectAll();

    //给管理员赋予权限
    E3Result giveSoles(BiyeAdminRole biyeAdminRole);

    //查看xx管理员是否有xx权限
    List<BiyeRoles> selectRoleById(String adminId);

    //查看所有管理员 以及所拥有的权限
    List<BiyeAdmin> selectAdminRoles();

    //修改管理员权限
    List<ArrayPojo> selectQuanxian(String adminId, AdminIdPojo adminIdPojo);
}
