package com.abc.zuul.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * zuul的服务降级类
 */
@Component
public class ConsumerFallback implements FallbackProvider {

    // 指定要进行服务降级的消费者微服务名称
    @Override
    public String getRoute() {
        // 对所有微服务开启降级功能
        return "*";
        // 仅对指定的微服务进行降级
        // return "abcmsc-consumer-depart-8080";
    }

    /**
     *   定义降级响应
     * @param route  指当前正在处理的微服务名称
     * @param cause
     * @return
     */
    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        // 对"abcmsc-consumer-depart-8080"不进行服务降级
        if("abcmsc-consumer-depart-8080".equals(route)) return null;

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                // 返回状态常量
                return HttpStatus.SERVICE_UNAVAILABLE;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                // 返回状态码，这里为503
                return HttpStatus.SERVICE_UNAVAILABLE.value();
            }

            @Override
            public String getStatusText() throws IOException {
                // 返回状态短语，这里为Service Unavailable
                return HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase();
            }

            // 关闭当前的Response
            @Override
            public void close() { }

            // 定义响应体
            @Override
            public InputStream getBody() throws IOException {
                // 指定降级信息
                String msg = "fallback:" + route;
                return new ByteArrayInputStream(msg.getBytes());
            }

            // 定制响应头
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}
