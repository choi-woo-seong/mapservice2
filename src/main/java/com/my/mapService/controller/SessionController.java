package com.my.mapService.controller;

import com.my.mapService.dto.User;
import com.my.mapService.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class SessionController {
    private final UserServiceImpl userService;

    public SessionController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String loginProcess(User user, HttpSession session) {
        System.out.println("login Info : " + user);
        User tempUser = userService.findById(user.getUserId()).orElse(null);

        User sessionUser = tempUser;

        if (!ObjectUtils.isEmpty(sessionUser)) {
            if (user.getPassword().equals(sessionUser.getPassword())) {
                session.setAttribute("sessionInfo",sessionUser);
                session.setMaxInactiveInterval(60 * 30);
                return "home";
            } else {
                return "/users/login";
            }
        } else {
            return "/users/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "home";
    }
}
