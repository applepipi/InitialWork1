package com.xiaozl.initialwork1.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xiaozl.initialwork1.entity.User;

/**
 * @author xiaozl
 * @date 2017/11/20
 */
public interface UserService {

    /**
     * 新建用户，验重
     *
     * @param user
     * @throws Exception
     */
    public User newUser(User user) throws Exception;

    /**
     * 检验登录
     *
     * @param user
     * @return
     * @throws Exception
     */
    public boolean checkLogin(User user) throws Exception;

    /**
     * 用户列表
     *
     * @return
     * @throws Exception
     */
    public List<User> listUser() throws Exception;
}
