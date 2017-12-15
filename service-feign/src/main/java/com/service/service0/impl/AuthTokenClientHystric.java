package com.service.service0.impl;

import com.bean.AuthQuery;
import com.common.base.ResponseData;
import com.service.service0.AuthTokenClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by a on 2017/12/15.
 */
@Component
public class AuthTokenClientHystric implements AuthTokenClient {
    @Override
    public ResponseData auth(@RequestBody AuthQuery query) {
        return ResponseData.ok("");
    }
}
