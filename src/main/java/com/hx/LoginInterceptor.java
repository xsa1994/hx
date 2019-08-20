package com.hx;

import com.hx.cookie.RequestLocal;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by huangch on 2019/8/9 17:43
 * description:
 *
 * @since JDK 1.6
 */
@Component
public class LoginInterceptor implements HandlerInterceptor, InitializingBean {

    private Logger log = LoggerFactory.getLogger(LoginInterceptor.class);


    private static List<String> white_request_url = Lists.newArrayList();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestLocal.clear();
        RequestLocal.get().setRequest(request);
        RequestLocal.get().setResponse(response);

        Long accountId = RequestLocal.get().getAccountId();
        String path = request.getServletPath();

//        if (path.startsWith("/webjars") || path.startsWith("/swagger") || path.startsWith("/root")
//                || path.startsWith("/images") || white_request_url.contains(path) || path.startsWith("/open") ||
//                path.startsWith("/error")) {
//            return true;
//        } else if (accountId != null) {
//            if ("/login".equals(path) || "/logout".equals(path)) {
//                // 登录过且未失效, 再重新登录的话重定向到首页
//                response.sendRedirect("/page/index.html");
//                return false;
//            }
//        } else {
//            log.warn("login is expire, please login again!");
//            response.sendRedirect("/page/login.html");
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        white_request_url.add("/");
        white_request_url.add("/private");
        white_request_url.add("/index.html");
        white_request_url.add("/favicon.ico");
        white_request_url.add("/login");
        white_request_url.add("/logout");

        // swagger
        white_request_url.add("/swagger-ui.html");
        white_request_url.add("/swagger-resources");
        white_request_url.add("/v2/api-docs");

        // 上传图片
        white_request_url.add("/upload/image");

    }
}
