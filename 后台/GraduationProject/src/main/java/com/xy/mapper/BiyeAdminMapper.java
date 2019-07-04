package com.xy.mapper;

import com.xy.pojo.BiyeAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BiyeAdminMapper {

    /**
     * 查询biyeadmin全部管理员信息
     */
    List<BiyeAdmin> selectBiyeAdminAll();

    /**
     * 根据管理员 adminId 查询管理员信息
     * wzj
     */
    List<BiyeAdmin> selectBiyeAdminAdminId(String adminId);


    /**
     * 根据userId 修改管理信息
     */
    void updateBiyeAdminAdminId(BiyeAdmin biyeAdmin);

    /**
     * 根据userId 删除管理信息
     */
    void deleteBiyeAdminAdminId(String adminId);


    /**
     * 根据userId 删除管理员-权限信息
     * @param adminId
     */
    void deleteAdminRoles(String adminId);


    /**
     * 添加管理员信息
     */
    void savaBiyeAdmin(BiyeAdmin biyeAdmin);

    /**
     * @return com.xy.pojo.BiyeAdmin
     * @Author GuoZhengYing
     * @Description //TODO admin登录验证
     * @Date 16:06 2019/6/2
     * @Param [name, password]
     **/
    BiyeAdmin login(BiyeAdmin biyeAdmin);
}
