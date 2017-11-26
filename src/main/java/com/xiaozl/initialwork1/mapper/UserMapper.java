package com.xiaozl.initialwork1.mapper;

import org.apache.ibatis.annotations.Param;

import com.xiaozl.initialwork1.entity.User;

import java.util.List;

/**
 * @author xiaozl
 * @date 2017/11/20
 */
public interface UserMapper {

    public void newUser(@Param("user") User user) throws Exception;

    public int countByUserNameAndPassword(@Param("userName") String userName,
                                          @Param("password") String password) throws Exception;

    public List<User> listUser() throws Exception;

}
