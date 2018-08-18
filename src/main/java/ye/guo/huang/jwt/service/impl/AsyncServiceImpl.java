package ye.guo.huang.jwt.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ye.guo.huang.jwt.service.AsyncService;

/**
 *
 * @author 448249687@qq.com
 * @Date 2018/8/17
 *  desc:线程池的使用
 */

@Service
public class AsyncServiceImpl implements AsyncService {

    private static final Logger LOGGER = LogManager.getLogger(AsyncServiceImpl.class);

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        LOGGER.info("start executeAsync");
        try{
            Thread.sleep(3000);
        }catch(Exception e){
            e.printStackTrace();
        }
        LOGGER.info("end executeAsync");
    }
}
