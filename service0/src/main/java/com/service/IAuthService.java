package com.service;

import com.bean.AuthQuery;
import com.bean.User;

/**
 * Created by a on 2017/12/14.
 */
public interface IAuthService {

    User auth(AuthQuery query);
}
