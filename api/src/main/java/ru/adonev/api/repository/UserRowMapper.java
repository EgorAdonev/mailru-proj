package ru.adonev.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import ru.adonev.api.model.UserPersona;

public class UserRowMapper implements RowMapper<UserPersona> {

  @Override
  public UserPersona mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new UserPersona(rs.getInt("id"),
        rs.getString("first_name"),
        rs.getString("second_name"),
        rs.getInt("age"),
        String.valueOf(rs.getBoolean("sex")),
        rs.getBigDecimal("money"));
  }
}
