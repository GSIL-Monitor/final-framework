package com.ilikly.finalframework.spring.application.listener;

import com.ilikly.finalframework.coding.plugins.spring.annotation.ApplicationEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author likly
 * @version 1.0
 * @date 2019-01-09 21:18:06
 * @since 1.0
 */
@Slf4j
@ApplicationEventListener
public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.out.println(event.getClass().getCanonicalName());
    }
}
