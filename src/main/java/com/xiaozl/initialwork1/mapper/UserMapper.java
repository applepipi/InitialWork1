package com.xiaozl.initialwork1.mapper;

import org.apache.ibatis.annotations.Param;

import com.xiaozl.initialwork1.entity.User;

import java.util.List;

/**
 * @author xiaozl
 * @date 2017/11/20
 */
public interface UserMapper {

    /**
     * 新增一条用户记录
     *
     * @param user
     * @throws Exception
     */
    public void newUser(@Param("user") User user) throws Exception;

    /**
     * 根据userName和password查询数据库中是否存在该用户
     *
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    public int countByUserNameAndPassword(@Param("userName") String userName,
                                          @Param("password") String password) throws Exception;

    /**
     * 查询所有用户
     *
     * @return
     * @throws Exception
     */
    public List<User> listUser() throws Exception;

    /**
     * 删除用户
     *
     * @param userId
     * @throws Exception
     */
    public void delUser(@Param("userId") Integer userId) throws Exception;

    /**
     * 根据id查询用户
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public User queryById(@Param("userId") Integer userId) throws Exception;

    /**
     * 修改用户
     *
     * @param user
     * @throws Exception
     */
    public void updateUser(@Param("user")User user) throws Exception;

}
