package ru.adonev.pages;

import io.qameta.allure.Step;
import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.adonev.utils.DateUtils;

public class RegisterPage extends BasePage {

  private static final String FNAME_FIELD = "fname";
  private static final String LNAME_FIELD = "lname";
  private static final String EMAIL = "input[name='partial_login']";
  private static final String PHONE_NUM = "#phone-number__phone-input";
  private static final String DAY_INPUT = "//div[@class='Select__value-container css-0'][.//*[@id='react-select-2-input']]";
  private static final String MONTH_INPUT = "//input[contains(@id, 'react-select-3-input')]";
  private static final String YEAR_INPUT = "//input[contains(@id, 'react-select-4-input')]";
  private static final String DAY = "//div[contains(@class, 'base')]//span[text()='День']";
  private static final String MONTH = "//div[contains(@class, 'base')]//span[text()='Месяц']";
  private static final String YEAR = "//div[contains(@class, 'base')]//span[text()='Год']";
  private static final String GENDER = "//form//input[@name='gender']";
  private static final By REGISTER_BUTTON = By.xpath("//form/button[@type='submit']");
  //все селекторы в By
  private static final String PASSWORD = "//*[@id=\"password\"]";

  public RegisterPage(WebDriver driver) {
    super(driver);
  }

  @Step("Зарегистрировать почтовый ящик")
  public void registerEmail() {
    driver.findElement(REGISTER_BUTTON).click();
  }

  @Step("Заполнить пароль")
  public void fillPass(/*@Param(mode = Mode.MASKED)*/ String pass) {
    fillSensitiveField(By.xpath(PASSWORD), "password", pass);
  }

  @Step("Заполнить ФИО")
  public void fillInitials(String firstName, String secondName) {
    //добавить селектор в отчет
    //метод кот-ый ищет элемент по имени(или плейсхолдеру)
    fillFieldByName(By.id(FNAME_FIELD), "Имя", firstName);
    fillFieldByName(By.id(LNAME_FIELD), "Фамилия", secondName);
  }

  @Step("Заполнить почту")
  public void fillMail(String mail) {
    fillFieldByName(By.cssSelector(EMAIL), "Почта", mail);
  }

  @Step("Заполнить телефон")
  public void fillPhoneNum(String phoneNum) {
    fillFieldByName(By.cssSelector(PHONE_NUM), "Телефон", phoneNum);
  }

  @Step("Заполнить дату рождения")
  public void fillBirthDate(LocalDate birthDate) {

    driver.findElement(By.xpath(DAY)).click();
    fillFieldByName(By.xpath(DAY_INPUT), "День",
        String.valueOf(birthDate.getDayOfMonth()));

    driver.findElement(By.xpath(MONTH)).click();
    fillFieldByName(By.xpath(MONTH_INPUT), "Месяц",
        DateUtils.getRusNameOfMonth(birthDate));

    driver.findElement(By.xpath(YEAR)).click();
    fillFieldByName(By.xpath(YEAR_INPUT), "Год",
        DateUtils.getRusNameOfMonth(birthDate));

    /*Select birthMonth = new Select(driver.findElement(By.xpath(MONTH)));
    birthMonth.selectByVisibleText(DateUtils.getRusNameOfMonth(birthDate));

    Select birthYear = new Select(driver.findElement(By.xpath(YEAR)));
    birthYear.selectByVisibleText(String.valueOf(birthDate.getYear()));*/
  }

  @Step("Заполнить пол")
  public void fillGender(boolean isMale) {
    Select birthDay = new Select(driver.findElement(By.xpath(GENDER)));
    birthDay.selectByValue(isMale ? "male" : "female");
  }

  @Step("Заполнить {fieldName}")
  private void fillFieldByName(By selector, String fieldName, String value) {
    driver.findElement(selector).sendKeys(value);
  }

  @Step("Заполнить поле {fieldName} с чувствительными данными ")
  private void fillSensitiveField(By selector, String fieldName, /*@Param(mode = Mode.MASKED)*/
      String sensitiveData) {
    driver.findElement(selector).sendKeys(sensitiveData);
  }

}
