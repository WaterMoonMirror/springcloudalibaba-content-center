package com.wondersgroup.lz.contentcenter.service.share;

import com.wondersgroup.lz.contentcenter.dao.share.ShareMapper;
import com.wondersgroup.lz.contentcenter.domain.dto.conten.ShareDTO;
import com.wondersgroup.lz.contentcenter.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class ShareService {

    final ShareMapper shareMapper;
    final RestTemplate restTemplate;
    final DiscoveryClient discoveryClient;
    final UserCenterFeignClient userCenterFeignClient;
    public ShareDTO getShareById(Integer id) {

        // 获取分享详情
        var share = shareMapper.selectByPrimaryKey(id);
       /* // 使用discoveryClient 获取动态地址
        var ins = discoveryClient.getInstances("user-center");
        var targUrl = ins.stream().map(r -> r.getUri().toString() + "/users/{id}")
                .findFirst()
                .orElseThrow(()->new RuntimeException("没有发现当前实例"));
        log.info("目标地址为{}",targUrl);*/
        // 获取用户信息
        /*var user = restTemplate.getForObject("http://user-center/users/{id}",
                UserDTO.class, id);*/
        var user = userCenterFeignClient.findById(id);
        var shareDTO = new ShareDTO();
        // 消息的装配
        BeanUtils.copyProperties(share,shareDTO);
        shareDTO.setWxNickname(user.getWxNickname());
        return shareDTO;
    }
}
