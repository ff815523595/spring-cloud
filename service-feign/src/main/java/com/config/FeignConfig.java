package com.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by a on 2017/12/15.
 */
@Configuration
public class FeignConfig {

  /*  @Bean
    public FeignBasicAuthRequestInterceptor feginbasicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }*/

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin","admin123");
    }

    /**
     * Feign请求拦截器
     */
    private class FeignBasicAuthRequestInterceptor  implements RequestInterceptor {

        public FeignBasicAuthRequestInterceptor() {

        }

        @Override
        public void apply(RequestTemplate template) {
            template.header("Authorization", System.getProperty("cloud.auth.token"));
        }
    }

}
