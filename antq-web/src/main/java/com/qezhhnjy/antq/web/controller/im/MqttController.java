package com.qezhhnjy.antq.web.controller.im;

import cn.hutool.core.util.StrUtil;
import com.qezhhnjy.antq.common.consts.BaseResult;
import com.qezhhnjy.antq.entity.im.MqttMessage;
import com.qezhhnjy.antq.im.mqtt.MqttComm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author zhaoyangfu
 * @since 2022/6/30-20:16
 */
@RestController
@Slf4j
@RequestMapping("/mqtt")
public class MqttController {

    @Resource
    private MqttComm mqttComm;

    @PostMapping("/publish")
    public BaseResult<MqttMessage> publish(@RequestBody @Valid MqttMessage message) {
        String topic = message.getTopic();
        Integer qos = message.getQos();
        String payload = String.format("%s:%s", LocalDateTime.now(), message.getPayload());
        if (StrUtil.isBlank(topic)) mqttComm.publish(payload);
        else if (qos == null) mqttComm.publish(topic, payload);
        else mqttComm.publish(topic, qos, payload);
        message.setPayload(payload);
        return BaseResult.success(message);
    }

    public static int i = 0;

    // @Scheduled(fixedRate = 1000)
    public void publish() {
        mqttComm.publish("antq", 1, String.format("%s:%s", i++, "Amy"));
    }

}
