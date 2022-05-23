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

    @Getter
    @Setter
    private String name;
    public void systemOut(){
        log.info("\"Hello Spring!\"");
    }

    public UserService(String name){
        this.name= name;
    }
}
