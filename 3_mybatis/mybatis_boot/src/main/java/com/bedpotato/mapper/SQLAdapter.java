package com.bedpotato.mapper;

/**
 * Created with IntelliJ IDEA.
 * User: hanwei
 * Date: 14-4-9
 * Time: 下午3:04
 * To change this template use File | Settings | File Templates.
 */
public class SQLAdapter {
    String sql;

    public SQLAdapter(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
