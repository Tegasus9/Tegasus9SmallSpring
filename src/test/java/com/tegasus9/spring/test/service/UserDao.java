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
    static {
        hashMap.put("10001", "小傅哥");
        hashMap.put("10002", "八杯水");
        hashMap.put("10003", "阿毛");
    }
    public String queryUserName(String id){
        return hashMap.get(id);
    }
}
