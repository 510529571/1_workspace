package com.xw.test.service.impl;

import com.xw.test.service.HelloService2;

/**
 * Author: wen.Xu
 * Date Time: 2014/10/29 10:38
 * Desc:
 */
public class HelloService2Impl implements HelloService2 {

    @Override
    public String sayHello(String str) {
        System.out.println("server receive :" + str);
        return "hello to2 " + str;
    }
}
