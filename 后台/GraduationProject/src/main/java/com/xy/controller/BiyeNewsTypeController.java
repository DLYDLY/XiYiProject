package com.xy.controller;

import com.xy.pojo.BiyeNewsType;
import com.xy.service.BiyeNewsTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author GuoZhengYing
 * @ClassName BiyeNewsTypeController
 * @Description TDDO
 * @Date 2019/5/17 10:48
 * @Version 1.0
 */
@RestController
@Api("新闻栏目接口")
public class BiyeNewsTypeController {

    @Autowired
    private BiyeNewsTypeService biyeNewsTypeService;


    @ApiOperation(value = "查看所有新闻栏目", httpMethod = "GET")
    @RequestMapping("/NewsTypeAll")
    public List<BiyeNewsType> selectAll() {
        List<BiyeNewsType> newsTypes = biyeNewsTypeService.selectAll();
        return newsTypes;
    }

}
