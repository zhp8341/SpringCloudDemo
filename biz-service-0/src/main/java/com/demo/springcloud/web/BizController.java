package com.demo.springcloud.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.springcloud.bean.User;

@RestController
public class BizController {

    private final Logger    logger = LoggerFactory.getLogger(BizController.class);

    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return "success";
    }

    @RequestMapping(value = "/getuser", method = RequestMethod.GET)
    public User add() {
        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("/getuser, host:" + instance.getHost() + ", service_id:" + instance.getServiceId());
        User usr = new User();
        usr.setName("ccblog.cn");
        usr.setSex(1);
        return usr;
    }

}
