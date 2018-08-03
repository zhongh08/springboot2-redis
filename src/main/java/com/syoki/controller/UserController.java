package com.syoki.controller;

import com.syoki.model.User;
import com.syoki.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    public User getUser(){
        User user = userService.getUser("xianggu");
        return user;
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(){
        userService.deleteUser("xianggu");
        return "执行了删除";
    }


}
