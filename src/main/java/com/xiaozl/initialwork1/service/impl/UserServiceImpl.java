package com.xiaozl.initialwork1.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiaozl.initialwork1.entity.User;
import com.xiaozl.initialwork1.mapper.UserMapper;
import com.xiaozl.initialwork1.service.UserService;

import java.util.Collections;
import java.util.List;

/**
 * @author xiaozl
 * @date 2017/11/20
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User newUser(User user) throws Exception {
        if (user == null || user.getUserName() == null || user.getPassword() == "") {
            return null;
        }

        try {
            //检查用户名是否重复
            List<User> userList= userMapper.listUser();
            if(userList != null){
                for(User u : userList){
                    if((u.getUserName()).equals(user.getUserName()))
                        return null;
                }
            }
            userMapper.newUser(user);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return user;
    }

    public boolean checkLogin(User user) throws Exception {
        if (user == null) {
            return false;
        }

        try {
            return userMapper.countByUserNameAndPassword(user.getUserName(), user.getPassword()) > 0 ? true : false;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<User> listUser() throws Exception {
        List<User> userList = Collections.emptyList();
        try {
            userList = userMapper.listUser();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return userList;
    }
}
