package com.ilikly.finalframework.data.query.operation;

import com.ilikly.finalframework.data.query.CriterionOperations;
import com.ilikly.finalframework.data.query.QProperty;

/**
 * @author likly
 * @version 1.0
 * @date 2019-01-18 13:38:04
 * @since 1.0
 */
public class NotContainsCriterionOperation<T> extends AbsCriterionOperation<T> {
    public static final NotContainsCriterionOperation INSTANCE = new NotContainsCriterionOperation();

    @Override
    public String name() {
        return CriterionOperations.NOT_CONTAINS.name();
    }

    @Override
    public String format(QProperty property, T value) {
        final String column = getPropertyColumn(property);
        return String.format("%s NOT LIKE '%%%s%%'", column, value.toString());
    }

}
