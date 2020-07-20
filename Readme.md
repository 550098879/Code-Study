## 乐优商城前置学习

### SpringBoot补充学习

- 注解
   - @Controller/@RestController 控制器/rest控制器
   - @EnableAutoConfiguration
   - @ComponentScan
   - @SpringApplication 启动器
   - @SpringBootConfiguration
   - @Configuration
   - @PropertiesSource("classpath:")
   - @Bean
   - @Value("${}")
   - @ConfigurationProperties(prefix = "jdbc")
   - @@EnableConfigurationProperties(JdbcProperties.class)
   


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
            