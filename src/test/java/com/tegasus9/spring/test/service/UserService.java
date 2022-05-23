package com.tegasus9.spring.test.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
@Log4j2
public class UserService {

    private UserDao userDao;
    @Getter
    @Setter
    private String uId;
    public void queryUserInfo(){
        log.info("查询用户名称：uid:{},名称：{}",uId,userDao.queryUserName(uId));
    }

    public UserService(String uId){
        this.uId = uId;
    }
}
