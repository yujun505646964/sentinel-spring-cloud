package com.example.demo.sentinel;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.DefaultBlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

@Slf4j
@Configuration
public class SentinelConfiguration {


    @Autowired
    private SentinelNacosConfigProperties sentinelNacosConfigProperties;

    @PostConstruct
    public void init() {
        try {
            Properties properties = new Properties();
            properties.setProperty("serverAddr", sentinelNacosConfigProperties.getServerAddr());
            properties.setProperty("namespace", sentinelNacosConfigProperties.getNamespace());
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
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(properties, sentinelNacosConfigProperties.getGroupId(), sentinelNacosConfigProperties.getFlowDataId(),
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }

    private void loadDegradeRules(Properties properties) {
        ReadableDataSource<String, List<DegradeRule>> degradeRuleDataSource = new NacosDataSource<>(properties, sentinelNacosConfigProperties.getGroupId(), sentinelNacosConfigProperties.getDegradeDataId(),
                source -> JSON.parseObject(source, new TypeReference<List<DegradeRule>>() {
                }));
        DegradeRuleManager.register2Property(degradeRuleDataSource.getProperty());
    }

    private void loadSystemRules(Properties properties) {
        ReadableDataSource<String, List<SystemRule>> systemRuleDataSource = new NacosDataSource<>(properties, sentinelNacosConfigProperties.getGroupId(), sentinelNacosConfigProperties.getSystemDataId(),
                source -> JSON.parseObject(source, new TypeReference<List<SystemRule>>() {
                }));
        SystemRuleManager.register2Property(systemRuleDataSource.getProperty());
    }

    private void loadParamRules(Properties properties) {
        ReadableDataSource<String, List<ParamFlowRule>> paramRuleDataSource = new NacosDataSource<>(properties, sentinelNacosConfigProperties.getGroupId(), sentinelNacosConfigProperties.getSystemDataId(),
                source -> JSON.parseObject(source, new TypeReference<List<ParamFlowRule>>() {
                }));
        ParamFlowRuleManager.register2Property(paramRuleDataSource.getProperty());
    }

    private void loadAuthrityRules(Properties properties) {
        ReadableDataSource<String, List<AuthorityRule>> authorityRuleDataSource = new NacosDataSource<>(properties, sentinelNacosConfigProperties.getGroupId(), sentinelNacosConfigProperties.getSystemDataId(),
                source -> JSON.parseObject(source, new TypeReference<List<AuthorityRule>>() {
                }));
        AuthorityRuleManager.register2Property(authorityRuleDataSource.getProperty());
    }

}
