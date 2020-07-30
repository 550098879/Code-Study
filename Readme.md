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
    - 通用Mapper
        - 主键id全部为0
            - 解决：自增主键类型int型改为Integer
        - 创建实体Bean失败,GenerationType.AUTO 错误
            - 解决: 主键生成策略: @GeneratedValue(strategy = GenerationType.IDENTITY)
    - 模块中创建模块,出现无法读取模块代码的问题
        - 解决:idea右上角->项目结构->Module->选中该模块->添加Source(参照其他模块,指定目录)
    
    
    
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
- 序列化/反序列化工具: jackson 工具包(SpringBoot默认消息转换器)
    - private static final ObjectMapper MAPPER = new ObjectMapper();
    - MAPPER.readValue();反序列化
    - MAPPER.writeValueAsString(Object obj) 对象序列化为字符串
- RestTemplate 远程调用技术(后端发送请求)
    - Spring提供了一个RestTemplate模板工具类，对基于Http的客户端进行了封装，并且实现了对象与json的序列化和反序列化，非常方便。RestTemplate并没有限定Http的客户端类型，而是进行了抽象，目前常用的3种都有支持：
      - HttpClient
      - OkHttp
      - JDK原生的URLConnection（默认的）
    - 注意:需要在项目中注册一个RestTemplate对象，可以在启动类位置注册
    ```java
    @SpringBootApplication
    public class HttpDemoApplication {
    	public static void main(String[] args) {
    		SpringApplication.run(HttpDemoApplication.class, args);
    	}
    	@Bean
    	public RestTemplate restTemplate() {
            // 默认的RestTemplate，底层是走JDK的URLConnection方式。
    		return new RestTemplate();
    	}
    }
    ```
- Idea快捷键
    - 次数.fori 快速创建for循环
 
- 什么是SpringBoot?
    - 构建spring应用的脚手架,内置Tomcat,打包成jar,自动配置(根据引入的依赖,简化配置)
- SpringBoot的基本使用
    - 搭建SpringBoot的基本应用
        - 引入统一的父工程,以及需要的启动器
        - 覆盖默认配置
        - 添加引导类,@SpringBootApplication(组合注解)
        
        
        
### SpringCloud 补充学习

- 架构演变:传统架构->水平拆分->垂直拆分(最早的分布式架构)->SOA(dubbo,面向服务)->微服务(SpringCloud)
- 远程调用技术:rpc http         (基于TCP协议)
    - rpc协议:需要自定义数据格式(团队约定),限定技术,传输速度快,效率快 (基于传输层) dubbo
    - http协议:统一的数据格式,比限定技术,rest接口,效率不如rpc  (基于表示层) SpringCloud
- 什么是SpringCloud?
    - 微服务架构的解决方案,是很多组件的集合
        - Eureka:注册中心.服务的注册与发现
        - Zuul:网关组件,路由请求,过滤器(ribbon,hystrix)
        - Ribbon: 负载均衡组件
        - Hystrix:熔断组件
        - Feign: 远程调用组件(ribbon hystrix)
        
- Eureka:高可用,修改端口实现注册中心转移
    - 注册中心:
        - 添加Eureka-server依赖(引入启动器)
        - 配置spring.application.name=注册中心名
        - 在引导类上添加@EnableEurekaServer成为注册中心
    - 客户端:
        - 添加Eureka-Client依赖(引入启动器)
        - 配置spring.application.name=注册中心名
        - 配置Eureka.client.service-url.defaultZone="http://localhost:8761/eureka(注册中心地址)"  (访问不加eureka)
        - 引导类添加@EnableDiscoveryClient 启用Eureka客户端
        
- Ribbon负载均衡器:自动请求,不同算法(轮询,随机,自定义..)   | 添加至服务消费者
    - 启动多个不同端口的同一个服务,模拟集群
    - 因为Eureka中已经集成了Ribbon，所以我们无需引入新的依赖。直接修改代码：在RestTemplate的配置方法上添加@LoadBalanced注解：
    - 修改调用方式，不再手动获取ip和端口，而是直接通过服务名称调用： 
        - 直接通过服务名发送请求
        - return restTemplate.getForObject("http://service-provider/user/"+id,User.class);
    - 修改负载均衡策略:覆盖application.yml的默认配置即可
    - 重试机制:添加spring-retry依赖,设置对应服务实例的重试机制配置信息即可(开启重试)
    
- Hystrix 熔断器:是Netflix开源的一个延迟和容错库，用于隔离访问远程服务、第三方库，防止出现级联失败。  | 添加至服务消费者
    - 雪崩问题: 微服务发生异常,请求阻塞,无响应,Tomcat无法释放线程,导致线程阻塞,最终导致服务器资源耗尽,所有服务不可用
        - 线程隔离:Hystrix为每个依赖服务调用分配一个小的线程池,如果线程池已满,调用将被立即拒绝,默认不采用排队,加速事变判定时间
            - 问题处理:不直接访问服务,而是通过线程池中空闲的线程来访问服务,线程池已满或者请求超时会进行降级处理(服务降级)
            - 服务降级:优先保证核心服务,而非核心服务不可用或弱可用,不会导致阻塞最多只是该服务对其他服务无响应
        - 服务熔断: 达到熔断阈值全部降级5s,5秒后尝试向微服务发送请求,正常后打开,反复尝试
    - 服务降级的使用:
        - 添加依赖:spring-cloud-starter-netflix-hystrix
        - 熔断时间,默认1s,配置yml覆盖默认时间
        - 引导类添加注解:@EnableCircuitBreaker--开启熔断器   (@EnableHystrix:所有方法默认开启熔断机制)
        - 全局熔断:
            - 控制层添加注解:@DefaultProperties(defaultFallback = "fallbackMethod") //全局熔断方法名
            - 指定默认的全局熔断方法(控制器中的所有方法返回值需要一致)
            - 需要熔断的方法需要添加 @HystrixCommand注解
            - 创建应急方法 fallbackMethod
        - 方法级熔断:
            - 方法添加注解: @HystrixCommand(fallbackMethod = "queryUserByIdFallback"),声明被熔断的方法,不指定使用全局默认
            - 创建应急方法 queryUserByIdFallback,参数列表和返回值需要一致
    - 服务熔断的使用:  熔断器,也叫断路器:Circuit Breaker
        - Closed:关闭状态,所有请求都能正常访问.
        - Open:打开状态,所有请求都会被降级,Hystrix会对请求情况计数,当一定时间内失败请求百分比达到阈值,在触发熔断,
               断路器会完全打开,默认失败比例的阈值是50%,请求次数最少不低于20次(出现异常的请求)
        - Half Open:半开状态,open状态不是永久的,打开后会进入休眠时间(默认是5s),随后断路器会自动进入半开状态,此时会释放部分请求,
                通过.若这些请求都是健康的,则会完全关闭断路器,否则继续保持打开,再次进行休眠计时
        -  
    - 组合注解: @SpringCloudApplication
        - @SpringBootApplication :SpringBoot注解
        - @EnableCircuitBreaker  : 开启熔断器
        - @EnableDiscoveryClient :开启服务发现(连接Eureka服务)

- Feign:集成了Ribbon负载均衡和Hystrix熔断器                  | 添加至服务消费者
    - Feign可以把Rest的请求进行隐藏，伪装成类似SpringMVC的Controller一样。你不用再自己拼接url，拼接参数等等操作，一切都交给Feign去做。      
    -  Feign使用:
        - 1.添加Feign依赖:spring-cloud-starter-openfeign
        - 2.引导类添加@EnableFeignClients注解,开启Feign
        - 3.无需RestTemplate队请求进行封装,直接使用Feign发送请求
        - 4.创建Feign接口,添加注解@FeignClient("服务提供者名"),定义方法,类似控制器,不需要写实现类(通过服务名获取服务的地址和端口)
        - 5.在控制层直接注入,调用使用即可
    - 使用Feign集成的Hystrix:开启熔断功能
    ```yml
      feign:
        hystrix:
          enabled: true  # 开启Feign的Hystrix熔断功能
    ```
    - 定义一个处理降级信息的类,实现FeignClient接口,重写方法负责作为fallback的处理类
    - 在FeignClient接口的注解添加属性fallback = 降级类.class
    - @FeignClient(value = "service-provider",fallback = UserClientFallback.class)
    
    - 使用Feign集成的Ribbon:默认集成,无需RestTemplate和引入额外依赖,只需要视情况配置即可
    ```yml
    user-service:
      ribbon:
        ConnectTimeout: 250 # 连接超时时间(ms)
        ReadTimeout: 1000 # 通信超时时间(ms)
        OkToRetryOnAllOperations: true # 是否对所有操作重试
        MaxAutoRetriesNextServer: 1 # 同一服务不同实例的重试次数
        MaxAutoRetries: 1 # 同一实例的重试次数
  
    ```
    - 请求压缩:Spring Cloud Feign 支持对请求和响应进行GZIP压缩，以减少通信过程中的性能损耗。通过下面的参数即可开启请求与响应的压缩功能：
    ```yml
        feign:
          compression:
            request:
              enabled: true # 开启请求压缩
               mime-types: text/html,application/xml,application/json # 设置压缩的数据类型
               min-request-size: 2048 # 设置触发压缩的大小下限
            response:
              enabled: true # 开启响应压缩
    ```
    - 日志级别:前面讲过，通过logging.level.xx=debug来设置日志级别。然而这个对Fegin客户端而言不会产生效果。
        因为@FeignClient注解修改的客户端在被代理时，都会创建一个新的Fegin.Logger实例。我们需要额外指定这个日志的级别才可以。
        - 设置org.zyx包下的日志级别都为debug
        ```yaml
            logging:
              level:
                org.zyx: debug
        ```
        - 编写配置类，定义日志级别
        ```java
           @Configuration
           public class FeignConfig {
               @Bean
               Logger.Level feignLoggerLevel(){
                   return Logger.Level.FULL;
               }
           }
        ```
        - 这里指定的Level级别是FULL，Feign支持4种级别：
            - NONE：不记录任何日志信息，这是默认值。
            - BASIC：仅记录请求的方法，URL以及响应状态码和执行时间
            - HEADERS：在BASIC的基础上，额外记录了请求和响应的头信息
            - FULL：记录所有请求和响应的明细，包括头信息、请求体、元数据。
        - 在FeignClient中指定配置类：
         ```java
          @FeignClient(value = "user-service", fallback = UserFeignClientFallback.class, configuration = FeignConfig.class)
          public interface UserFeignClient {
              @GetMapping("/user/{id}")
              User queryUserById(@PathVariable("id") Long id);
          }
         ```    
- Zuul网关
    - 服务网关是微服务架构中一个不可或缺的部分。通过服务网关统一向外系统提供REST API的过程中，除了具备服务路由、均衡负载功能之外，它还具备了权限控制等功能。
      Spring Cloud Netflix中的Zuul就担任了这样的一个角色，为微服务架构提供了前门保护的作用，同时将权限控制这些较重的非业务逻辑内容迁移到服务路由层面，
      使得服务集群主体能够具备更高的可复用性和可测试性。
    - 不管是来自于客户端（PC或移动端）的请求，还是服务内部调用。一切对服务的请求都会经过Zuul这个网关，然后再由网关来实现 鉴权、
      动态路由等等操作。Zuul就是我们服务的统一入口。
    - 功能:
        - 身份认证与安全:
        - 审查与监控:
        - 动态路由:
        - 压力测试:
        - 负载分配: 
        - 静态响应处理:
        - 多区域弹性:
     - 使用:
        - 创建工程,引入Zuul启动器
        - 启动类添加@EnableZuulProxy 注解,启用Zuul网关组件
            - 第一种配置方式:
                - zuul.routs.<路由名称>.path=/serviceName/**
                - zuul.routs.<路由名称>.url=http://localhost:port            
                - yml配置端口,服务名,和网关路由,(缺点:固定url,硬编码,服务应该从注册中心找)
            - 第二种配置方式
                - zuul.routs.<路由名称>.path=/serviceName/**
                - zuul.routs.<路由名称>.serviceId=serviceName
                - 将Zuul注入到Eureka注册中心(依赖,注解(启用EurekaClient),yml注册中心地址)
                - 不再配置服务对应的路由url,而是配置serviceId
                - 通过服务ID路由url地址实现负载均衡
            - 第三种配置方式
                - zuul.routs.<服务名>=/serviceName/**
                - 直接将Path放到路由名称(服务名)后面,不再配置path/url/serviceID
                - 默认自动路由注册中心所有的服务
                - 可以通过路由设置的路径访问,也可以通过服务名访问
            - 第四种配置方式
                - 不配置,只需要Zuul注册到注册中心,默认就是服务Id开头路径,自动路由
            ```yaml
                  server:
                    port: 10010
                  spring:
                    application:
                      name: zyx-zuul
                  
                  #配置zuul路由
                  zuul:
                    routes:
                      service-provider: /service-provider/**  #路由名称,可以随便取,习惯是服务名
                      service-customer: /cus(可自定义访问路径)/**
                    prefix: /api       #路由前缀,判断是否经过Zuul,可配置可不配置
                  #      path: /service-provider/**     #多级目录
                  #      url: http://localhost:8081     #路由url
                  #      serviceId: service-provider     #实现负载均衡,避免硬编码
                  eureka:
                    client:
                      service-url:
                        defaultZone: http://localhost:8761/eureka
                  #配置负载均衡超时等待时间
                  ribbon:
                    ReadTimeout: 120000  #请求处理的超时时间
                    ConnectTimeout: 30000  #请求连接的超时时间
            ```
          
               

- 玩法
    - 引入组件的启动器
    - 覆盖默认配置
    - 在引导类上添加注解,开启相关组件
    - 存储在双重Map中
        - Map<serviceId,Map<服务实例名,实例对象(instance)>>
- 技巧
    - idea开启service,配置Application,自动扫描所有启动类,统一管理启动器

- 注解 :
    - @EnableDiscoveryClient //启用Eureka客户端(SpringCloud提供,推荐使用)    ---@EnableEurekaClient(netflix提供) 都可以启动

- 异常
    - 父模块配置了数据库源,导致子模块启动要求配置数据源
        - 解决: 
            - 配置相应参数,配置数据源即可
            - 启动类排除掉DataSourceAutoConfiguration(数据源自动配置)
        ```
                  @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
                  public class CosApplication {
                      public static void main(String[] args) {
                          SpringApplication.run(CosApplication.class);
                      }
                  }
                             
         ```
    - Zuul网关请求超时问题:
        - 配置Ribbon的请求时间和超时时间即可(默认时间是1s)
        ```yaml
        ribbon: 
        	ReadTimeout: 120000  #请求处理的超时时间  
        	ConnectTimeout: 30000  #请求连接的超时时间
        ```