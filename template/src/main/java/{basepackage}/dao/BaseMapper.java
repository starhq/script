package ${basepackage}.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Mapper基类
 *
 * @author starhq(50849806@qq.com)
 * @version 1.0
 * @description
 * @date 2018-04-06 17:24:25
 */
public interface BaseMapper<T> {

    /**
     * 批量保存对象
     *
     * @param pojos 需要保存的对象
     * @return 保存的个数
     */
    int saveBatch(List<T> pojos);

    /**
     * 保存对象
     *
     * @param pojo 需要保存的对象
     * @return 保存的个数
     */
    int save(T pojo);

    /**
     * 更新对象
     *
     * @param pojo 需要更新的对象
     * @return 修改的个数
     */
    int update(T pojo);

    /**
     * 删除单个对象
     *
     * @param id 主键
     * @return 删除的个数
     */
    int remove(Serializable id);

    /**
     * 从数据库里查询多个对象
     *
     * @return 对象集合
     */
    List<T> list();

    /**
     * 从数据库查询单个对象
     *
     * @param id 主键
     * @return 对象
     */
    T get(Serializable id);
}
