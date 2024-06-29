package ru.adonev.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Utils {
  private Utils() {}
  public static String getProperty(String property, String propsFile) {
    Properties prop = new Properties();
    try (InputStream input = new FileInputStream(propsFile)) {
      prop.load(input);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return prop.getProperty(property);
  }
}
