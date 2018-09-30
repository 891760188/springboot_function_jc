package ye.guo.huang.jwt.common.argumentResolver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ye.guo.huang.jwt.common.ResponseBean;
import ye.guo.huang.jwt.common.util.DateUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/param")
public class ParameterArgVoController {

    private static final Logger LOGGER = LogManager.getLogger(ParameterArgVoController.class);

    @RequestMapping(value = "/test01.arg",method = RequestMethod.GET)
    public ResponseBean test01 (@RequestParam(value = "name",required = false) String name, HttpServletRequest request , HttpServletResponse response ,ParameterArgVo parameterArgVo){

        Cookie [] cookies = request.getCookies();
        LOGGER.info(cookies);

        Cookie cookie = new Cookie("aa","我是aa");//+DateUtils.formatDateToString(new Date(),DateUtils.DATE_FORMAT_FULL)
        cookie.setMaxAge(10000);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseBean.response("success");
    }

}
