package com.service.impl;

import com.bean.AuthQuery;
import com.bean.User;
import com.service.IAuthService;
import org.springframework.stereotype.Service;

/**
 * Created by a on 2017/12/14.
 */
@Service
public class AuthServiceImpl implements IAuthService{
    @Override
    public User auth(AuthQuery query) {
        return new User(1L);
    }
}
