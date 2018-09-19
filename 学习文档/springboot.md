# Spring boot 学习（1.5版本）
[TOC]

## 一、引入Spring boot
### 1.1 maven 管理
+ 1.1.1：spring boot 的maven 依赖：
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
+ 1.1.2：使用 spring boot maven plugin
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
+ 1.1.3：修改spring 引入jar包中的版本号，只需在dependencyManagement 中引入目标版本号jar，会自动替换spring boot中的依赖jar版本
+ 1.1.4：若项目使用maven模块，则上面的spring boot 只需在父pom中引入，子pom中直接引入对应的spring boot的功能依赖（即对应的starter）
+ 1.1.5：[spring boot 的starter](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/htmlsingle/#using-boot-starter)

## 二、基本注解
+ \@Configuration 注解：Spring boot 的Java-base configuration的核心注解，定义当前类为配置类
+ \@ComponentScan 注解：启动自动扫描功能，包含@Configuration注解的类也会被自动扫描
+ \@EnableAutoConfiguration 注解: 启动springboot的自动配置功能; 
	+ Class<?>[] exclude() : 排除特定的配置类; 
	+ String[] excludeName(): 排除特定的配置类，通过类的全限定名
+ \@ SpringBootApplication 注解: 效果等同于使用了：\@EnableAutoConfiguration、\@Configuration、\@ComponentScan 三种注解的默认配置
+ \@Import 注解: 导入特定的@Configuration 类
+ \@ImportResource 注解: 导入指定的XML resource文件 

## 三、配置文件
### 1. 使用 YAML 代替 Properties 配置文件
Spring 框架中有2个class 支持加载YAML文档，YamlPropertiesFactoryBean 可以将 YAML 加载为 Properties；YamlMapFactoryBean 可以将 YAML 文档加载为一个 Map.

YAML 文档样例:
```
environments:
    dev:
        url: http://dev.bar.com
        name: Developer Setup
    prod:
        url: http://foo.bar.com
        name: My Cool App
```

对应的 properties：
```
environments.dev.url=http://dev.bar.com
environments.dev.name=Developer Setup
environments.prod.url=http://foo.bar.com
environments.prod.name=My Cool App
```
YAML的list 配置项样例：
```
my:
   servers:
       - dev.bar.com
       - foo.bar.com
```
list对应的 properties：
```
my.servers[0]=dev.bar.com
my.servers[1]=foo.bar.com
```

### 1. 使用 \@ConfigurationProperties 读取配置文件中自定义的配置项
```
/**
*	@ConfigurationProperties(prefix = "my")读取配置文件中前缀为'my' 的配置项
*   @Component 注入到spring 容器中，这样就可以在其它类中使用 @Autowired 的形式注入
*/
@ConfigurationProperties(prefix = "my")
@Component 
public class MyConfiguration {

    private List<String> name;

    private String sex;

    private int age;

    //getters and setters
}

/**
 *	yaml 配置文件中的配置：
 *	其中names 是
 */
my:
  names:
      - shaoyu
      - sunshuangshuang
  age:  18
  sex:  man

```
