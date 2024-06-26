package ru.adonev.api.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public record UserPersona(long id,
                          String firstName,
                          String lastName,
                          int age,
                          String sex,
                          java.math.BigDecimal money) {

  @Override
  public String toString() {
    return new ToStringBuilder(ToStringStyle.SHORT_PREFIX_STYLE)
        .append("id", id)
        .append("firstName", firstName)
        .append("lastName", lastName)
        .append("age", age)
        .append("sex", sex)
        .append("money", money)
        .toString();
  }
}
