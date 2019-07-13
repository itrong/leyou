package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * il商城注册中心启动类
 *
 * @author lujianrong
 */
@EnableEurekaServer
@SpringBootApplication
public class LYRegistry {
    public static void main(String[] args) {
        SpringApplication.run(LYRegistry.class,args);
    }

}
