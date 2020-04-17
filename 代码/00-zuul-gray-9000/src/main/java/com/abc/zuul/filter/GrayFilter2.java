package com.abc.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicBoolean;


@Component
@Slf4j
public class GrayFilter2 extends ZuulFilter {
    private AtomicBoolean flag = new AtomicBoolean(true);

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
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        if(flag.get()) {
            flag.set(false);
            // 若flag的值为true，则路由到running-host主机
            RibbonFilterContextHolder.getCurrentContext().add("host-mark", "running-host");

        // 若flag的值为false，则路由到gray-host主机
        } else {
            flag.set(true);
            RibbonFilterContextHolder.getCurrentContext().add("host-mark", "gray-host");
        }

        return null;
    }
}
