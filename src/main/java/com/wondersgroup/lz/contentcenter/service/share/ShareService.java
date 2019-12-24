package com.wondersgroup.lz.contentcenter.service.share;

import com.wondersgroup.lz.contentcenter.dao.share.ShareMapper;
import com.wondersgroup.lz.contentcenter.domain.dto.conten.ShareDTO;
import com.wondersgroup.lz.contentcenter.domain.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    final ShareMapper shareMapper;
    final RestTemplate restTemplate;
    public ShareDTO getShareById(Integer id) {

        // 获取分享详情
        var share = shareMapper.selectByPrimaryKey(id);
        // 获取用户信息
        var user = restTemplate.getForObject("http://localhost:8080/users/{id}",
                UserDTO.class, id);
        var shareDTO = new ShareDTO();
        // 消息的装配
        BeanUtils.copyProperties(share,shareDTO);
        shareDTO.setWxNickname(user.getWxNickname());
        return shareDTO;
    }
}
