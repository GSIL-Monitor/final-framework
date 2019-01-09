package com.ilikly.finalframework.data.mapping.converter;

/**
 * @author likly
 * @version 1.0
 * @date 2019-01-09 17:52:29
 * @since 1.0
 */
public class NameConverterRegister {
    private static NameConverterRegister instance = new NameConverterRegister();

    private final NameConverter defaultNameConverter = new SimpleNameConverter();

    private NameConverter tableNameConverter = defaultNameConverter;
    private NameConverter columnNameConverter = defaultNameConverter;

    private NameConverterRegister() {
    }

    public static NameConverterRegister getInstance() {
        return instance;
    }

    public NameConverter getTableNameConverter() {
        return tableNameConverter;
    }

    public void setTableConverter(NameConverter tableNameConverter) {
        this.tableNameConverter = tableNameConverter;
    }

    public NameConverter getColumnNameConverter() {
        return columnNameConverter;
    }

    public void setColumnConverter(NameConverter columnNameConverter) {
        this.columnNameConverter = columnNameConverter;
    }
}
