## 乐优商城前置学习

### SpringBoot补充学习

- 异常处理
    - Spring注入Druid配置类:无法创建bean的异常
        - 原因：是缺少jdbc相关的jar包， 
        - 解决办法：引入spring-jdbc 包
 
 
- 配置相关
    - 默认配置文件:spring boot项目中同时存在application.properties和application.yml文件时，
                  两个文件都有效，但是application.properties的优先级会比application.yml高。
        -  配置文件所在目录不同优先级也不同。如下图1~4优先级从高到低:
        - ![图片](https://img-blog.csdn.net/20180921103443224?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3UwMTQwNDk1MjE=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

       
                