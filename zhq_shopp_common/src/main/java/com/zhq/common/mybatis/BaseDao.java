package com.zhq.common.mybatis;

import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

public interface BaseDao {
    /**
     * 增加持久化对象
     * @param oj
     * @param table
     */
    @InsertProvider(type = BaseProvider.class, method = "save")
    public void save(@Param("oj") Object oj, @Param("table") String table);

    /**
     * 修改持久化对象
     * @param oj
     * @param table
     * @param idKey
     */
    @InsertProvider(type = BaseProvider.class, method = "update")
    public void update(@Param("oj") Object oj, @Param("table") String table, @Param("idKey") Long idKey);

}
