package com.xy.service;


import com.xy.common.utils.E3Result;
import com.xy.common.utils.EasyUIDataGridResult;
import com.xy.pojo.BiyeUser;

import java.util.List;

public interface BiyeUserService {

    /**
     * 查询biyeuser全部用户信息
     */
    EasyUIDataGridResult selectBiyeUserAll(int page, int rows);

    /**
     * 根据用户 userId 查询用户信息
     * wzj
     */
    BiyeUser selectBiyeUserUserId(String userId);

    /**
     * 根据用户 userId 注销用户信息
     */
    void cancelBiyeUserUserId(String userId);


    /**
     * @return com.xy.pojo.BiyeUser
     * @Author GuoZhengYing
     * @Description //TODO 根据用户唯一标示码查询用户
     * @Date 8:41 2019/5/22
     * @Param [jwt]
     **/
    E3Result selectBiyeUserByopendId(String jwt);

    /**
     * @return com.xy.common.utils.E3Result
     * @Author GuoZhengYing
     * @Description //TODO 修改用户信息
     * @Date 8:35 2019/5/22
     * @Param [biyeUser]
     **/
    E3Result updateBiyeUserUserId(BiyeUser biyeUser);


}
