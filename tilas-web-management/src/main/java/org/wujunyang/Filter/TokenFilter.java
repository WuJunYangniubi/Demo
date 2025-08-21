package org.wujunyang.Filter;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.wujunyang.Utils.JwtUtils;
import org.wujunyang.Utils.ThreadLocalUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI =request.getRequestURI();


        if(requestURI.contains("/login")){
            log.info("登录请求");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String token = request.getHeader("token");

        if(token == null || token.isEmpty()){
            log.info("token为空");
            response.setStatus(401);
            return;
        }
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Object id = claims.get("id");
            ThreadLocalUtil.setUserId(id.toString());
        } catch (Exception e) {
            log.info("token验证失败");
            response.setStatus(401);
            return;
        }

        log.info("token验证成功");

        filterChain.doFilter(servletRequest,servletResponse);
        ThreadLocalUtil.removeUserId();
    }
}
