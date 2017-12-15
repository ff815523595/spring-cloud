package com.service.service0;

import com.bean.AuthQuery;
import com.common.base.ResponseData;
import com.service.service0.impl.AuthTokenClientHystric;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by a on 2017/12/15.
 */
@FeignClient(value = "service-hi" , fallback = AuthTokenClientHystric.class)
public interface AuthTokenClient {

    @RequestMapping(method = RequestMethod.POST , value = "/oauth/token")
    ResponseData auth(@RequestBody AuthQuery query) ;
}
