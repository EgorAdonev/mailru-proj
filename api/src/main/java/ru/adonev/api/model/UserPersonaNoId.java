package ru.adonev.api.model;

public record UserPersonaNoId(String firstName,
                              String lastName,
                              int age,
                              String sex,
                              java.math.BigDecimal money) {


}
