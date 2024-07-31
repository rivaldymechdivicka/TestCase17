package com.example.demo.configuration;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.MqttConnectionOptionsBuilder;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class MqttConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttConfig.class);

    @Value("${application.mqtt.brokerUrl}")
    private String brokerUrl;

    @Value("${application.mqtt.user}")
    private String username;

    @Value("${application.mqtt.pass}")
    private String password;

    @Value("${application.mqtt.clientIdentifier}")
    private String clientId;

    @Bean
    public MqttClient mqttClient() {
        try {
            var options = new MqttConnectionOptionsBuilder()
                .automaticReconnect(true)
                .cleanStart(true)
                .connectionTimeout(30)
                .username(username)
                .password(password != null ? password.getBytes() : null)
                .build();

            LOGGER.info("Connecting to MQTT broker at {}", brokerUrl);
            MqttClient client = new MqttClient(brokerUrl, clientId);
            client.connect(options);
            LOGGER.info("Connected to MQTT broker at {}", brokerUrl);
            return client;
        } catch (MqttException e) {
            LOGGER.error("Failed to connect to MQTT broker: {}", e.getMessage());
            return null;
        }
    }
}
