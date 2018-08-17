package ye.guo.huang.jwt.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ye.guo.huang.jwt.mapper.ExcelTestMapper;
import ye.guo.huang.jwt.mapper.ExcelTestTempMapper;
import ye.guo.huang.jwt.pojo.ExcelTest;
import ye.guo.huang.jwt.pojo.ExcelTestTemp;
import ye.guo.huang.jwt.service.ExcelTestService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelTestServiceImpl implements ExcelTestService {

    private static final Logger LOGGER = LogManager.getLogger(ExcelTestServiceImpl.class);

    @Resource
    private ExcelTestTempMapper excelTestTempMapper;

    @Resource
    private ExcelTestMapper excelTestMapper ;

    @Override
    public synchronized long importDatas() {
        long starTime = System.currentTimeMillis();
        List<ExcelTestTemp> list = new ArrayList<ExcelTestTemp>();
        for(int i = 1 ; i <= 10000 ;i++){
            ExcelTestTemp temp = new ExcelTestTemp("名字"+i,String.valueOf(i));
            list.add(temp);
        }
//        int dels = excelTestTempMapper.deleteAll();
        int inserts = excelTestTempMapper.insertBatch(list);
        long endTime = System.currentTimeMillis();
//        LOGGER.info("dels="+dels);
        LOGGER.info("inserts="+inserts);
        LOGGER.info("costTime+"+(endTime - starTime));
        return endTime - starTime;
    }

    @Override
    @Transactional
    public synchronized long importUpdateDatas() {
        long starTime = System.currentTimeMillis();
        //组织将要用来更新的数据
        List<ExcelTestTemp> list = new ArrayList<ExcelTestTemp>();
        for(int i = 14000 ; i <= 24000 ;i++){
            ExcelTestTemp temp = new ExcelTestTemp("名字"+i,String.valueOf(i));
            list.add(temp);
        }
        //先清空临时表数据
        int dels = excelTestTempMapper.deleteAll();
        LOGGER.info("dels="+dels+"  costTime="+(System.currentTimeMillis() - starTime));
        //插数据进临时表
        long starTime2 = System.currentTimeMillis();
        int inserts = excelTestTempMapper.insertBatch(list);
        LOGGER.info("inserts="+inserts+"  costTime="+(System.currentTimeMillis() - starTime2));
        //更新有问题数据的remark字段
        long starTime3 = System.currentTimeMillis();
        int errors = excelTestTempMapper.updateErrMsg();
        LOGGER.info("errors="+errors+"  costTime="+(System.currentTimeMillis() - starTime3));
        //插入数据
        long starTime4 = System.currentTimeMillis();
        int updateInsert = excelTestMapper.insertCheck();
        LOGGER.info("updateInsert="+updateInsert+"  costTime="+(System.currentTimeMillis() - starTime4));
        long endTime = System.currentTimeMillis();
        //查出问题数据
        long starTime5 = System.currentTimeMillis();
        List<ExcelTestTemp> errList =excelTestTempMapper.selectErrList();
        LOGGER.info("costTime+"+(endTime - starTime));
        return endTime - starTime;
    }

    @Override
    @Transactional
    public synchronized String importUpdateDatasMap() {

        long starTime = System.currentTimeMillis();
        //组织将要用来更新的数据
        List<ExcelTest> dataList = new ArrayList<ExcelTest>();
        for(int i = 9000 ; i <= 19000 ;i++){
            ExcelTest temp = new ExcelTest("名字"+i,String.valueOf(i));
            dataList.add(temp);
        }
        LOGGER.info("造数据所用时间  costTime="+(System.currentTimeMillis() - starTime));

        //查询所有数据
        long starTime2 = System.currentTimeMillis();
        ExcelTest excelTest = new ExcelTest();
        List<ExcelTest> excelTestList = excelTestMapper.selectByExcelTest(excelTest);
        LOGGER.info("查已经存在数据所用时间  costTime="+(System.currentTimeMillis() - starTime2));

        //数据对比
        long starTime3 = System.currentTimeMillis();
        Map<String,Integer> dataMap = new HashMap<String,Integer>();
        for (int i = 0,len = excelTestList.size() ; i < len ; i++){
            dataMap.put(excelTestList.get(i).getCard() , i) ;
        }
        List<ExcelTest> okList = new ArrayList<ExcelTest>();
        List<String> errList = new ArrayList<String>();
        for (int i = 0 ,len = dataList.size() ; i < len ; i++ ){
            ExcelTest temp = dataList.get(i);
            if(dataMap.get(temp.getCard()) == null){
                okList.add(temp);
            }else {
                errList.add(temp.getName() + " 身份证已经存在");
            }
        }
        LOGGER.info("对比数据所用时间  costTime="+(System.currentTimeMillis() - starTime3));
        //插入数据
        long starTime4 = System.currentTimeMillis();
        int ins = 0;
        if(okList.size() != 0){
            ins = excelTestMapper.insertBatch(okList);
        }
        LOGGER.info("ins="+ins+"  插入数据所用时间  costTime="+(System.currentTimeMillis() - starTime4));
        return "成功数="+ins + "   失败数="+errList.size() + errList.toString();
    }


}
