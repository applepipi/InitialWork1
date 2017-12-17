package com.xiaozl.initialwork1.service.impl;

import com.xiaozl.initialwork1.exception.BusinessException;
import com.xiaozl.initialwork1.exception.InitialWorkException;
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

    public User newUser(User user) throws BusinessException {
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
            //int i = 1/0;//异常测试
        } catch (Exception e) {
            throw new BusinessException(InitialWorkException.NewUserFailed.getMessage(),e);
        }
        return user;
    }

    public boolean checkLogin(User user) throws BusinessException {
        if (user == null) {
            return false;
        }

        try {
            return userMapper.countByUserNameAndPassword(user.getUserName(), user.getPassword()) > 0 ? true : false;
        } catch (Exception e) {
            throw new BusinessException(InitialWorkException.CountByUserNameAndPasswordFailed.getMessage(),e);
        }
    }

    public List<User> listUser() throws BusinessException {
        List<User> userList = Collections.emptyList();
        try {
            userList = userMapper.listUser();
        }catch (Exception e){
            throw new BusinessException(InitialWorkException.ListUserFailed.getMessage(),e);
        }
        return userList;
    }

    public boolean delUser(Integer userId) throws Exception {
        if(userId == null || userId <= 0){
            throw new BusinessException(InitialWorkException.UserIdError.getMessage());
        }
        try {
            userMapper.delUser(userId);
        }catch (Exception e){
            throw new BusinessException(InitialWorkException.DelUserByIdFailed.getMessage(),e);
        }
        return true;
    }

    public User queryById(Integer userId) throws BusinessException {
        User user = null;
        if(userId == null || userId <= 0){
            throw new BusinessException(InitialWorkException.UserIdError.getMessage());
        }
        try {
            user = userMapper.queryById(userId);
        }catch (Exception e){
            throw new BusinessException(InitialWorkException.QueryUserByIdFailed.getMessage(),e);
        }
        return user;
    }

    public boolean updateUser(User user) throws BusinessException {
        if(user == null || user.getId() == null || user.getId() <= 0 || user.getUserName() == null || "".equals(user.getUserName()) || user.getPassword() == null || "".equals(user.getPassword())){
            return false;
        }
        try{
            userMapper.updateUser(user);
        }catch (Exception e){
            throw new BusinessException(InitialWorkException.UpdateUserFailed.getMessage(),e);
        }
        return true;
    }
}
