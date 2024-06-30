package ru.adonev.steps;

import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter.Mode;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FormSteps {


  @Step("Заполнить {fieldName}")
  public void fillFieldByName(By selector, String fieldName, String value, WebDriver driver) {
    driver.findElement(selector).sendKeys(value);
  }

  @Step("Заполнить поле {fieldName} с чувствительными данными ")
  public void fillSensitiveField(By selector, String fieldName, /*@Param(mode = Mode.MASKED)*/ String sensitiveData,
      WebDriver driver) {
    driver.findElement(selector).sendKeys(sensitiveData);
  }

  @Step("Ожидаем доступность кнопки {selector} для клика")
  public void waitUntilClickable(WebDriver driver, By selector) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(selector)).click();
  }
}
