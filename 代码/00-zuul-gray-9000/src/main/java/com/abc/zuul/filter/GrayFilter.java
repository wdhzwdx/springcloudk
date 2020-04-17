package com.abc.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


// @Component
@Slf4j
public class GrayFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -5;
    }

    @Override
    public boolean shouldFilter() {
        // 所有请求都通过过滤
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        // 默认将请求路由到host-mark为running-host的主机上
        RibbonFilterContextHolder.getCurrentContext().add("host-mark", "running-host");

        // 获取请求头gray-mark的值
        String mark = request.getHeader("gray-mark");
        // 若gray-mark的值为enable
        if(!StringUtils.isEmpty(mark) && "enable".equals(mark)) {
            // 将请求路由到host-mark为gray-host的主机上
            RibbonFilterContextHolder.getCurrentContext().add("host-mark", "gray-host");
        }
        return null;
    }
}
