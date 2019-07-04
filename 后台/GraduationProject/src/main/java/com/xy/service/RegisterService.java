package com.xy.service;

import com.xy.common.utils.E3Result;
import com.xy.pojo.BiyeUser;

/**
 * @author GuoZhengYing
 * @ClassName RegisterService
 * @Description TDDO
 * @Date 2019/5/21 8:49
 * @Version 1.0
 */
public interface RegisterService {

    E3Result mailboxVerification(String email);

    E3Result registerUser(BiyeUser user);
}
