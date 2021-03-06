package com.ilikly.finalframework.mybatis.generator;

import com.ilikly.finalframework.core.Assert;
import com.ilikly.finalframework.data.annotation.FunctionColumn;
import com.ilikly.finalframework.data.mapping.Dialect;
import com.ilikly.finalframework.data.mapping.Property;
import com.ilikly.finalframework.data.mapping.generator.AbsColumnGenerator;
import com.ilikly.finalframework.mybatis.Utils;
import org.apache.ibatis.type.TypeHandler;

/**
 * @author likly
 * @version 1.0
 * @date 2019-01-22 12:15:32
 * @since 1.0
 */
public class BaseColumnGenerator extends AbsColumnGenerator {

    public static final BaseColumnGenerator INSTANCE = new BaseColumnGenerator();
    private final Dialect dialect;

    public BaseColumnGenerator() {
        this(Dialect.DEFAULT);
    }

    public BaseColumnGenerator(Dialect dialect) {
        this.dialect = dialect;
    }

    @Override
    public String generateWriteValue(String prefix, Property property, String value) {
        if (property.hasAnnotation(FunctionColumn.class)) {
            FunctionColumn functionColumn = property.getRequiredAnnotation(FunctionColumn.class);
            String writer = functionColumn.writer();
            return String.format(writer, formatValue(prefix, property, value));
        } else {
            return formatValue(prefix, property, value);
        }

    }

    protected String formatValue(String prefix, Property property, String value) {
        final Class javaType = Utils.getPropertyJavaType(property);
        final TypeHandler typeHandler = Utils.getPropertyTypeHandler(dialect, property);
        final StringBuilder builder = new StringBuilder();

        builder.append(property.placeholder() ? "#{" : "${").append(value);

        if (Assert.nonEmpty(prefix)) {
            builder.append(".").append(prefix);
        }
        builder.append(".").append(property.getName());

        if (typeHandler != null) {
            builder.append(",javaType=").append(javaType.getCanonicalName());
            builder.append(",typeHandler=").append(typeHandler.getClass().getCanonicalName());
        }
        builder.append("}");

        return builder.toString();
    }

}
