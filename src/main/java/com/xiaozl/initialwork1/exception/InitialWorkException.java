package com.xiaozl.initialwork1.exception;

/**
 * Created by user on 2017/12/8.
 * 异常枚举类
 */
public enum InitialWorkException {

    NewUserFailed("添加用户失败",1001),
    CountByUserNameAndPasswordFailed("查找用户失败",1002),
    ListUserFailed("查询用户列表失败",1003),
    DelUserByIdFailed("删除用户失败",1004),
    QueryUserByIdFailed("根据id查询用户失败",1005),
    UpdateUserFailed("修改用户失败",1006),
    UserIdError("用户ID不合法",1007),
    FileNotFound("文件找不到啦",1008),
    ;

    private String message;
    private int code;

     InitialWorkException(String message, int code){
        this.message = message;
        this.code = code;
    }
    public String getMessage(){
         return message;
    }
    public int getCode(){
        return code;
    }
}
