# Spring-Boot:监控（actuator）
[TOC]

## 模块引入
在Spring-Boot项目中引入spring-boot-actuator 模块很简单，只要在pom.xml里新增下面的依赖即可，Spring-Boot应用启动后会自动开启。

```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## Endpoints
actuator的核心部分，它用来监视应用程序及交互，spring-boot-actuator中已经内置了非常多的Endpoints（health、info、beans、httptrace、shutdown）等等，同时也允许我们自己扩展自己的端点。
EndPoint 的访问是通过URI的方式请求的。

|EndPoint|desc|Sensitive|
|--|--|--|
|autoconfig|获取应用的自动化配置报告，其中包括所有自动化配置的候选项|YES|
|beans|获取应用的自动化配置报告，其中包括所有自动化配置的候选项|YES|