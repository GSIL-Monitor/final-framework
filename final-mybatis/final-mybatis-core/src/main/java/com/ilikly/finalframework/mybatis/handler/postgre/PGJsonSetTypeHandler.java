package com.ilikly.finalframework.mybatis.handler.postgre;


import com.ilikly.finalframework.json.Json;
import com.ilikly.finalframework.mybatis.handler.JsonCollectionTypeHandler;

import java.util.Set;

/**
 * @author likly
 * @version 1.0
 * @date 2018-09-26 23:55
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class PGJsonSetTypeHandler<T> extends PGJsonCollectionTypeHandler<T, Set<T>> {


    public PGJsonSetTypeHandler(Class<T> type) {
        super(type);
    }

    @Override
    protected Set<T> getResult(String value, Class type) {
        return Json.parse(value, Set.class, type);
    }
}
