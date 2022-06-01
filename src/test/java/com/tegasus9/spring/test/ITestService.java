package com.tegasus9.spring.test;

/**
 * @author XiongYiGe
 * @date 2022/5/31
 * @description
 */
public interface ITestService {
    void canYouImplementMe();

    public static void main(String[] args) {
        ITestService testChildService = new TestChildService();
        testChildService.canYouImplementMe();
    }
}
