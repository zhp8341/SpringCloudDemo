package com.demo.springcloud.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springcloud.service.User;
import com.demo.springcloud.service.UserClient;

@RestController
public class UserController {

    @Autowired
    UserClient userClient;

    @RequestMapping(value = "/getuserinfo", method = RequestMethod.GET)
    public User getuserinfo() {
        return userClient.getuserinfo();
    }
    
    @RequestMapping(value = "/getuserinfostr", method = RequestMethod.GET)
    public String getuserinfostr() {
        return userClient.getuserinfostr();
    }
    
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return userClient.info();
    }


}