package org.zyx.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 定义一个过滤器，模拟一个登录的校验。基本逻辑：如果请求中有access-token参数，则认为请求有效，放行。
 * 1.继承 ZuulFilter顶级父类
 * 2.实现四个方法
 * 3.返回对应值
 * 4.编写过滤逻辑
 */

@Component
public class LoginFilter extends ZuulFilter {

//    @Resource  //直接通过自动注入的方式注入request
//    private HttpServletRequest request;

    @Override   //过滤器类型:pre,route,post,error
    public String filterType() {
        return "pre";
    }

    @Override   //过滤器优先级(执行顺序:返回值越小,优先级越高)
    public int filterOrder() {
        return 2;//默认不使用0,具有拓展性
    }

    @Override    //是否需要过滤(是否生效,true执行run方法,false不执行)
    public boolean shouldFilter() {
        return true;
    }

    @Override   //执行,编写过滤器业务逻辑
    public Object run() throws ZuulException {
        //直接自动注入request/response即可
//      String access_token = request.getParameter("access_token");

        //1.1:初始化context(zuul包)上下文对象,servlet String
        RequestContext context = RequestContext.getCurrentContext();//获取当前上下文对象
        //1.2:获取Request对象,通过Zuul获取request
        HttpServletRequest request = context.getRequest();
        //2.获取参数
        String access_token = request.getParameter("access_token");
        System.out.println("access_token:"+access_token);

        if(StringUtils.isEmpty(access_token)){
            //拦截,不转发请求,请求失败
            context.setSendZuulResponse(false);
            //设置相应状态码(401:身份未认证)
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            context.getResponse().setContentType("text/html;charset=utf-8");
            //设置相应提示
            context.setResponseBody("用户未认证,请重试");
        }

        return null;//返回值为null,就表示该过滤器什么也不做
    }
}
