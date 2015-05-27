import com.xw.test.service.HelloService;
import com.xw.test.service.HelloService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Author: wen.Xu
 * Date Time: 2014/10/29 11:20
 * Desc:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class DubboConsumerTest {
    private HelloService helloService;
    private HelloService2 helloService2;
    private HelloService2 helloService2_2;

    @Resource
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @Resource
    public void setHelloService2(HelloService2 helloService2) {
        this.helloService2 = helloService2;
    }

    @Resource
    public void setHelloService2_2(HelloService2 helloService2_2) {
        this.helloService2_2 = helloService2_2;
    }

    @Test
    public void test() throws Exception {
        System.out.println("helloService: " + helloService);
  /*      for(int i=0;i<10;i++){
        System.out.println("result: " + helloService.sayHello("zhangsan"));
        }*/
        for (int i = 0; i < 10; i++)
            System.out.println("result2: " + helloService2.sayHello("lishi"));
        for (int i = 0; i < 10; i++)
            System.out.println("result2: " + helloService2_2.sayHello("lishi"));
        Thread.sleep(60 * 1000);

    }
}

