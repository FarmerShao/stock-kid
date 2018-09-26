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

+ \@SpringBootApplication 注解: 效果等同于使用了：\@EnableAutoConfiguration、\@Configuration、\@ComponentScan 三种注解的默认配置

+ \@Import 注解: 导入特定的@Configuration 类

+ \@ImportResource 注解: 导入指定的XML resource文件 

+ \@PropertySource 注解： 加载指定配置文件，加在\@Configuration 注解的类上 （但是无法加载YAML文件）

## 三、配置文件
### 1. Application property 文件
SpringApplication 会自动加载以下位置 application.properties或者application.yml 文件的配置，然后将这些配置项加入到Spring 的 Environment 中：

1. 当前路径下的/config 包中
2. 当前路径
3. classpath 下的/config 包中
4. classpath 根目录

`
注： 若在上述目录均包含 application.properties/.yml 文件，全部都会被加载入 Environment 中；
`

### 2. 加载 properties 配置文件
```
/**
* 通过 @PropertySource、@Configuration、@Value 来加载 properties 中的配置
*/
@PropertySource("classpath:config/my-config.properties")
@Configuration
public class TestImportConfiguration {

    @Value("${test.import.code}")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
```

### 3. 使用 YAML 代替 Properties 配置文件
Spring 框架中有2个class 支持加载YAML文档，YamlPropertiesFactoryBean 可以将 YAML 加载为 Properties；YamlMapFactoryBean 可以将 YAML 文档加载为一个 Map.
（暂时还不知道如何加载除application.yml之外的YAML文件）

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

### 4. 使用 \@ConfigurationProperties 读取配置文件中自定义的配置项
```
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


/**
*	@ConfigurationProperties(prefix = "my")读取配置文件中前缀为'my' 的配置项
*   @Component 注入到spring 容器中，这样就可以在其它类中使用 @Autowired 的形式注入
*   注意：必须在pom 文件中引入
*/
@ConfigurationProperties(prefix = "my")
@Component 
public class MyConfiguration {

    private List<String> name;

    private String sex;

    private int age;

    //getters and setters
}

```

### 5. 多环境配置文件（YAML）
在平时，我们经常会碰到测试环境一套配置，生产环境又是另外一套配置，那如何在不同的环境间切换环境配置呢？

1. 配置测试环境配置文件(dev): 在application.yml 同一目录下创建 application-dev.yml 
2. 配置生产环境配置文件(master): 在application.yml 同一目录下创建 application-master.yml 
3. 激活对应的配置文件：在application.yml中配置

```
spring:
	profiles:
		active: dev
```
### 附录： [spring 默认的 YAML meta-data](!https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/htmlsingle/#configuration-metadata)


## 三、Bean 注入Spring 容器
通过\@Configuration & \@Bean 来实现传统Spring 项目中的 <bean\>的对象注入

```
** 
 * DruidConfiguration ：druid的监控配置
 **/
@Configuration
public class DruidConfiguration {

    /**
     * 配置监控服务器
     *
     * @return 返回监控注册的servlet对象
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //设置servlet 注册实体
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //设置ip白名单
        bean.addInitParameter("allow", "127.0.0.1");
        //设置ip黑名单， deny优先级大于allow
        bean.addInitParameter("deny", "192.168.10.55");
        //设置控制台管理用户
        bean.addInitParameter("loginUsername", "druid");
        bean.addInitParameter("loginPassword", "123456");
        //是否可以重置数据
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }
 }
```

## 四、Spring MVC 
### 1. 自定义 HttpMessageConverters
在 Spring MVC 中是通过HttpMessageConverter 接口将 HTTP的请求信息和响应信息转换为对应的格式信息。
如果需要添加或者自定义 converters ，可以使用 Spring Boot的 HttpMessageConverters 类：
```
@Configuration
public class MyConfiguration {

    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> additional = ...
        HttpMessageConverter<?> another = ...
        return new HttpMessageConverters(additional, another);
    }

}
```

### 2. 静态Content
Spring Boot 会默认加载下列目录下的静态资源：
```
1: /static
2: /public
3: /resources
3: /META-INF/resources
注： 上面的 resources 不是项目结构中的 resources 目录，而是在项目结构下的 resources 目录下，再新建一个 resources 目录。
```
若在上述目录下存在 index.html 文件，则这个index.html 会自动成为项目的 home page。

