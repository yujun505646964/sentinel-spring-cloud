# 工程简介

改工程主要包含sentinel相关功能的开发：Nacos持久化、spring cloud集成、spring cloud gateway集成


# 注意点
spring.cloud.sentinel.filter.enabled注解原理，开启关闭对比
根据项目需要，如果全部接口都要，则开启true。部分接口限流，用
@SentinelResource注解 

定义的全局异常处理器只能作用到Spring cloud 自动注册的resource，@sentinelResource的注解不起作用

SystemRuleManager 
if (resourceWrapper.getEntryType() != EntryType.IN) {
            return;
        }

AbstractSentinelInterceptor
Entry entry = SphU.entry(resourceName, ResourceTypeConstants.COMMON_WEB, EntryType.IN);


# 延伸阅读

https://mp.weixin.qq.com/s/AjHCUmygTr78yo9yMxMEyg

sentinel-dashboard持久化到nacos
https://blog.csdn.net/nesrpk/article/details/107294409

接口流控处理优化
https://blog.csdn.net/luanlouis/article/details/91633042
@SentinelResource注解
https://blog.csdn.net/lzb348110175/article/details/107634024
https://www.jianshu.com/p/b61727df6ea5

https://www.jianshu.com/p/c3d441bf4064


