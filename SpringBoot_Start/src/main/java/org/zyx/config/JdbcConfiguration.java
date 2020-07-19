package org.zyx.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * JDBC 数据源,连接池配置类
 */
@Configuration //声明这个类是一个java配置类,相当于一个xml配置文件
@PropertySource("classpath:jdbc.properties") //读取Property配置文件
public class JdbcConfiguration {

    @Value(value = "${jdbc.url}")
    private String url;
    @Value(value = "${jdbc.driverClassName}")
    private String driverClassName;
    @Value(value = "${jdbc.username}")
    private String username;
    @Value(value = "${jdbc.password}")
    private String password;



    @Bean //把方法的返回值注入到IOC容器中
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        //添加this,区分局部和全局
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setDriverClassName(this.driverClassName);

        return dataSource;
    }



}
