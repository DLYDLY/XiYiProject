package com.xy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xy.common.jwt.JwtToken;
import com.xy.common.utils.E3Result;
import com.xy.mapper.BiyeAdminMapper;
import com.xy.pojo.BiyeAdmin;
import com.xy.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author GuoZhengYing
 * @ClassName LoginServiceImpl
 * @Description TDDO
 * @Date 2019/5/18 15:27
 * @Version 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Value("${wx.APPID}")
    private String APPID;

    @Value("${wx.APPSecret}")
    private String APPSecret;

    @Value("${wx.USER_INFO}")
    private String USER_INFO;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private BiyeAdminMapper biyeAdminMapper;

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 微信登录
     * @Date 15:04 2019/5/20
     * @Param [code]
     **/
    @Override
    public E3Result getWxUserOpenid(String code) {

        //拼接url
        StringBuilder url = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?");
        url.append("appid=");//appid设置
        url.append(APPID);
        url.append("&secret=");//secret设置
        url.append(APPSecret);
        url.append("&js_code=");//code设置
        url.append(code);
        url.append("&grant_type=authorization_code");

        try {
            //构建一个Client
            HttpClient client = HttpClientBuilder.create().build();
            //构建一个GET请求
            HttpGet get = new HttpGet(url.toString());
            //提交GET请求
            HttpResponse response = client.execute(get);
            //拿到返回的HttpResponse的"实体"
            HttpEntity result = response.getEntity();
            String content = EntityUtils.toString(result);
            //打印返回的信息
            System.out.println(content);
            JSONObject data = JSON.parseObject(content);
            String opendId = data.getString("openid");
            if (StringUtils.isNotBlank(opendId)) {
                //2、登录成功后生成token。Token相当于原来的jsessionid，字符串，可以使用uuid。
                String jwt = jwtToken.generateToken(opendId);
                //放入缓存，并设置时间
                redisTemplate.opsForValue().set(USER_INFO + ":" + jwt, content);
                // 5、设置key的过期时间。模拟Session的过期时间。一般半个小时。
                redisTemplate.expire(USER_INFO + ":" + jwt, 1, TimeUnit.HOURS);

                return E3Result.ok(jwt);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        //请求失败
        //返回
        return E3Result.build(500, "请求失败");
    }

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 验证jwt是否有效
     * @Date 15:09 2019/5/20
     * @Param [jwt]
     **/
    @Override
    public E3Result verificationLogin(String jwt) {
        //判断是否有jwt
        if (StringUtils.isBlank(jwt)) {
            return E3Result.build(400, "jwt为空");
        }
        //查看redis中的jwt是否过期
        String opendId = (String) redisTemplate.opsForValue().get(USER_INFO + ":" + jwt);
        if (StringUtils.isBlank(opendId)) {
            //过期了就重新请求
            return E3Result.build(500, "登录超时请重新登录");
        }
        return E3Result.ok("登录没有超时,可以使用！");
    }


    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 管理员登录
     * @Date 16:00 2019/6/2
     * @Param [name, password]
     **/
    @Override
    public E3Result adminLogin(String name, String password) {
        BiyeAdmin admin = new BiyeAdmin();
        admin.setAdminName(name);
        admin.setAdminPassword(password);
        BiyeAdmin admin1 = biyeAdminMapper.login(admin);
        if (admin1 == null) {
            return E3Result.build(400, "用户名或密码错误");
        }
        return E3Result.build(200, "登录成功", admin1);
    }

}
