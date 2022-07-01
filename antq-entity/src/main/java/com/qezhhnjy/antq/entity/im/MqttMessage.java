package com.qezhhnjy.antq.entity.im;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhaoyangfu
 * @since 2022/6/30-20:20
 */
@Data
public class MqttMessage {

    private String topic;
    @NotBlank(message = "payload不能为空")
    private String payload;
    private Integer    qos;
}
