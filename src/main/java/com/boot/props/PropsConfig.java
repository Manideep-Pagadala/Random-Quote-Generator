package com.boot.props;

import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "app")
@Data
public class PropsConfig {

	private Map<String, String> messages;

}
