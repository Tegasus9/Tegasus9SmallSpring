package com.tegasus9.spring.test.service;


import lombok.Getter;
import lombok.Setter;

/**
 * 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 * 公众号：bugstack虫洞栈
 * Create by 小傅哥(fustack)
 */
@Getter
@Setter
public class UserService  {
    private String uId;
    private String company;
    private String location;
    private IUserDao userDao;
    public String queryUserInfo() {
        return userDao.queryUserName(uId) + ",company: " + company + ",location: " + location;
    }


}
