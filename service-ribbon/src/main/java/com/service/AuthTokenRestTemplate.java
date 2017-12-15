package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * 添加token信息的resttemplate
 * Created by a on 2017/12/14.
 */
@Component
public class AuthTokenRestTemplate {
    @Autowired
    private RestTemplate restTemplate;
    private static final String AUTH_TOKEN_KEY = "Authorization";

    public <T> T getForObject(String url, Class<T> responseType, Object... uriVariables) throws RestClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTH_TOKEN_KEY , "121212");

        return restTemplate.getForObject(url , responseType , new HttpEntity<T>(headers) , uriVariables);
    }
}
