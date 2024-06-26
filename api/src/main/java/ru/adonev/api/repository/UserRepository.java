package ru.adonev.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public long findLastUser() {
    return jdbcTemplate.queryForObject(
            "select max(id) from person",
            new UserRowMapper())
        .id();

  }
}
