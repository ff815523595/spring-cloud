package com.service.service0;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by a on 2017/11/22.
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(@RequestParam(value = "name") String name) {
        return "sorry"+name+" connect is failed";
    }
}
