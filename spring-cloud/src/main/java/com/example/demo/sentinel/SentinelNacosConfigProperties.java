package com.example.demo.sentinel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author yujun
 * @date 2021/2/26 11:40
 * @description sentinel nacos 服务器参数配置
 */
@Component("sentinelNacosConfigProperties")
@ConfigurationProperties(prefix = "sentinel.nacos.server")
public class SentinelNacosConfigProperties {

    @Value("${sentinel:nacos:server.ip}")
    private String ip;
    @Value("${sentinel:nacos:server.port}")
    private String port;
    @Value("${sentinel:nacos:server.namespace}")
    private String namespace;
    @Value("${sentinel:nacos:server.groupId}")
    private String groupId;
    @Value("${spring.application.name}")
    private String applicationName;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getServerAddr() {
        return this.getIp() + ":" + this.getPort();
    }

    public String getFlowDataId() {
        return applicationName + SentinelNacosConfigConstant.FLOW_DATA_ID_POSTFIX;
    }

    public String getDegradeDataId() {
        return applicationName + SentinelNacosConfigConstant.DEGRADE_DATA_ID_POSTFIX;
    }

    public String getSystemDataId() {
        return applicationName + SentinelNacosConfigConstant.SYSTEM_DATA_ID_POSTFIX;
    }

    public String getParamFlowDataId() {
        return applicationName + SentinelNacosConfigConstant.PARAM_FLOW_DATA_ID_POSTFIX;
    }

    public String getAuthorityDataId() {
        return applicationName + SentinelNacosConfigConstant.AUTHORITY_DATA_ID_POSTFIX;
    }

    @Override
    public String toString() {
        return "NacosConfigProperties [ip=" + ip + ", port=" + port + ", namespace="
                + namespace + ", groupId=" + groupId + "]";
    }


}
