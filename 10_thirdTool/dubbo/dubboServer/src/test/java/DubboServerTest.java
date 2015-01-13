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
public class DubboServerTest{
    private HelloService helloService;

    @Resource
    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @Test
    public void test() throws Exception {
        System.out.println("helloService: " + helloService);
        Thread.sleep(50 * 60 * 1000);
    }
}
