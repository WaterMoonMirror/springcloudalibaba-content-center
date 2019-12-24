package com.wondersgroup.lz.contentcenter.controller.content;

import com.wondersgroup.lz.contentcenter.domain.dto.conten.ShareDTO;
import com.wondersgroup.lz.contentcenter.service.share.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("share")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {
    final ShareService shareService;

    @GetMapping("{id}")
    public ShareDTO getShareById(@PathVariable Integer id){
        var share = shareService.getShareById(id);
        return share;
    }

}
