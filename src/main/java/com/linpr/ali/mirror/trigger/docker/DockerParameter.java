package com.linpr.ali.mirror.trigger.docker;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lin10
 * @date 2021/8/7 14:44
 * @description
 **/
@Data
public class DockerParameter {

    public static final String DOCKER = "docker";
    public static final String PULL = "pull";
    public static final String STOP = "stop";
    public static final String RM = "rm";
    public static final String AND = "&&";
    public static final String RUN = "run";
    public static final String DOCKER_D = "-d";
    public static final String DOCKER_NAME = "--name";
    public static final String DOCKER_P = "-p";
    public static final String DOCKER_V = "-v";

    public DockerParameter() {
        this.cmd = new LinkedList<>();
    }

    private List<String> cmd;


    public DockerParameter add(String param) {
        this.cmd.add(param);
        return this;
    }

    public DockerParameter pull(String image) {
        this.add(DOCKER).add(PULL).add(image);
        return this;
    }

    public DockerParameter stop(String containerId) {
        this.add(DOCKER).add(STOP).add(containerId);
        return this;
    }

    public DockerParameter and() {
        this.add(AND);
        return this;
    }

    public DockerParameter rm(String containerId) {
        this.add(DOCKER).add(RM).add(containerId);
        return this;
    }


    public DockerParameter run(String image) {
        return run(image, null);
    }

    public DockerParameter run(String image, String name) {
        return run(image, name, null);
    }

    public DockerParameter run(String image, String name, List<String> ports) {
        return run(image, name, ports, null, null);
    }

    public DockerParameter run(String image, String name, List<String> ports, List<String> volumeList, String param) {
        this.add(DOCKER).add(RUN).add(DOCKER_D);
        if (!ObjectUtils.isEmpty(name)) {
            this.add(DOCKER_NAME).add(name);
        }
        if (!ObjectUtils.isEmpty(ports)) {
            for (String port : ports) {
                if (!"".equals(port.trim())) {
                    this.add(DOCKER_P).add(port);
                }
            }
        }
        if (!ObjectUtils.isEmpty(volumeList)) {
            for (String volume : volumeList) {
                if (!"".equals(volume.trim())) {
                    this.add(DOCKER_V).add(volume);
                }
            }
        }
        this.add(image);
        if (!ObjectUtils.isEmpty(param)) {
            this.add(param);
        }
        return this;
    }

    public String cmd() {
        return String.join(" ", this.cmd);
    }


}
