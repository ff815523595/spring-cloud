package com.controll;

import com.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by a on 2017/11/21.
 */
@RestController
public class HelloControler {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/hi")
    public String hello(@RequestParam String name){
        return helloService.hiService(name);
    }
}
