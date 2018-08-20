package ye.guo.huang.jwt.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.pojo.StringDateConvert;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author 448249687@qq.com
 * @Date 2018/8/4
 *
 */
@Api(value="reids的tringRedisTemplate使用",tags={"redies-apis"})
@RestController
@RequestMapping("/api/reids")
public class StringRedisTemplateController {

    private static final Logger LOGGER = LogManager.getLogger(StringRedisTemplateController.class);

    @Autowired
    private StringRedisTemplate redisTemplate ;

    //注入RedisTemplate对象
    @Autowired
    private RedisTemplate<String, Object> objRedisTemplate;

    @ApiOperation(value="reidsStringRedisTemplate", notes="reidsStringRedisTemplate")
    @GetMapping("/set.test")
    public ResponseBean set() {
        //设置缓存，建议每个键都设置过期时间
        redisTemplate.opsForValue().set("test", "test", 1000000, TimeUnit.SECONDS);
        //获取缓存值
        String value = redisTemplate.opsForValue().get("test");
        //删除某个键
        redisTemplate.delete("test");
        //操作set
        redisTemplate.opsForSet().add("testSet", "1","2","t");
        Set<String> members = redisTemplate.opsForSet().members("testSet");//获取set内的所有值
        redisTemplate.opsForSet().remove("testSet", "1","2");//移除set内的多个对象
        members = redisTemplate.opsForSet().members("testSet");//获取set内的所有值
        //操作list
        redisTemplate.opsForList().rightPush("testList", "1");
        List<String> list = redisTemplate.opsForList().range("testList", 0, -1);//获取list内的所有元素

        return new ResponseBean();
    }


    @ApiOperation(value="RedisTemplate<String, Object>", notes="RedisTemplate<String, Object>")
    @GetMapping("/set2.test")
    public ResponseBean set2(StringDateConvert stringDateConvert) {
        objRedisTemplate.opsForValue().set("stringDateConvert", stringDateConvert,10,TimeUnit.SECONDS);
        stringDateConvert = (StringDateConvert) objRedisTemplate.opsForValue().get("stringDateConvert");
        return new ResponseBean(stringDateConvert);

    }

}
