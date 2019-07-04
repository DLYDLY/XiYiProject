package com.xy.service.impl;

import com.xy.mapper.BiyeAdminMapper;
import com.xy.pojo.BiyeAdmin;
import com.xy.service.BiyeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BiyeAdminServiceImpl implements BiyeAdminService {

    @Autowired
    private BiyeAdminMapper biyeAdminMapper;

    /**
     * 查询biyeadmin全部管理员信息
     */
    @Override
    public List<BiyeAdmin> selectBiyeAdminAll() {
        return biyeAdminMapper.selectBiyeAdminAll();
    }

    /**
     *根据管理员 adminId 查询管理员信息
     * wzj
     */
    @Override
    public List<BiyeAdmin> selectBiyeAdminAdminId(String adminId) {
        List<BiyeAdmin> list = biyeAdminMapper.selectBiyeAdminAdminId(adminId);
        return list;
    }

    /**
     * 根据userId 修改管理信息
     */
    @Override
    public void updateBiyeAdminAdminId(BiyeAdmin biyeAdmin) {
        biyeAdminMapper.updateBiyeAdminAdminId(biyeAdmin);
    }

    /**
     * 根据userId 删除管理信息
     */
    @Override
    public void deleteBiyeAdminAdminId(String adminId) {
        biyeAdminMapper.deleteAdminRoles(adminId);
        biyeAdminMapper.deleteBiyeAdminAdminId(adminId);
    }

    /**
     * 添加管理员信息
     */
    @Override
    public void savaBiyeAdmin(BiyeAdmin biyeAdmin) {
        biyeAdminMapper.savaBiyeAdmin(biyeAdmin);
    }
}
