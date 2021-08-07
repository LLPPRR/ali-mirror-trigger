package com.linpr.ali.mirror.trigger.service;

import com.linpr.ali.mirror.trigger.docker.DockerParameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author lin10
 * @date 2021/8/7 14:31
 * @description
 **/
@Slf4j
@Component
public class ConsoleService {


    public String dockerContainerId(String dockerMirroring) {
        String[] cmd = new String[]{"docker", "ps", "-a"};
        String containerId = "";
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            InputStream is = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(dockerMirroring)) {
                    containerId = line.substring(0, 12);
                    break;
                }
            }
            p.waitFor();
            is.close();
            reader.close();
            p.destroy();
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("获取docker镜像有误！");
        }
        return containerId;
    }


    public void restartDocker(String image, String name, List<String> ports, List<String> volumeList, String param) {
        DockerParameter dockerParameter = new DockerParameter();
        dockerParameter.pull(image).and();
        String containerId = dockerContainerId(image);
        if (!ObjectUtils.isEmpty(containerId)) {
            dockerParameter.stop(containerId).and().rm(containerId).and();
        }
        dockerParameter.run(image, name, ports, volumeList, param);
        exec(dockerParameter.cmd());
    }


    public void exec(String cmd) {
        log.info("参数：{}", cmd);
        String[] exec = {"/bin/sh", "-c", cmd};
        try {
            Process p = Runtime.getRuntime().exec(exec);
            InputStream is = p.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
            }
            p.waitFor();
            is.close();
            reader.close();
            p.destroy();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
