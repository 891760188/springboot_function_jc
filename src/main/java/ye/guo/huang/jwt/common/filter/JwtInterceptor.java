package ye.guo.huang.jwt.common.filter;

import io.jsonwebtoken.SignatureException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import ye.guo.huang.jwt.common.exception.UnauthorizedException;
import ye.guo.huang.jwt.common.token.Token;
import ye.guo.huang.jwt.common.token.TokenCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Enumeration;

/**
 *  过滤器 拦截器
 * @author 448249687@qq.com
 * @Date 2018/8/4
 *
 */

public class JwtInterceptor implements HandlerInterceptor{

    private static final Logger LOGGER = LogManager.getLogger(JwtInterceptor.class);

        /**
         * 请求之前
         * @param request 请求对象
         * @param response 返回对象
         * @param o
         * @return
         * @throws Exception
         */
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
            LOGGER.info("preHandle");
            if(request.getRequestURI().endsWith(".json")){
                //其他请求获取头信息
                String authHeader = request.getHeader("token");
                //如果没有header信息
                if (authHeader == null || authHeader.trim() == "") {
                    throw new UnauthorizedException("not jwt");
                }
                Token token = TokenCache.getToken(authHeader) ;
                if(null == token){
                    throw new UnauthorizedException("not token");
                }
               if ( new Date().getTime() - token.getCreateTime().getTime() > token.getDeadline()){
                   throw new UnauthorizedException("token time out");
               }
               token.setCreateTime(new Date());
                return true;
            }
            return true ;

        }
        /**
         * 根据传入的类型获取spring管理的对应dao
         * @param clazz 类型
         * @param request 请求对象
         * @param <T>
         * @return
         */
        private <T> T getDAO(Class<T> clazz,HttpServletRequest request)
        {
            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
            return factory.getBean(clazz);
        }

        @Override
        public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        }

        @Override
        public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        }
    }
