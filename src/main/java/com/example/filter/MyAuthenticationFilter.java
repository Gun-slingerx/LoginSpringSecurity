package com.example.filter;

import com.example.config.AdminAuthenticationEntryPoint;
import com.example.config.Constants;
import com.example.domain.SecurityUser;
import com.example.service.impl.UserDetailsServiceImpl;
import com.example.util.MultiReadHttpServletRequest;
import com.example.util.MultiReadHttpServletResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 自定义过滤器 MyAuthenticationFilter 继承 OncePerRequestFilter 实现访问鉴权
 */
@Slf4j
@Component
public class MyAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    AdminAuthenticationEntryPoint authenticationEntryPoint;

    private final UserDetailsServiceImpl userDetailsService;

    protected MyAuthenticationFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("请求头类型： " + request.getContentType());
        if ((request.getContentType() == null && request.getContentLength() > 0) || (request.getContentType() != null && !request.getContentType().contains(Constants.REQUEST_HEADERS_CONTENT_TYPE))) {
            filterChain.doFilter(request, response);
            return;
        }

        MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
        MultiReadHttpServletResponse wrappedResponse = new MultiReadHttpServletResponse(response);
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            logRequestBody(wrappedRequest);
            String jwtToken = wrappedRequest.getHeader(Constants.REQUEST_HEADER);
            log.debug("后台检查令牌:{}", jwtToken);
            if (StringUtils.isNotBlank(jwtToken)) {
                // JWT相关start ===========================================
                // 获取jwt中的信息
                Claims claims = Jwts.parser().setSigningKey(Constants.SALT).parseClaimsJws(jwtToken.replace("Bearer", "")).getBody();
                // 获取当前登录用户名
                System.out.println("获取当前登录用户名: " + claims.getSubject());
                // TODO 如需使用jwt特性在此做处理~
                // JWT相关end ===========================================

                String userName = claims.getSubject();
                //通过用户名获取用户信息
                SecurityUser securityUser = (SecurityUser) userDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
                // 全局注入角色权限信息和登录用户基本信息
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } catch (ExpiredJwtException e) {
            // jwt令牌过期
            SecurityContextHolder.clearContext();
            this.authenticationEntryPoint.commence(wrappedRequest, response, null);
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            this.authenticationEntryPoint.commence(wrappedRequest, response, e);
        } finally {
            stopWatch.stop();
            long usedTimes = stopWatch.getTotalTimeMillis();
            // 记录响应的消息体
            logResponseBody(wrappedRequest, wrappedResponse, usedTimes);
        }
    }


    private String logRequestBody(MultiReadHttpServletRequest request) {
        MultiReadHttpServletRequest wrapper = request;
        if (wrapper != null) {
            try {
                String bodyJson = wrapper.getBodyJsonStrByJson(request);
                String url = wrapper.getRequestURI().replace("//", "/");
                System.out.println("-------------------------------- 请求url: " + url + " --------------------------------");
                Constants.URL_MAPPING_MAP.put(url, url);
                log.info("`{}` 接收到的参数: {}", url, bodyJson);
                return bodyJson;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void logResponseBody(MultiReadHttpServletRequest request, MultiReadHttpServletResponse response, long useTime) {
        MultiReadHttpServletResponse wrapper = response;
        if (wrapper != null) {
            byte[] buf = wrapper.getBody();
            if (buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    payload = "[unknown]";
                }
                log.info("`{}`  耗时:{}ms  返回的参数: {}", Constants.URL_MAPPING_MAP.get(request.getRequestURI()), useTime, payload);
            }
        }
    }
}
