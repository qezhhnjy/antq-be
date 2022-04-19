package com.qezhhnjy.antq.web.controller.im;

import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.im.server.ChannelManager;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhaoyangfu
 * @date 2022/4/19-21:59
 */
@RestController
@Slf4j
@RequestMapping("/im-manager")
public class ManagerController {

    @GetMapping("/group")
    public BaseResult<ChannelGroup> group() {
        ChannelGroup group = ChannelManager.group();
        return BaseResult.success(group);
    }

    @GetMapping("/username-map")
    public BaseResult<Enumeration<String>> usernameMap() {
        ConcurrentHashMap<String, Channel> map = ChannelManager.usernameMap();
        return BaseResult.success(map.keys());
    }

}
