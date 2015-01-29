package com.xw.test.service;

import com.xw.test.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Author: wen.Xu
 * Date Time: 2014/10/29 11:16
 * Desc:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class DubboServerTest2 {
    private HelloService2 helloService2;

    @Resource
    public void setHelloService2(HelloService2 helloService2) {
        this.helloService2 = helloService2;
    }

    @Test
    public void test() throws Exception {
        System.out.println("helloService2: " + helloService2);
        Thread.sleep(50 * 60 * 1000);
    }
}
