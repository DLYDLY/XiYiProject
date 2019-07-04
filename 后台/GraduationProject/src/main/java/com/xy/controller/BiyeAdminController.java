package com.xy.controller;

import com.xy.common.utils.E3Result;
import com.xy.pojo.BiyeAdmin;
import com.xy.service.BiyeAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/biyeAdmim")
@Api("管理员信息管理")
public class BiyeAdminController {

    @Autowired
    private BiyeAdminService biyeAdminService;

    @ApiOperation(value = "查询所有管理员信息", notes = "查询所有")
    @RequestMapping(value = "/selectBiyeUserAll",method = RequestMethod.GET)
    public List<BiyeAdmin> selectBiyeAdminAll() {
        List<BiyeAdmin> biyeAdmins = biyeAdminService.selectBiyeAdminAll();
        return biyeAdmins;
    }

    @ApiOperation(value = "根据Id查询管理员信息", notes = "根据Id查询")
    @RequestMapping(value = "/selectAdminId/{adminId}",method = RequestMethod.GET)
    public List<BiyeAdmin> selectAdminId(@ApiParam(value = "管理员ID",required = true) @PathVariable String adminId) {
       List<BiyeAdmin> list = biyeAdminService.selectBiyeAdminAdminId(adminId);
        for (BiyeAdmin biyeAdmin : list) {
            System.out.println(biyeAdmin);
        }
        return list;
    }

    @ApiOperation(value = "根据Id修改管理员信息", notes = "根据Id修改管理员信息")
    @RequestMapping(value = "/updateAdminId/{adminId}",method = RequestMethod.POST)
    public E3Result updateBiyeAdminAdminId(@RequestBody BiyeAdmin biyeAdmin) {
        biyeAdminService.updateBiyeAdminAdminId(biyeAdmin);
        return E3Result.ok();
    }

    @ApiOperation(value = "根据Id删除管理员信息", notes = "根据Id删除管理员信息")
    @RequestMapping(value = "/deleteAdminId/{adminId}",method = RequestMethod.GET)
    public E3Result deleteBiyeAdminAdminId(@ApiParam(value = "管理员ID",required = true) @PathVariable String adminId) {
        biyeAdminService.deleteBiyeAdminAdminId(adminId);
        return E3Result.ok();
    }

    @ApiOperation(value = "添加管理员", notes = "添加管理员")
    @PostMapping("/savaAdmin")
    public E3Result savaSpringb(@RequestBody BiyeAdmin biyeAdmin) {
        String uuid = UUID.randomUUID().toString();
        String adminId = uuid.replaceAll("-", "");
        biyeAdmin.setAdminId(adminId);
        String password = DigestUtils.md5DigestAsHex(biyeAdmin.getAdminPassword().getBytes());

        biyeAdmin.setAdminPassword(password);

        biyeAdminService.savaBiyeAdmin(biyeAdmin);
        return E3Result.ok();
    }



}
