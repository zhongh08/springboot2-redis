package com.syoki.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class SessionController {

    @RequestMapping("/putSession")
    public String putSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.getAttribute("session data");
        return "ok";
    }

}
