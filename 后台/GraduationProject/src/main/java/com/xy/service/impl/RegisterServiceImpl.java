package com.xy.service.impl;

import com.xy.common.jwt.JwtToken;
import com.xy.common.utils.CodeUtil;
import com.xy.common.utils.E3Result;
import com.xy.common.utils.IDUtils;
import com.xy.common.utils.MailUtil;
import com.xy.mapper.BiyeUserMapper;
import com.xy.pojo.BiyeUser;
import com.xy.service.BiyeUserService;
import com.xy.service.RegisterService;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author GuoZhengYing
 * @ClassName RegisterServiceImpl
 * @Description TDDO
 * @Date 2019/5/21 9:07
 * @Version 1.0
 */
@Service
public class RegisterServiceImpl implements RegisterService {


    @Autowired
    private BiyeUserMapper biyeUserMapper;

    @Autowired
    private JwtToken jwtToken;

    @Override
    public E3Result mailboxVerification(String email) {

        String date = biyeUserMapper.checkOutEmail(email);

        if (StringUtils.isBlank(date)) {

            //利用正则表达式（可改进）验证邮箱是否符合邮箱的格式
            if (!email.matches("^\\w+@(\\w+\\.)+\\w+$")) {
                System.out.println("邮箱验证失败");
                return E3Result.build(500, "邮箱验证失败");
            }
            //生成激活码
            String data = CodeUtil.generateUniqueCode();
            //保存成功则通过线程的方式给用户发送一封邮件
            new Thread(new MailUtil(email, data)).start();

            return E3Result.build(200, "邮件发送！", data);

        }

        return E3Result.build(500, "邮箱已存在不能注册,请重新输入!");

    }

    @Override
    public E3Result registerUser(BiyeUser user) {
        if (StringUtils.isBlank(user.getUsername())) {
            return E3Result.build(400, "用户名不能为空！");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return E3Result.build(400, "密码不能为空！");
        }
        if (StringUtils.isBlank(user.getOpendId())) {
            return E3Result.build(400, "微信用户唯一码不能为空！");
        }
        if (StringUtils.isBlank(user.getPhone())) {
            return E3Result.build(400, "电话不能为空！");
        }
        if (StringUtils.isBlank(user.getEmail())) {
            return E3Result.build(400, "邮箱不能为空！");
        }
        if (StringUtils.isBlank(user.getUserportrait())) {
            return E3Result.build(400, "头像不能为空！");
        }

        String jwt = user.getOpendId();
        Claims token = jwtToken.getClaimByToken(jwt);
        String subject = token.getSubject();
        System.out.println(subject);

        String id = UUID.randomUUID().toString().replaceAll("-", "");
        //补全属性

        String md5Pass = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());

        user.setUserId(id);
        user.setPassword(md5Pass);
        user.setOpendId(subject);

        biyeUserMapper.addUser(user);

        return E3Result.ok("注册成功！");
    }
}
