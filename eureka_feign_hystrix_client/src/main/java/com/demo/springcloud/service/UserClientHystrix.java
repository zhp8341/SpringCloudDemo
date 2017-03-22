
package com.demo.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public class UserClientHystrix implements UserClient {

    @Override
    public User getuserinfo() {
        throw new NullPointerException(" User getuserinfo() 服务不可用。。");
    }

    @Override
    public String getuserinfostr() {

        return " UserClientHystrix getuserinfostr() is fallback 服务不可用。。";
    }

    @Override
    public String info() {

        return " UserClientHystrix info() is fallback 服务不可用。。";
    }

}
