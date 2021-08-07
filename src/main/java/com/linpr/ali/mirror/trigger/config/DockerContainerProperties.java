package com.linpr.ali.mirror.trigger.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lin10
 * @date 2021/8/7 16:13
 * @description
 **/
@Data
@Component
@ConfigurationProperties(prefix = "docker.container")
@EnableConfigurationProperties({DockerContainerProperties.class})
public class DockerContainerProperties {

    private Map<String, DockerContainerBean> map;


}
