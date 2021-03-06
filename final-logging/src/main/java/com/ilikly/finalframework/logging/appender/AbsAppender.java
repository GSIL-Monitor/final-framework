package com.ilikly.finalframework.logging.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.ilikly.finalframework.logging.converter.ILoggingEvent2LoggingEventConverter;
import com.ilikly.finalframework.logging.entity.LoggingEvent;

/**
 * @author likly
 * @version 1.0
 * @date 2019-01-16 21:17:21
 * @since 1.0
 */
public abstract class AbsAppender extends AppenderBase<ILoggingEvent> {

    private ILoggingEvent2LoggingEventConverter converter = new ILoggingEvent2LoggingEventConverter();

    @Override
    protected void append(ILoggingEvent eventObject) {
        LoggingEvent loggingEvent = converter.map(eventObject);
        append(loggingEvent);
    }

    protected abstract void append(LoggingEvent event);
}
