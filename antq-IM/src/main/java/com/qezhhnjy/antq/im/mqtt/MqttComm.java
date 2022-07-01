package com.qezhhnjy.antq.im.mqtt;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * @author qezhhnjy at 2020/3/17-10:13
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttComm {

    void publish(String payload);

    void publish(@Header(MqttHeaders.TOPIC) String topic, String payload);

    void publish(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String payload);

}
