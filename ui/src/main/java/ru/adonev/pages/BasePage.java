package ru.adonev.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  /**
   * Получает название страницы из браузера
   */
  public String getTitle() {
    return this.driver.getTitle();
  }
  /**
   * Получает URL текущей страницы из браузера
   */
  public String getBaseUrl() {
    return this.driver.getCurrentUrl();
  }
}
