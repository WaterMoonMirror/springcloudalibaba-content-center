package com.wondersgroup.lz.contentcenter;

import com.wondersgroup.lz.contentcenter.configuration.UserCenterFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.wondersgroup.lz")
@SpringBootApplication
//@EnableFeignClients(defaultConfiguration = UserCenterFeignConfiguration.class)  // 开启Feint客户端
@EnableFeignClients  // 开启Feint客户端
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }

    @Bean
    @LoadBalanced   // ribbion 负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
