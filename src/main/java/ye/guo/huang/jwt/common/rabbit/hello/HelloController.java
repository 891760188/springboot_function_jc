package ye.guo.huang.jwt.common.rabbit.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloSender helloSender ;

    @RequestMapping(value = "/test01",method = RequestMethod.GET)
    public String test01(@RequestParam(value = "name",defaultValue = "zshello") String name ,
                         @RequestParam(value = "times",defaultValue = "10") int times ){
        helloSender.send(name,times);
        return name ;
    }

}
