package com.xy.service;

import com.xy.common.utils.E3Result;
import com.xy.pojo.BiyeUser;

public interface LoginService {

    E3Result getWxUserOpenid(String code);

    E3Result verificationLogin(String jwt);

    E3Result adminLogin(String adminName, String adminPassword);
}
