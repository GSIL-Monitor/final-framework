package com.ilikly.finalframework.spring.handler.interceptor;

import com.ilikly.finalframework.coding.plugins.spring.annotation.AutoConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author likly
 * @version 1.0
 * @date 2018-12-26 14:07:07
 * @since 1.0
 */
@AutoConfiguration
public class HandlerInterceptorAutoConfiguration {
    @Bean
    public HandlerInterceptorConfigurer handlerInterceptorConfigurer() {
        return new HandlerInterceptorConfigurer();
    }

    @Bean
    public TraceHandlerInterceptor traceHandlerInterceptor() {
        return new TraceHandlerInterceptor();
    }
}
