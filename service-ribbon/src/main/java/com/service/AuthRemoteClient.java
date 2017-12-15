package com.service;

import com.bean.AuthQuery;
import com.common.base.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by a on 2017/12/15.
 */
@Service
public class AuthRemoteClient {
    @Autowired
    AuthTokenRestTemplate authTokenRestTemplate;

    public ResponseData auth(AuthQuery query){
        return null;
    }
}
