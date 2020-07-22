package org.zyx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zyx.interceptor.MyInterceptor;

import javax.annotation.Resource;

/**
 * MVC配置类
 * 1.声明该类是一个java配置类
 * 2.实现WebMvcConfigurer接口
 *
 * 注入拦截器
 *
 */

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Resource
    private MyInterceptor myInterceptor;
    /**
     * 拦截器注册器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**1.注册自定义的拦截器
         * 2.配置拦截路径:    /* 拦截一级路径
         *                   /* 拦截二级路径
         * 3.
         */
        registry.addInterceptor(myInterceptor)
        .addPathPatterns("/**")
        ;

    }
}
