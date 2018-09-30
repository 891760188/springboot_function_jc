package ye.guo.huang.jwt.common.argumentResolver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class ParameterArgVoArgumentResolver implements HandlerMethodArgumentResolver {

    private static final Logger LOGGER = LogManager.getLogger(ParameterArgVoArgumentResolver.class);

    /**
     *  这个参数是否需要做处理
     * @param methodParameter 可用获取参数的一些信息
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        Class<?> clazz = methodParameter.getParameterType();
        boolean flag = clazz == ParameterArgVo.class ;
        if(flag){
            LOGGER.info(methodParameter.getParameterName());
        }
        return flag;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
        ParameterArgVo parameterArgVo = new ParameterArgVo();
        parameterArgVo.setId(1);
        parameterArgVo.setName("名字");
        parameterArgVo.setCrtDt(new Date());
        return parameterArgVo;
    }
}
