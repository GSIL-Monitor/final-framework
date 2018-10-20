package cn.com.likly.finalframework.data.provider;

import cn.com.likly.finalframework.data.domain.Query;
import cn.com.likly.finalframework.data.mapping.holder.EntityHolder;
import cn.com.likly.finalframework.data.mapping.holder.PropertyHolder;
import lombok.NonNull;

/**
 * @author likly
 * @version 1.0
 * @date 2018-10-19 11:04
 * @since 1.0
 */
public interface SelectProvider<T> extends Provider<String> {

    SelectProvider<T> SELECT(@NonNull EntityHolder<T> holder);

    SelectProvider<T> SELECT_COUNT(@NonNull EntityHolder<T> holder);

    SelectProvider<T> QUERY(Query<PropertyHolder> query);
}
