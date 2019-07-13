package com.leyou.item;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * il商城商品微服务启动类
 *
 * @author lujianrong
 */

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.item.mapper")
public class LYItemService {
    public static void main(String[] args) {
        SpringApplication.run(LYItemService.class, args);
}
}
