package ye.guo.huang.jwt.common.schdule;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ye.guo.huang.jwt.common.token.Token;
import ye.guo.huang.jwt.common.token.TokenCache;
import ye.guo.huang.jwt.common.util.DateUtils;

import java.util.Date;

/**
 *  token的定时任务
 * @author 448249687@qq.com
 * @Date 2018/8/4
 *
 */

@Component
@EnableScheduling
public class TokenScheduled {

    private static final Logger LOGGER = LogManager.getLogger(TokenScheduled.class);

    /**
     * 没五秒钟执行一次
     */
//    @Scheduled(cron="0 */1 * * * ?")
    @Scheduled(cron="*/5000 * * * * ?")
    public void executeFileDownLoadTask() {

        Thread current = Thread.currentThread();
        LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>任务编号current.getId()="+current.getId()+" 定时任务开始=" + DateUtils.formatDateToString(new Date(),DateUtils.DATE_FORMAT_FULL));

        for (String key : TokenCache.getCurrentHeap().keySet()) {
            Token token = TokenCache.getToken(key);
            if (token == null) {
                TokenCache.removeToken(key);
            } else {
                Long now = System.currentTimeMillis();
                Long create = token.getCreateTime().getTime();
                boolean timeOut = token.getDeadline() < (now - create);
                if (timeOut) {
                    LOGGER.info(token.getUserName() + "---------------------------------");
                    TokenCache.removeToken(key);
                }
            }

        }

//        LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<任务编号current.getId()="+current.getId()+" 定时任务结束=" + DateUtils.formatDateToString(new Date(),DateUtils.DATE_FORMAT_FULL));

    }

}
