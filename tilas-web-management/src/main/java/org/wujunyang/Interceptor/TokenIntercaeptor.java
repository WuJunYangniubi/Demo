//package org.wujunyang.Interceptor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.wujunyang.Utils.JwtUtils;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//@Slf4j
////@Component
//public class TokenIntercaeptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI =request.getRequestURI();
//        if(requestURI.contains("/login")){
//            log.info("登录请求");
//            return true;
//        }
//        String token = request.getHeader("token");
//
//        if(token == null || token.isEmpty()){
//            log.info("token为空");
//            response.setStatus(401);
//            return false;
//        }
//        try {
//            JwtUtils.parseJWT(token);
//        } catch (Exception e) {
//            log.info("token验证失败");
//            response.setStatus(401);
//            return false;
//        }
//
//        log.info("token验证成功");
//
//        return true;
//    }
//}
