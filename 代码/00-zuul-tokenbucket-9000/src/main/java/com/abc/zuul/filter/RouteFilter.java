package com.abc.zuul.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RouteFilter extends ZuulFilter {

    // 创建令牌桶：每秒产生2个令牌
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -5;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("通过过滤");
        return null;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();

        // tryAcquire()：若立刻可以获取到1个令牌，则返回true
        // tryAcquire(5, 3, TimeUnit.SECONDS)：若在3秒内可以获取到5个令牌，则返回true
        // acquire()：获取到1个令牌，否则阻塞，返回结果为阻塞的时间
        // acquire(5)：获取到5个令牌，否则阻塞，返回结果为阻塞的时间
        if(!RATE_LIMITER.tryAcquire()) {
            log.warn("访问量超载");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(429);
            return false;
        }
        return true;
    }
}
