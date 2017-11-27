package com.service.service0;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by a on 2017/11/22.
 */
//value值需与注册的服务端spring.application.name一致,用于获取相应的client
@FeignClient(value = "service-hi" , fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "/hi")
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
