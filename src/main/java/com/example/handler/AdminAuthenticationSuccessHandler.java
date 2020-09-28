package com.example.handler;

import com.example.domain.SecurityUser;
import com.example.dto.ApiResult;
import com.example.dto.LoginDto;
import com.example.util.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 认证成功处理实现类
 */
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication auth) throws IOException, ServletException {
        LoginDto dto = new LoginDto();
        SecurityUser securityUser = ((SecurityUser) auth.getPrincipal());
        dto.setMenus(getMenus());
        dto.setToken(securityUser.getCurrentUserInfo().getToken());
        ResponseUtils.out_new(httpServletResponse, dto);
    }

    //测试菜单
    Map<String, String> getMenus() {
        Map<String, String> map = new HashMap();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        return map;
    }
}
