package ye.guo.huang.jwt;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.cache.RedisCacheManager;

@SpringBootApplication
@MapperScan("ye.guo.huang.jwt.mapper")//mybaits扫描
//@EnableCaching // 启动缓存-redis
public class JwtApplication {
    private static Logger logger = LoggerFactory.getLogger(JwtApplication.class);


    public static void main(String[] args) {
        logger.info("Start CacheApplication.. ");
        SpringApplication.run(JwtApplication.class, args);
    }

    /**
     * 重新配置RedisCacheManager
     * @param rd
     */
//    @Autowired
//    public void configRedisCacheManger(RedisCacheManager rd){
//        rd.setDefaultExpiration(100L);
//    }

}
