package com.tegasus9.spring.test.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author XiongYiGe
 * @date 2022/5/23
 * @description
 */
public class UserDao {
    private  static Map<String,String> hashMap = new HashMap<>();
    public void initDataMethod(){
        System.out.println("执行：userDao初始化方法");
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }

    public void destroyDataMethod(){
        System.out.println("执行：UserDao摧毁方法");
        hashMap.clear();
    }
    public String queryUserName(String id){
        return hashMap.get(id);
    }

    @Override
    public String toString() {
        return hashMap.toString();
    }
}
