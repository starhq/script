package ${basepackage}.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {

    int saveBatch(List<T> pojos);

    int save(T pojo);

    int update(T pojo);

    int removeById(Serializable id);

    List<T> list();

    T getById(Serializable id);
}
