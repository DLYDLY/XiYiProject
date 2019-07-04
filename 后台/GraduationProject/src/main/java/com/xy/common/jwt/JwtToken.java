package com.xy.common.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.Date;


/**
 * @author GuoZhengYing
 * @ClassName JwtToken
 * @Description TDDO
 * @Date 2019/5/16 8:59
 * @Version 1.0
 */
@Configuration
public class JwtToken {
    private static Logger logger = LoggerFactory.getLogger(JwtToken.class);

    /**
     * 秘钥
     */
    @Value("${jwt.secret}")
    private String secret;

    /**
     * 过期时间(秒)
     */
    @Value("${jwt.expire}")
    private long expire;


    /**
     * 生成jwt token
     */
    public String generateToken(Object o) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(o + "")
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims getClaimByToken(String token) {
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        String[] header = token.split("Bearer");
        token = header[0];
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            logger.debug("validate is token error ", e);
            return null;
        }
    }

    /**
     * token是否过期
     *
     * @return true：过期
     */
    public static boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }

    // Getter && Setter
    public static Logger getLogger() {
        return logger;
    }

    public static void setLogger(Logger logger) {
        JwtToken.logger = logger;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpire() {
        return expire;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }
}
