package ru.adonev.utils;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateUtils {

  private DateUtils() {
  }

  public static String getRusNameOfMonth(LocalDate birthDate) {
    return birthDate.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru"));
  }
}
