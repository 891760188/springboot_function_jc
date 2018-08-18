package ye.guo.huang.jwt.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.service.AsyncService;

/**
 *
 * @author 448249687@qq.com
 * @Date 2018/8/17
 *  desc 线程池的使用
 */

@RestController
@RequestMapping("/api/async")
public class AsyncController {

    private static final Logger LOGGER = LogManager.getLogger(AsyncController.class);

    @Autowired
    private AsyncService asyncService ;

    @RequestMapping("/async.async")
    public ResponseBean executeAsync(){
       asyncService.executeAsync();
        return ResponseBean.response(true);
    }


}
