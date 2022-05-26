package com.tegasus9.spring.test.service;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

/**
 * @author XiongYiGe
 * @date 2022/5/20
 * @description
 */
@Log4j2
@Data
public class UserService {

    private String uId;
    private String company;
    private String location;
    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }
}
