package ru.adonev.api;

import ru.adonev.utils.Utils;

public final class ApiSetupService {

  private ApiSetupService() {
  }

  public static String getAddr() {
    String host = Utils.getProperty("host",
        "src/main/resources/api-test-host.properties");
    String port = Utils.getProperty("port",
        "src/main/resources/api-test-host.properties");
    return host + port;
  }
}
