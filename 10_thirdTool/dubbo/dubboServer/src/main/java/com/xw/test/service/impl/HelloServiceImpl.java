package com.xw.test.service.impl;

import com.xw.test.service.HelloService;

/**
 * Author: wen.Xu
 * Date Time: 2014/10/29 10:38
 * Desc:
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String str) {
        System.out.println("server receive :" + str);
        return "hello to " + str;
    }
}
