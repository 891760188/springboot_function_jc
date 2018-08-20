package ye.guo.huang.jwt.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.pojo.StringDateConvert;

/**
 *
 * @author 448249687@qq.com
 * @Date 2018/8/20
 *  desc 参数绑定时将String时间转换为Date
 */
@Api(value="参数注入字符串转时间",tags={"参数注入字符串转时间-apis"})
@RestController
@RequestMapping("/api/stringDate")
public class StringDateConvertController {
    private static final Logger LOGGER = LogManager.getLogger(StringDateConvertController.class);

    @ApiOperation(value="参数注入字符串转时间", notes="参数注入字符串转时间")
    @ApiImplicitParam(paramType="dateStr",name = "dateStr", value = "date字符串时间", required = true ,dataType="String")
    @GetMapping("/str2Date.do")
    public ResponseBean str2Date(StringDateConvert stringDateConvert){
        LOGGER.info(stringDateConvert);
        return new ResponseBean(stringDateConvert);
    }



}
