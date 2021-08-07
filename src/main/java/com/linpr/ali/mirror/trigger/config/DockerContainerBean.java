package com.linpr.ali.mirror.trigger.config;

import lombok.Data;

import java.util.List;

/**
 * @author lin10
 * @date 2021/8/7 16:20
 * @description
 **/
@Data
public class DockerContainerBean {


    private String name;
    private String param;
    private List<String> ports;
    private List<String> volumes;


}
