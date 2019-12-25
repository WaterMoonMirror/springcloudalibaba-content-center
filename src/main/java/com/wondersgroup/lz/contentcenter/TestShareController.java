package com.wondersgroup.lz.contentcenter;

import com.wondersgroup.lz.contentcenter.dao.share.ShareMapper;
import com.wondersgroup.lz.contentcenter.domain.entity.share.Share;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestShareController {

     final ShareMapper shareMapper;
     final DiscoveryClient discoveryClient;

    @GetMapping("/test")
    public  List<Share> test(){
        // 1. 做插入
        Share share = new Share();
        share.setCreateTime(new Date());
        share.setUpdateTime(new Date());
        share.setTitle("xxx");
        share.setCover("xxx");
        share.setAuthor("大目");
        share.setBuyCount(1);

        this.shareMapper.insertSelective(share);
        List<Share> shares = shareMapper.selectAll();
        return shares;
    }

    @GetMapping("test2")
    public List<ServiceInstance> test2(){
        var instances = discoveryClient.getInstances("user-centos");
        return instances;
    }

}
