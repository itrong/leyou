package com.leyou.goods;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author lujianrong
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LYGoodsWeb {
    public static void main(String[] args) {
        SpringApplication.run(LYGoodsWeb.class, args);
    }
}