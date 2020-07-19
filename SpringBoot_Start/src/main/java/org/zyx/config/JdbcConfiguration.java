package org.zyx.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * JDBC 数据源,连接池配置类(基于Spring)
 */
@Configuration //声明这个类是一个java配置类,相当于一个xml配置文件
//@PropertySource("classpath:jdbc.properties") //读取Property配置文件
@EnableConfigurationProperties(JdbcProperties.class)
public class JdbcConfiguration {

    /**
     * 通过读取Property配置文件设置属性
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

    @Resource
    private org.zyx.config.JdbcProperties jdbcProperties;


    @Bean //把方法的返回值注入到IOC容器中
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        //添加this,区分局部和全局
//        dataSource.setUrl(this.url);
//        dataSource.setUsername(this.username);
//        dataSource.setPassword(this.password);
//        dataSource.setDriverClassName(this.driverClassName);

        /**
         * 通过配置读取类获取参数
         */
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());

        return dataSource;
    }


}
