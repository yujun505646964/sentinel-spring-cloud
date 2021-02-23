# 工程简介

改工程主要包含sentinel相关功能的开发：Nacos持久化、spring cloud集成、spring cloud gateway集成


# 注意点
spring.cloud.sentinel.filter.enabled注解原理，开启关闭对比
根据项目需要，如果全部接口都要，则开启true。部分接口限流，用
@SentinelResource注解 

spring.cloud.sentinel.filter.enabled =false
为什么控制台流控规则不能点？


# 延伸阅读

https://mp.weixin.qq.com/s/AjHCUmygTr78yo9yMxMEyg

sentinel-dashboard持久化到nacos
https://blog.csdn.net/nesrpk/article/details/107294409

接口流控处理优化
https://blog.csdn.net/luanlouis/article/details/91633042
@SentinelResource注解
https://blog.csdn.net/lzb348110175/article/details/107634024
https://www.jianshu.com/p/b61727df6ea5



