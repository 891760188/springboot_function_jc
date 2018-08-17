package ye.guo.huang.jwt.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.service.ExcelTestService;

/**
 *
 * @author 448249687@qq.com
 * @Date 2018/8/16
 *
 */

@RestController
@RequestMapping("/api/excel")
public class ExcelTestController {

    private static final Logger LOGGER = LogManager.getLogger(ExcelTestController.class);

    @Autowired
    private ExcelTestService excelTestService;

    @GetMapping("/importDatas.test")
    public ResponseBean importDatas() {
        long time = excelTestService.importDatas();
        return ResponseBean.response(time);
    }
    @GetMapping("/updateDatas.test")
    public ResponseBean importUpdateDatas() {
        long time = excelTestService.importUpdateDatas();
        return ResponseBean.response(time);
    }
    @GetMapping("/updateDatasMap.test")
    public ResponseBean importUpdateDatasMap() {
       String msg = excelTestService.importUpdateDatasMap();
        return ResponseBean.response(msg);
    }
}
