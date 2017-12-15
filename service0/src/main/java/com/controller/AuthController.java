package com.controller;

import com.bean.AuthQuery;
import com.bean.User;
import com.common.base.ResponseData;
import com.common.util.JWTUtils;
import com.service.IAuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by a on 2017/12/14.
 */
@RestController
@RequestMapping(value="/oauth")
public class AuthController {

    @Autowired
    private IAuthService authService;

    @PostMapping("/token")
    public ResponseData auth(@RequestBody AuthQuery query) throws Exception {
        return checkAuth(query);
    }

    @GetMapping("/token")
    public ResponseData oauth(AuthQuery query) throws Exception {
       return checkAuth(query);
    }

    private ResponseData checkAuth(AuthQuery query){
        if (StringUtils.isBlank(query.getAccessKey()) || StringUtils.isBlank(query.getSecretKey())) {
            return ResponseData.failByParam("accessKey and secretKey not null");
        }

        User user = authService.auth(query);
        if (user == null) {
            return ResponseData.failByParam("认证失败");
        }

        JWTUtils jwt = JWTUtils.getInstance();
        return ResponseData.ok(jwt.getToken(user.getId().toString()));
    }
}
