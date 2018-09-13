# Spring boot 学习（1.5版本）
[TOC]

## 一、引入Spring boot
### 1.1 maven 管理

+ spring boot 的maven 依赖：
```
<dependencyManagement> 
	<dependencies>
		<!-- Spring boot-->
		<dependency>
			<groupId>org.springframework.boot</groupId>
		 	<artifactId>spring-boot-dependencies</artifactId>
			<version>1.5.15.RELEASE</version>
			<type>pom</type>
			<scope>import</scope>
		</dependency>
	</dependencies>
</dependencyManagement>
```
+ 使用 spring boot maven plugin
```
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```
+ 修改spring 引入jar包中的版本号，只需在dependencyManagement 中引入目标版本号jar，会自动替换spring boot中的依赖jar版本

+ 若项目使用maven模块，则上面的spring boot 只需在父pom中引入，子pom中直接引入对应的spring boot的功能依赖（即对应的starter）

+ [spring boot 的starter](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/htmlsingle/#using-boot-starter)


## 二、基本注解

## 三、配置文件

