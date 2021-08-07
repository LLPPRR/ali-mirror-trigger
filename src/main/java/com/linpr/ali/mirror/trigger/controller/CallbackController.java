package com.linpr.ali.mirror.trigger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linpr.ali.mirror.trigger.config.DockerContainerBean;
import com.linpr.ali.mirror.trigger.config.DockerContainerProperties;
import com.linpr.ali.mirror.trigger.controller.param.AliMirrorCallbackParam;
import com.linpr.ali.mirror.trigger.service.ConsoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lin10
 * @date 2021/8/7 10:30
 * @description
 **/
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/callback")
public class CallbackController {

    private final ConsoleService consoleService;
    private final DockerContainerProperties dockerContainerProperties;

    @GetMapping("/info")
    public Map<String, DockerContainerBean> info() throws JsonProcessingException {
        Map<String, DockerContainerBean> map = dockerContainerProperties.getMap();
        ObjectMapper mapper = new ObjectMapper();
        log.info(mapper.writeValueAsString(map));
        return map;
    }

    @PostMapping("ali-mirror")
    public void callback(@RequestBody AliMirrorCallbackParam param) {
        String tag = param.getPush_data().getTag();
        String region = param.getRepository().getRegion();
        String repoFullName = param.getRepository().getRepo_full_name();
        String dockerName = "registry." + region + ".aliyuncs.com/" + repoFullName + ":" + tag;
        log.info("镜像：{}", dockerName);
        DockerContainerBean dockerContainerBean = dockerContainerProperties.getMap().get(tag);
        if (!ObjectUtils.isEmpty(dockerContainerBean)) {
            consoleService.restartDocker(dockerName, dockerContainerBean.getName(),
                    dockerContainerBean.getPorts(), dockerContainerBean.getVolumes(), dockerContainerBean.getParam());
        }
    }


}
