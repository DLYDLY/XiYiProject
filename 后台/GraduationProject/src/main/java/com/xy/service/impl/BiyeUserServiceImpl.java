package com.xy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xy.common.jwt.JwtToken;
import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.mapper.BiyeUserMapper;
import com.xy.pojo.BiyeNews;
import com.xy.pojo.BiyeUser;
import com.xy.service.BiyeUserService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BiyeUserServiceImpl implements BiyeUserService {

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private BiyeUserMapper biyeUserMapper;

    /**
     * 查询biyeuser全部用户信息
     * 分页
     */


    @Override
    public EasyUIDataGridResult selectBiyeUserAll(int page, int rows) {
        //设置分页信息
        PageHelper.startPage(page, rows);
        List<BiyeUser> list = biyeUserMapper.selectBiyeUserAll();
        //取分页信息
        PageInfo<BiyeUser> pageInfo = new PageInfo<>(list);

        //创建返回结果对象
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(pageInfo.getTotal());
        result.setRows(list);
        return result;
    }

    /**
     * 根据用户 userId 查询用户信息
     * wzj
     * 17-23
     */
    @Override
    public BiyeUser selectBiyeUserUserId(String userId) {
        return biyeUserMapper.selectBiyeUserUserId(userId);
    }

    /**
     * 根据用户 userId 注销用户信息
     */
    @Override
    public void cancelBiyeUserUserId(String userId) {
        biyeUserMapper.cancelBiyeUserUserId(userId);
    }

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 根据opendid查询用户
     * @Date 8:57 2019/5/22
     * @Param [jwt]
     **/
    @Override
    public E3Result selectBiyeUserByopendId(String jwt) {
        if (StringUtils.isBlank(jwt)) {
            return E3Result.build(400, "用户唯一码不能为空！");
        }
        Claims token = jwtToken.getClaimByToken(jwt);
        String subject = token.getSubject();
        BiyeUser biyeUser = biyeUserMapper.selectUserByOpendId(subject);
        if (biyeUser == null){
            return E3Result.build(400,"该用户没有注册");
        }
        return E3Result.ok(biyeUser);
    }


    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 修改用户
     * @Date 8:57 2019/5/22
     * @Param [biyeUser]
     **/
    @Override
    public E3Result updateBiyeUserUserId(BiyeUser biyeUser) {
        if (StringUtils.isBlank(biyeUser.getUserId())) {
            return E3Result.build(400, "用户Id不能为空！");
        }
        biyeUser.setUpdated(new Date());
        biyeUserMapper.updateBiyeUserUserId(biyeUser);
        return E3Result.ok();
    }


}
