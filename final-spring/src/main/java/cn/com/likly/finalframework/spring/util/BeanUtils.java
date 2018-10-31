package cn.com.likly.finalframework.spring.util;

import cn.com.likly.finalframework.util.Assert;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unused")
public interface BeanUtils {

    /**
     * return the beans annotated by specified annotation.
     *
     * @param applicationContext spring application context
     * @param annotationType     the specified annotation
     * @param <T>                the target bean type
     */
    @SuppressWarnings("unchecked")
    static <T> List<T> findBeansByAnnotation(ApplicationContext applicationContext, Class<? extends Annotation> annotationType) {
        Assert.isNull(applicationContext, "applicationContext must be not null!");
        Assert.isNull(annotationType, "annotationType must be not null!");
        return Arrays.stream(BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext, Object.class))
                .filter(name -> applicationContext.findAnnotationOnBean(name, annotationType) != null)
                .map(name -> (T) applicationContext.getBean(name))
                .collect(Collectors.toList());
    }


}
