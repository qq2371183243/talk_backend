package me.daylight.talk.utils;

import org.apache.ibatis.type.*;

import java.sql.*;

@MappedTypes({Long.class})
@MappedJdbcTypes({JdbcType.TIMESTAMP})
public class DateLongTypeHandler implements TypeHandler<Long> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, Long mills, JdbcType jdbcType) throws SQLException {
        if (mills != null) {
            Timestamp timestamp=new Timestamp(mills);
            preparedStatement.setTimestamp(i,timestamp);
        }
    }

    @Override
    public Long getResult(ResultSet resultSet, String s) throws SQLException {
        Timestamp res=resultSet.getTimestamp(s);
        return res.getTime();
    }

    @Override
    public Long getResult(ResultSet resultSet, int i) throws SQLException {
        Timestamp res=resultSet.getTimestamp(i);
        return res.getTime();
    }

    @Override
    public Long getResult(CallableStatement callableStatement, int i) throws SQLException {
        Timestamp res=callableStatement.getTimestamp(i);
        return res.getTime();
    }
}
