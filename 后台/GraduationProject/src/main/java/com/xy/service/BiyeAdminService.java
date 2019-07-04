package com.xy.service;

import com.xy.pojo.BiyeAdmin;


import java.util.List;

public interface BiyeAdminService {

    /**
     * 查询biyeadmin全部管理员信息
     */
    List<BiyeAdmin> selectBiyeAdminAll();

    /**
     *根据管理员 adminId 查询管理员信息
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
     * 添加管理员信息
     */
    void   savaBiyeAdmin(BiyeAdmin biyeAdmin);
}
