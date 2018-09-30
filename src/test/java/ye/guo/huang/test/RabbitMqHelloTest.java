package ye.guo.huang.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ye.guo.huang.jwt.common.rabbit.hello.HelloSender;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=RabbitMqHelloTest.class)
public class RabbitMqHelloTest {

//    @Autowired
//    private HelloSender helloSender;
//
//    @Test
//    public void hello(){
//        helloSender.send();
//    }

}
