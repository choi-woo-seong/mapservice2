package com.my.mapService.config;

import com.my.mapService.dto.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyInterCeptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User currentSession = (User) session.getAttribute("sessionInfo");

        if(ObjectUtils.isEmpty(currentSession)){
            response.sendRedirect("/login");
            return false;
        } else {
            return true;
        }

    }
}
