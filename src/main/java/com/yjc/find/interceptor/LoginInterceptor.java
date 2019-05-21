package com.yjc.find.interceptor;

import com.yjc.find.base.constant.Constant;
import com.yjc.find.pojo.User;
import com.yjc.find.utils.JwtUtil;
import com.yjc.find.utils.MyUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    protected Logger logger= LoggerFactory.getLogger(this.getClass());

    /**
     * 进去前
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        logger.debug("==============================preHandle");
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }
//        .excludePathPatterns("**/login**","*/register","*/reset")
        if(request.getRequestURI().endsWith("/login")){
            return true;
        }

        String authHeader = request.getHeader("authorization");
        if (authHeader == null || !authHeader.startsWith("bearer;")) {
            MyUtil.checkFailed("身份验证错误");
        }

        String token = authHeader.substring(7);
        Claims claims= JwtUtil.parseJWT(token, Constant.BASE64_SECURITY);
        MyUtil.checkNull(claims,"请登录后访问");
        long id=MyUtil.getLong(claims.get("id"));
        MyUtil.check(id!=0,"请登录后访问");
        User user = new User();
        user.setId(id);
        user.setUsername(MyUtil.ObjectToString(claims.get("username")));
        user.setUserType(MyUtil.getInt(claims.get("userType")));
        logger.info("用户{}:{}", id ,user);
        JwtUtil.setUser(user);
        return true;
    }

    /**
     * 完成后
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.debug("==============================afterCompletion");
        JwtUtil.removeUser();
    }
}
