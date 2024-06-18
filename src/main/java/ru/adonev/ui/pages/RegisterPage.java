package ru.adonev.ui.pages;

import org.openqa.selenium.WebDriver;

public class RegisterPage {

  protected WebDriver driver;

  public RegisterPage(WebDriver driver) {
    this.driver = driver;

  }
  public String getTitle(){
    return this.driver.getTitle();
  }
  public String getBaseUrl(){
    return this.driver.getCurrentUrl();
  }

}
