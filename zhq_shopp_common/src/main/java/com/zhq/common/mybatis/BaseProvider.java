package com.zhq.common.mybatis;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * @program: zhq_shopp_parent
 * @description: 封装基本Sql
 * @author: HQ Zheng
 * @create: 2019-09-15 12:34
 */
public class BaseProvider {
    public String save(Map<String, Object> para) {
        final Object oj = para.get("oj");
        final String table = (String) para.get("table");
        String sql = new SQL() {
            {
                INSERT_INTO(table);
                String columns = ReflectionUtils.getInsertFields(oj);
                String values = ReflectionUtils.getInsertDeclaredFieldsValue(oj);
                VALUES(columns, values);
            }
        }.toString();
        return sql;
    }

    public String update(Map<String, Object> para) {
        final Object oj = para.get("oj");
        final String table = (String) para.get("table");
        final Long idKey = (Long) para.get("idKey");
        String sql = new SQL() {
            {
                UPDATE(table);
                SET(ReflectionUtils.updateAllSerField(oj));
                WHERE("id="+idKey + "");
            }
        }.toString();
        return sql;
    }

}
