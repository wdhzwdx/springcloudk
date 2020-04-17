package com.abc.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul过滤器
 */
@Component
@Slf4j
public class RouteFilter extends ZuulFilter {

    // 设置过滤类型
    @Override
    public String filterType() {
        // 指定在路由之前进行过滤
        return FilterConstants.PRE_TYPE;
    }

    // 设置当前过滤器在所有过滤器中执行的顺序
    // 数字越小优先级越高，可以是负数
    @Override
    public int filterOrder() {
        return -5;
    }

    // 通过过滤后要执行的业务逻辑
    @Override
    public Object run() throws ZuulException {
        log.info("通过过滤");
        return null;
    }

    // 真正的过滤规则就是定义在这里的
    // 返回true，则通过过滤
    @Override
    public boolean shouldFilter() {
        // 获取请求上下文
        RequestContext context = RequestContext.getCurrentContext();
        // 从请求上下文中获取请求
        HttpServletRequest request = context.getRequest();

        // 从请求中获取请求参数与uri
        String user = request.getParameter("user");
        String uri = request.getRequestURI();

        // 若/abc8080的访问中没有携带user参数，则无法通过过滤
        if(uri.contains("/abc8080") && StringUtils.isEmpty(user)) {
            log.warn("user用户为空");
            // 设置没有通过过滤
            context.setSendZuulResponse(false);
            // 返回的状态码
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
            // 返回false，不会调用run()方法了
            return false;
        }

        return true;
    }
}
