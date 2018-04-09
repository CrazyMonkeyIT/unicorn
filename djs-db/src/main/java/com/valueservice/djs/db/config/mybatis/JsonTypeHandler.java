package com.valueservice.djs.db.config.mybatis;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 完成数据库json字符串的转换
 */
@Deprecated
public class JsonTypeHandler extends BaseTypeHandler<Object> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter,
                                    JdbcType jdbcType) throws SQLException {

        ps.setString(i, JSON.toJSONString(parameter));
    }

    @Override
    public Object getNullableResult(ResultSet rs, String columnName)
            throws SQLException {

        return JSON.parseObject(rs.getString(columnName), Object.class);
    }

    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {

        return JSON.parseObject(rs.getString(columnIndex), Object.class);
    }

    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {

        return JSON.parseObject(cs.getString(columnIndex), Object.class);
    }
}
