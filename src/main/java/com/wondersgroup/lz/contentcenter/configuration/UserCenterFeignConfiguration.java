package com.wondersgroup.lz.contentcenter.configuration;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 *  配置Feig的日志级别
 */
public class UserCenterFeignConfiguration {

    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
}
