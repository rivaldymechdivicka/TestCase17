package com.example.demo.controller;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mqtt")
@RequiredArgsConstructor
public class MqttController {

  private final MqttClient mqttClient;

  @PostMapping("/publish")
  public void publishMessage() throws MqttException {
    mqttClient.publish("test", new MqttMessage("Test".getBytes()));
  }
}
