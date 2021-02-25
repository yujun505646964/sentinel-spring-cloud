package com.myhexin.kaihu.sentinel.configuration;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRuleManager;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * @author yujun
 * @date 2021/2/25 11:40
 * @description sentinel nacos
 */

@Slf4j
@Configuration
public class SentinelNacosConfiguration {

    //配置中心地址
    @Value("${spring.cloud.naocs.config.serverAddr}")
    private String serverAddr;

    @Value("${sentinel.naocs.namespace:sentinel}")
    private String namespace;

    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct
    public void init() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", serverAddr);
            properties.setProperty("namespace", namespace);
            loadFlowRules(properties);
            loadDegradeRules(properties);
            loadSystemRules(properties);
            loadParamRules(properties);
            loadAuthrityRules(properties);
        } catch (Exception e) {
            log.error("初始化网关限流时发生错误", e);
        }
    }

    private void loadFlowRules(Properties properties) {
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties, SentinelNacosConfigUtils.getGroupId(appName), SentinelNacosConfigUtils.getFlowDataId(appName),
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    private void loadDegradeRules(Properties properties) {
        ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<>(properties, SentinelNacosConfigUtils.getGroupId(appName), SentinelNacosConfigUtils.getDegradeDataId(appName),
                source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {
                }));
        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
    }

    private void loadSystemRules(Properties properties) {
        ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new NacosDataSource<>(properties, SentinelNacosConfigUtils.getGroupId(appName), SentinelNacosConfigUtils.getSystemDataId(appName),
                source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {
                }));
        SystemRuleManager.register2Property(systemRuleDataSource.getProperty());
    }

    private void loadParamRules(Properties properties) {
        ReadableDataSource<String, List<ParamFlowRule>> paramRuleDataSource = new NacosDataSource<>(properties, SentinelNacosConfigUtils.getGroupId(appName), SentinelNacosConfigUtils.getSystemDataId(appName),
                source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {
                }));
        ParamFlowRuleManager.register2Property(paramRuleDataSource.getProperty());
    }

    private void loadAuthrityRules(Properties properties) {
        ReadableDataSource<String, List<AuthorityRule>> authorityRuleDataSource = new NacosDataSource<>(properties, SentinelNacosConfigUtils.getGroupId(appName), SentinelNacosConfigUtils.getSystemDataId(appName),
                source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {
                }));
        AuthorityRuleManager.register2Property(authorityRuleDataSource.getProperty());
    }


}
