package com.linpr.ali.mirror.trigger.controller.param;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lin10
 * @date 2021/8/7 11:06
 * @description
 **/
@NoArgsConstructor
@Data
public class AliMirrorCallbackParam {


    /**
     * push_data : {"digest":"sha256:b0fc483a175c94d1ebd6393c371ed599d2a1da5cc6f9fdde0bb84413c9ba7070","pushed_at":"2021-08-07 11:04:24","tag":"d_1.0.0"}
     * repository : {"date_created":"2021-08-03 21:01:23","name":"wage","namespace":"linpr","region":"cn-shenzhen","repo_authentication_type":"NO_CERTIFIED","repo_full_name":"linpr/wage","repo_origin_type":"NO_CERTIFIED","repo_type":"PRIVATE"}
     */

    private PushDataBean push_data;
    private RepositoryBean repository;

    @NoArgsConstructor
    @Data
    public static class PushDataBean {
        /**
         * digest : sha256:b0fc483a175c94d1ebd6393c371ed599d2a1da5cc6f9fdde0bb84413c9ba7070
         * pushed_at : 2021-08-07 11:04:24
         * tag : d_1.0.0
         */
        private String digest;
        private String pushed_at;
        private String tag;
    }

    @NoArgsConstructor
    @Data
    public static class RepositoryBean {
        /**
         * date_created : 2021-08-03 21:01:23
         * name : wage
         * namespace : linpr
         * region : cn-shenzhen
         * repo_authentication_type : NO_CERTIFIED
         * repo_full_name : linpr/wage
         * repo_origin_type : NO_CERTIFIED
         * repo_type : PRIVATE
         */
        private String date_created;
        private String name;
        private String namespace;
        private String region;
        private String repo_authentication_type;
        private String repo_full_name;
        private String repo_origin_type;
        private String repo_type;
    }


}
