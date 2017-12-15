package com.controll;

import com.service.service0.SchedualServiceHi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by a on 2017/11/22.
 */
@RestController
public class HiController {

    private static final Logger logger = LoggerFactory.getLogger(HiController.class);
    @Autowired
    SchedualServiceHi schedualServiceHi;
    @Autowired
    DiscoveryClient client;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        List<String> services =  client.getServices();
        for (String service : services) {
            logger.info("注册的服务信息："+service.toString());
        }
        return schedualServiceHi.sayHiFromClientOne(name);
    }
}
