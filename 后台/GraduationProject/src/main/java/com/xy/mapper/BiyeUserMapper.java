package com.xy.mapper;

import com.xy.pojo.BiyeAdmin;
import com.xy.pojo.BiyeUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BiyeUserMapper {

    /**
     * 查询biyeuser全部用户信息
     */
    List<BiyeUser> selectBiyeUserAll();

    /**
     * 根据用户 userId 查询用户信息
     * wzj
     */
    BiyeUser selectBiyeUserUserId(String userId);

    /**
     * 注销用户
     * 根据用户 userId 注销用户信息
     * 等同于修改user表中state列默认值改为1
     */
    void cancelBiyeUserUserId(String userId);

    /**
     * 根据userId 修改用户信息
     */
    void updateBiyeUserUserId(BiyeUser biyeUser);

    /**
     * @return java.lang.String
     * @Author GuoZhengYing
     * @Description //TODO 验证邮箱是否存在
     * @Date 10:37 2019/5/21
     * @Param [email]
     **/
    String checkOutEmail(String email);


    /**
     * @return void
     * @Author GuoZhengYing
     * @Description //TODO 添加用户
     * @Date 10:52 2019/5/21
     * @Param [user]
     **/
    void addUser(BiyeUser user);


    /**
     * @return com.xy.pojo.BiyeUser
     * @Author GuoZhengYing
     * @Description //TODO 根据opendID查询用户
     * @Date 8:50 2019/5/22
     * @Param [opendId]
     **/
    BiyeUser selectUserByOpendId(String opendId);

}
