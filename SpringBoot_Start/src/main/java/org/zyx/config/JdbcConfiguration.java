package org.zyx.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * JDBC 数据源,连接池配置类
 */
@Configuration //声明这个类是一个java配置类,相当于一个xml配置文件
//@PropertySource("classpath:jdbc.properties") //读取Property配置文件
//3.通过属性读取类获取配置,自动注入,允许自动读取配置属性
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfiguration {

    /**
     * 1.通过读取Property配置文件设置属性
     * @return
     */
//    @Value(value = "${jdbc.url}")
//    private String url;
//    @Value(value = "${jdbc.driverClassName}")
//    private String driverClassName;
//    @Value(value = "${jdbc.username}")
//    private String username;
//    @Value(value = "${jdbc.password}")
//    private String password;

//    @Resource //自动注入
//    private org.zyx.config.JdbcProperties jdbcProperties;


    @Bean //把方法的返回值注入到IOC容器中
    public DataSource dataSource(JdbcProperties jdbcProperties) {//2.通过形参注入配置bean
        DruidDataSource dataSource = new DruidDataSource();
        //添加this,区分局部和全局
//        dataSource.setUrl(this.url);
//        dataSource.setUsername(this.username);
//        dataSource.setPassword(this.password);
//        dataSource.setDriverClassName(this.driverClassName);


        //通过配置读取类获取参数
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());

        return dataSource;
    }

    //4.直接通过在方法上添加注释读取配置属性
/*
    @ConfigurationProperties(prefix = "jdbc")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }
*/


}
