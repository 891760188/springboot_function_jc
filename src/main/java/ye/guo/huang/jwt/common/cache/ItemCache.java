package ye.guo.huang.jwt.common.cache;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ye.guo.huang.jwt.common.util.StringUtils;
import ye.guo.huang.jwt.mapper.SysDataItemMapper;
import ye.guo.huang.jwt.pojo.SysDataItem;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  项目启动，数据字典加载进缓存
 * @author 448249687@qq.com
 * @Date 2018/8/6
 *
 */

@Component
public class ItemCache implements CommandLineRunner {

    private static final Logger LOGGER = LogManager.getLogger(ItemCache.class);

    @Autowired
    private SysDataItemMapper sysDataItemMapper ;

    private Map<String, ConcurrentHashMap<String, String>> lookupItemCache;

    @Override
    public void run(String... strings) throws Exception {
        long beginTime = System.currentTimeMillis();
        LOGGER.info("开始加载数据字典---------------------------------------------------------------------------------------");
        List<SysDataItem> sysDataItemList = sysDataItemMapper.findAll();
        if(null != sysDataItemList){
            lookupItemCache = new ConcurrentHashMap<String, ConcurrentHashMap<String, String>>();
            for (SysDataItem item : sysDataItemList) {
                String cdId = item.getCdId() + "";
                if(!StringUtils.isBlank(cdId)){
                    ConcurrentHashMap<String, String> itemMap = lookupItemCache.get(cdId);
                    if(null == itemMap){
                        itemMap = new ConcurrentHashMap<String, String>();
                    }
                    String cdValId = item.getCdValId() + "";
                    String cdValName = item.getCdValName() ;
                    if(StringUtils.isNotEmpty(cdValId) && StringUtils.isNotEmpty(cdValName)){
                        itemMap.put(cdValId,cdValName);
                        LOGGER.info(item.getCdName() + "-->>>" + cdValName );
                    }
                    lookupItemCache.put(cdId,itemMap);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long seconds = ( endTime - beginTime ) / 1000 ;
        LOGGER.info("结束加载数据字典，共用时间"+seconds+"秒-------------------------------------------------------------------");
    }

    /**
     *  获取数据字典对象
     * @return
     */
    public synchronized Map<String, ConcurrentHashMap<String, String>> getLookupItemCache(){
        return lookupItemCache;
    }
}
