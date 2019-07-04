package com.xy.controller;

import com.xy.common.utils.E3Result;
import com.xy.pojo.BiyeUser;
import com.xy.service.LoginService;
import com.xy.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author GuoZhengYing
 * @ClassName SsoController
 * @Description TDDO
 * @Date 2019/5/20 18:39
 * @Version 1.0
 */
@RestController
@Api("登录与注册接口")
@RequestMapping("/sso")
public class SsoController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;


    @ApiOperation(value = "登录", notes = "微信登录获取OpendId", httpMethod = "GET")
    @RequestMapping("/WXLogin/{code}")
    public E3Result getWxLogin(@ApiParam(value = "用户临时验证码", required = true) @PathVariable String code) {
        E3Result result = loginService.getWxUserOpenid(code);
        return result;
    }

    @ApiOperation(value = "校验", notes = "校验登录码是否有效", httpMethod = "GET")
    @RequestMapping("/verify/{jwt}")
    public E3Result checkOutJWT(@ApiParam(value = "存在本地登录码", required = true) @PathVariable String jwt) {
        E3Result result = loginService.verificationLogin(jwt);
        return result;
    }

    @ApiOperation(value = "验证", notes = "邮箱验证码", httpMethod = "GET")
    @RequestMapping("/verifyEmail/{email}")
    public E3Result checkOutEmail(@ApiParam(value = "邮箱", required = true) @PathVariable String email) {
        E3Result result = registerService.mailboxVerification(email);
        return result;
    }


    @ApiOperation(value = "注册", notes = "用户注册", httpMethod = "POST")
    @RequestMapping("/register")
    public E3Result zhuche(@RequestBody BiyeUser user) {
        E3Result result = registerService.registerUser(user);
        return result;
    }


    @ApiOperation("管理员登录")
    @RequestMapping(value = "/LoginAdmin/{adminName}/{adminPassword}", method = RequestMethod.POST)
    public E3Result adminLogin(@ApiParam(value = "管理员名", required = true) @PathVariable String adminName,
                               @ApiParam(value = "密码", required = true) @PathVariable String adminPassword) {
        E3Result result = loginService.adminLogin(adminName, adminPassword);
        return result;
    }

}
