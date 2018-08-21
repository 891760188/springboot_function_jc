package ye.guo.huang.jwt.common.cache.redis;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ye.guo.huang.jwt.pojo.ExcelTest;
import ye.guo.huang.jwt.pojo.UUser;
import ye.guo.huang.jwt.service.ExcelTestService;
import ye.guo.huang.jwt.service.UUserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  加载初始化数据进缓存
 * @author 448249687@qq.com
 * @Date 2018/8/20
 *
 */

@Component
public class RedisCacheInit implements InitializingBean {

    private static final Logger LOGGER = LogManager.getLogger(RedisCacheInit.class);

    @Autowired
    private RedisService redisService;   //注入redis的service

    @Autowired
    private UUserService uUserService;

    @Autowired
    private ExcelTestService excelTestService;


    @Override
    public void afterPropertiesSet() throws Exception {
        LOGGER.info("-----初始化人员数据-----");
        List<UUser> userList = uUserService.findAll();
//        for (UUser user : userList){
//            LOGGER.info(user.toString());
//        }
        long a=System.currentTimeMillis();
//        redisService.remove("user");
        redisService.set("user", userList);
        LOGGER.info("-----初始化人员数据-----执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");

        LOGGER.info("-----初始化excel数据-----");
        a=System.currentTimeMillis();
        List<ExcelTest> excels = excelTestService.selectByExcelTest(new ExcelTest());
//        for (ExcelTest excel : excels){
//            LOGGER.info(excel.toString());
//        }
        redisService.set("excels", excels);
        LOGGER.info("-----初始化excel数据-----执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
    }
}
