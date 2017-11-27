package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by a on 2017/11/22.
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.service.service0")
@ComponentScan("com")
@EnableHystrixDashboard
public class ServiceFeignApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceFeignApplication.class);
    }
}
