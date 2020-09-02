package com.payjs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author chengtianqi
 * @create 2020/6/22 22:31
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ServletInitializer.class, args);
    }

}
