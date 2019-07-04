package com.xy.controller;

import com.xy.common.utils.E3Result;
import com.xy.pojo.*;
import com.xy.service.RolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: LinXiaoKai
 * @Date: 2019/5/14 16:58
 * @Description:
 */
@RestController
@RequestMapping("/roles")
@Api("权限管理")
public class RolesController {

    @Autowired
    private RolesService rolesService;

    @ApiOperation(value = "获取全部权限信息", notes = "获取全部权限信息")
    @GetMapping("/selectAllRoles")
    public List<BiyeRoles> selectAllRoles() {
        List<BiyeRoles> list = rolesService.selectAll();
        return list;
    }

    @ApiOperation(value = "赋予管理员权限", notes = "赋予管理员权限")
    @PostMapping(value = "add/{biyeAdminRole}")
    public E3Result addSoles(@RequestBody BiyeAdminRole biyeAdminRole) {
        E3Result result = rolesService.giveSoles(biyeAdminRole);
        return result;
    }

    @ApiOperation(value = "查看管理员所拥有权限", notes = "查看管理员所拥有权限")
    @PostMapping("/selectAllRolesById")
    public List<BiyeRoles> selectRolesByAdminId(@RequestBody String adminId) {
        List<BiyeRoles> list = rolesService.selectRoleById(adminId);
        return list;
    }

    @GetMapping("/selectAdminRoles")
    @ApiOperation(value = "查看所有管理员以及拥有权限", notes = "查看所有管理员以及拥有权限")
    public List<BiyeAdmin> selectAdminRoles() {
        List<BiyeAdmin> list = rolesService.selectAdminRoles();
        return list;
    }

    @PostMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adminId", value = "管理员ID", required = true, paramType = "query", dataType =
                    "String")
    })
    @ApiOperation(value = "修改管理员权限", notes = "修改管理员权限")
    public List<ArrayPojo> selectQuanxian(String adminId, @RequestBody AdminIdPojo adminIdPojo) {
        List<ArrayPojo> list = rolesService.selectQuanxian(adminId, adminIdPojo);
        return list;
    }
}
