package com.example.demo.configuration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "application.mqtt")
@Getter
@Setter
public class MqttProperties {

    private String brokerUrl;
    private String user;
    private String pass;
    private String clientIdentifier;

    public byte[] getPassBytes() {
        return pass != null ? pass.getBytes() : null;
    }
}
