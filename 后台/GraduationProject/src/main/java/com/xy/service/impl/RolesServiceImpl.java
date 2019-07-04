package com.xy.service.impl;

import com.xy.common.utils.E3Result;
import com.xy.mapper.RolesMapper;
import com.xy.pojo.*;
import com.xy.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @Auther: LinXiaoKai
 * @Date: 2019/5/14 16:54
 * @Description:
 */
@Service
public class RolesServiceImpl implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public List<BiyeRoles> selectAll() {
        List<BiyeRoles> list = rolesMapper.selectAll();
        return list;
    }

    @Override
    public E3Result giveSoles(BiyeAdminRole biyeAdminRole) {
        rolesMapper.giveSoles(biyeAdminRole);
        return E3Result.ok();
    }

    @Override
    public List<BiyeRoles> selectRoleById(String adminId) {
        List<BiyeRoles> list = rolesMapper.selectRoleById(adminId);
        return list;
    }

    @Override
    public List<BiyeAdmin> selectAdminRoles() {
        List<BiyeAdmin> list = rolesMapper.selectAdminRoles();
        return list;
    }

    @Override
    public List<ArrayPojo> selectQuanxian(String adminId, AdminIdPojo adminIdPojo) {
        List<String> list1 = new ArrayList<String>();
        List<String> list2 = new ArrayList<String>();
        List<ArrayPojo> list = rolesMapper.selectQuanxian(adminId);
        for (ArrayPojo arrayPojo : list) {
            list2.add(arrayPojo.getRoleId());
        }


        if (!adminIdPojo.getRoleId1().equals("string")) {
            list1.add(adminIdPojo.getRoleId1());
        }
        if (!adminIdPojo.getRoleId2().equals("string")) {
            list1.add(adminIdPojo.getRoleId2());
        }
        if (!adminIdPojo.getRoleId3().equals("string")) {
            list1.add(adminIdPojo.getRoleId3());
        }
        if (!adminIdPojo.getRoleId4().equals("string")) {
            list1.add(adminIdPojo.getRoleId4());
        }

        // 添加的权限
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        for (String s1 : reduce1) {
            System.out.println("添加权限"+s1);
            rolesMapper.addQuanxian(s1,adminId);
        }

        // 删除的权限
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println(reduce2);
        for (String s2 : reduce2) {
            System.out.println("删除权限"+s2);
            rolesMapper.deleteQuanxian(s2,adminId);
        }

        return list;
    }

}
