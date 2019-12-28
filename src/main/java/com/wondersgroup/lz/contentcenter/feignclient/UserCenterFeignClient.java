package com.wondersgroup.lz.contentcenter.feignclient;

import com.wondersgroup.lz.contentcenter.configuration.UserCenterFeignConfiguration;
import com.wondersgroup.lz.contentcenter.domain.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 使用Feign的自定义配置
 */
//@FeignClient(name = "user-center",configuration = UserCenterFeignConfiguration.class)
@FeignClient(name = "user-center")
public interface UserCenterFeignClient {
    @GetMapping("/users/{id}")
    UserDTO findById(@PathVariable Integer id);

    @GetMapping("/users/q")
    UserDTO query(@SpringQueryMap UserDTO userDTO);
}
