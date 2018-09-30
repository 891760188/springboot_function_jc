package ye.guo.huang.jwt.common.rabbit.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {

    /**
     * rabbitTemplate是springboot 提供的默认实现
     */
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(String name,int times) {

		//hello是队列通道，context是消息内容
        for(int i = 0 ; i < times ;i++){
            String context =i + "="+name+ "="+new Date();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Sender : " + context);
            this.rabbitTemplate.convertAndSend("hello", context);
        }

	}

}