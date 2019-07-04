package com.xy.controller;

import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.pojo.BiyeUser;
import com.xy.service.BiyeUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/biyeUser")
@Api("用户信息管理")
public class BiyeUserController {

    @Autowired
    private BiyeUserService biyeUserService;

    @ApiOperation(value = "分页查询所有用户信息", notes = "查询所有")
    @RequestMapping(value = "/selectBiyeUserAll/{page}/{rows}", method = RequestMethod.GET)
    public EasyUIDataGridResult selectBiyeUserAll(@ApiParam(value = "当前页数", required = true) @PathVariable int page,
                                                  @ApiParam(value = "要显示条数", required = true) @PathVariable int rows) {
        EasyUIDataGridResult result = biyeUserService.selectBiyeUserAll(page, rows);
        return result;
    }

    @ApiOperation(value = "根据Id查询用户信息", notes = "根据Id查询")
    @RequestMapping(value = "/selectUserId/{userId}", method = RequestMethod.GET)
    public BiyeUser selectUserId(@ApiParam(value = "用户ID", required = true) @PathVariable String userId) {
        BiyeUser biyeUser = biyeUserService.selectBiyeUserUserId(userId);
        return biyeUser;
    }


    @ApiOperation(value = "根据Id注销用户信息", notes = "根据Id注销")
    @RequestMapping(value = "/cancelUserId/{userId}", method = RequestMethod.GET)
    public E3Result cancelUserId(@ApiParam(value = "用户ID", required = true) @PathVariable String userId) {
        biyeUserService.cancelBiyeUserUserId(userId);
        return E3Result.ok();
    }

    @ApiOperation(value = "查询", notes = "根据opendid查询用户信息")
    @RequestMapping(value = "/selectByOpendId/{jwt}", method = RequestMethod.GET)
    public E3Result selectUserByOpendId(@ApiParam(value = "用户唯一标示码", required = true) @PathVariable String jwt) {
        E3Result result = biyeUserService.selectBiyeUserByopendId(jwt);
        return result;
    }

    @ApiOperation(value = "修改", notes = "修改用户信息")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public E3Result updateByUser(@RequestBody BiyeUser user) {
        E3Result result = biyeUserService.updateBiyeUserUserId(user);
        return result;
    }
}
