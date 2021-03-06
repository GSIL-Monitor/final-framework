package com.ilikly.finalframework.coding.element;

import com.ilikly.finalframework.core.Streable;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * @author likly
 * @version 1.0
 * @date 2018-10-29 09:55
 * @since 1.0
 */
public interface Entity<P extends Property> extends Streable<P>, Iterable<P> {

    /**
     * return the package of entity
     *
     * @see javax.lang.model.util.Elements#getPackageOf(Element)
     */
    String getPackage();

    /**
     * return the name of entity
     */
    String getName();


    /**
     * return the simpleName of entity
     */
    String getSimpleName();

    /**
     * return the raw type of entity
     * @see TypeElement#toString()
     */
    String getRawType();

    /**
     * return the type of entity
     * @see javax.lang.model.util.Types#erasure(TypeMirror)
     */
    String getType();

    List<P> getProperties();

    P getProperty(String name);

    P getIdProperty();

    default P getRequiredIdProperty() {

        P property = getIdProperty();

        if (property != null) {
            return property;
        }

        throw new IllegalStateException(String.format("Required identifier property not found for %s!", getType()));
    }

    <A extends Annotation> A getAnnotation(Class<A> annotationType);

    default <A extends Annotation> boolean hasAnnotation(Class<A> annotationType) {
        return getAnnotation(annotationType) != null;
    }

}
