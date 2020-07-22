## 乐优商城前置学习

### Spring 补充学习
- Spring 七大核心
   - 核心容器(Spring-core)：核心容器提供 Spring 框架的基本功能。核心容器的主要组件是 BeanFactory，
     它是工厂模式的实现。BeanFactory 使用控制反转 （IOC） 模式将应用程序的配置和依赖性规范与实际的应用程序代码分开。
   - Spring 上下文(Spring-context)：Spring 上下文是一个配置文件，向 Spring 框架提供上下文信息。Spring 上下文包括企业服务，
     例如 JNDI、EJB、电子邮件、国际化、校验和调度功能。
   - Spring AOP：通过配置管理特性，Spring AOP 模块直接将面向方面的编程功能集成到了 Spring 框架中。所以，可以很容易地使 Spring 框架管理的任何对象支持 AOP。
     Spring AOP 模块为基于 Spring 的应用程序中的对象提供了事务管理服务。通过使用 Spring AOP，不用依赖 EJB 组件，就可以将声明性事务管理集成到应用程序中。
   - Spring DAO：JDBC DAO 抽象层提供了有意义的异常层次结构，可用该结构来管理异常处理和不同数据库供应商抛出的错误消息。异常层次结构简化了错误处理，
     并且极大地降低了需要编写的异常代码数量（例如打开和关闭连接）。Spring DAO 的面向 JDBC 的异常遵从通用的 DAO 异常层次结构。
   - Spring ORM：Spring 框架插入了若干个 ORM 框架，从而提供了 ORM 的对象关系工具，其中包括 JDO、Hibernate 和 iBatis SQL Map。
     所有这些都遵从 Spring 的通用事务和 DAO 异常层次结构。
   - Spring Web 模块：Web 上下文模块建立在应用程序上下文模块之上，为基于 Web 的应用程序提供了上下文。所以，Spring 框架支持与 Jakarta Struts 的集成。
     Web 模块还简化了处理多部分请求以及将请求参数绑定到域对象的工作。 
   - Spring MVC 框架：MVC 框架是一个全功能的构建 Web 应用程序的 MVC 实现。通过策略接口，MVC 框架变成为高度可配置的，
     MVC 容纳了大量视图技术，其中包括 JSP、Velocity、Tiles、iText 和 POI。

### SpringBoot补充学习

- 注解
   - @Controller/@RestController 控制器/rest控制器
   - @EnableAutoConfiguration 启用SpringBoot应用的自动配置
   - @ComponentScan 类似于<context:component-scan base-package="">,自动扫描该类所在的包以及所有子包
   - @SpringApplication 启动器
   - @SpringBootConfiguration 实质是@Configuration注解,声明一个Java配置类,只允许包含一个
   - @Configuration 声明这个类是一个java配置类,相当于一个xml配置文件
   - @PropertiesSource("classpath:")  读取Property配置文件
   - @Bean 注入对象
   - @Value("${}") 读取配置文件属性
   - @ConfigurationProperties(prefix = "jdbc") 配置属性
   - @@EnableConfigurationProperties(JdbcProperties.class) 通过属性读取类获取配置,自动注入,允许自动读取配置属性
   

- 异常处理
    - Spring注入Druid配置类:无法创建bean的异常
        - 原因：是缺少jdbc相关的jar包， 
        - 解决办法：引入spring-jdbc 包
 
 
- 配置相关
    - 默认配置文件:spring boot项目中同时存在application.properties和application.yml文件时，
                  两个文件都有效，但是application.properties的优先级会比application.yml高。
        -  配置文件所在目录不同优先级也不同。如下图1~4优先级从高到低:
        - ![图片](https://img-blog.csdn.net/20180921103443224?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTQwNDk1MjE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
    - 读取配置文件的四种方式
        - @PropertySource("classpath:jdbc.properties") //读取Property配置文件,@Value("${}")注入睡醒
        - @ConfigurationProperties(prefix = "jdbc") //直接通过在方法上添加注释读取配置属性
            ```java
             @ConfigurationProperties(prefix = "jdbc")
                public DataSource dataSource() {
                    DruidDataSource dataSource = new DruidDataSource();
                    return dataSource;
                }
            ```   
        - 创建配置类,@EnableConfigurationProperties(JdbcProperties.class)读取,可复用
            ```java
            @Data
            @Component //交由IOC容器管理,使用@Configuration也一样
            @ConfigurationProperties(prefix = "jdbc")
            public class JdbcProperties {
                private String url;
                private String driverClassName;
                private String username;
                private String password;
            }
            ```     
        - 上面的基础上直接传入形参即可:public DataSource dataSource(JdbcProperties jdbcProperties) {}
    - SpringBoot的四种属性注入
        - @Autowired/@Resource
        - 构造方法注入
        - @Bean方法形参注入
        - 直接在@Bean方法上使用@ConfigurationProperties(prefix = "jdbc")注解
        
- 日志等级配置: logging.level.org.springframework = DEBUG

- 整合数据源 HikariCP数据源(光,性能最好的数据源,SpringBoot默认连接池)
    - 引入jdbc启动器,mysql驱动
    - SpringBoot默认使用的Hikari数据源,所以无需配置数据源类型
    - 配置四中基本属性:url,username,password,驱动默认mysql驱动
    - spring.datasource.url=jdbc:mysql://localhost:3306/myDatabase  
- 拦截器配置
    - 自定义拦截器,实现HandlerInterceptor接口
    - 配置拦截器:自定义一个java配置类(@Configuration),实现WebMvcConfigurer接口
- 整合事务:添加@Transactional 注解(在service层或controller层)
 
 
- 什么是SpringBoot?
    - 构建spring应用的脚手架,内置Tomcat,打包成jar,自动配置(根据引入的依赖,简化配置)
- SpringBoot的基本使用
    - 搭建SpringBoot的基本应用
        - 引入统一的父工程,以及需要的启动器
        - 覆盖默认配置
        - 添加引导类,@SpringBootApplication(组合注解)
        