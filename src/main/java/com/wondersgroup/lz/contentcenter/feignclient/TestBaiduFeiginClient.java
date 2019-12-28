package com.wondersgroup.lz.contentcenter.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "baidu",url = "https://www.baidu.com/")
public interface TestBaiduFeiginClient {
    @GetMapping("")
    String index();
}
