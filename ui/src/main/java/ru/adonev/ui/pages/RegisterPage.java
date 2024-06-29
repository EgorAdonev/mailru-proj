package ru.adonev.ui.pages;

import io.qameta.allure.Step;
import java.time.LocalDate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import ru.adonev.steps.FormSteps;
import ru.adonev.ui.elements.Locators;
import ru.adonev.utils.DateUtils;

public class RegisterPage extends BasePage {

  public static final String FNAME_FIELD = "fname";
  public static final String LNAME_FIELD = "lname";
  public static final String EMAIL = "input[name='partial_login']";
  public static final String PHONE_NUM = "#phone-number__phone-input";
  public static final String DAY_SELECT = "//form//div[@data-test-id='bdate-tooltip']//div[contains(@class, 'day')]//select";
  public static final String DAY_INPUT = "//div[contains(@class, 'value')][.//*[contains(@id,'react-select-2-input')]/input";
  public static final String MONTH_INPUT = "//div[contains(@class, 'value')][.//*[contains(@id,'react-select-3-input')]/input";
  public static final String YEAR_INPUT = "//div[contains(@class, 'value')][.//*[contains(@id,'react-select-4-input')]/input";
  public static final String DAY = "//div[contains(@class, 'base')]//span[text()='День']";
  public static final String MONTH = "//div[contains(@class, 'base')]//span[text()='Месяц']";
  public static final String YEAR = "//div[contains(@class, 'base')]//span[text()='Год']";
  public static final String GENDER = "//form//input[@name='gender']";
  public static final By REGISTER_BUTTON =
      By.xpath("//form/button[@type='submit']");

  private final FormSteps formSteps;
  protected WebDriver driver;

  @Autowired
  public RegisterPage(FormSteps formSteps, WebDriver driver) {
    super(driver);
    this.formSteps = formSteps;
    this.driver = driver;
  }

  @Step("Зарегистрировать почтовый ящик")
  public void registerEmail() {
    driver.findElement(REGISTER_BUTTON).click();
  }

  @Step("Заполнить пароль")
  public void fillPass(String pass) {
    formSteps.fillSensitiveField(By.xpath(Locators.PASSWORD), "password", pass, driver);
  }

  @Step("Заполнить ФИО")
  public void fillInitials(String firstName, String secondName) {
    formSteps.fillFieldByName(By.id(FNAME_FIELD), "Имя", firstName, driver);
    formSteps.fillFieldByName(By.id(LNAME_FIELD), "Фамилия", firstName, driver);
  }

  @Step("Заполнить почту")
  public void fillMail(String mail) {
    formSteps.fillFieldByName(By.cssSelector(EMAIL), "Почта", mail, driver);
  }

  @Step("Заполнить телефон")
  public void fillPhoneNum(String phoneNum) {
    formSteps.fillFieldByName(By.cssSelector(PHONE_NUM), "Телефон", phoneNum, driver);
  }

  @Step("Заполнить дату рождения")
  public void fillBirthDate(LocalDate birthDate) {

    driver.findElement(By.xpath(DAY)).click();
    formSteps.fillFieldByName(By.cssSelector(DAY_INPUT), "День",
        String.valueOf(birthDate.getDayOfMonth()), driver);

    driver.findElement(By.xpath(MONTH)).click();
    formSteps.fillFieldByName(By.cssSelector(MONTH_INPUT), "Месяц",
        DateUtils.getRusNameOfMonth(birthDate), driver);

    driver.findElement(By.xpath(YEAR)).click();
    formSteps.fillFieldByName(By.cssSelector(YEAR_INPUT), "Год",
        DateUtils.getRusNameOfMonth(birthDate), driver);

    /*Select birthMonth = new Select(driver.findElement(By.xpath(MONTH)));
    birthMonth.selectByVisibleText(DateUtils.getRusNameOfMonth(birthDate));

    Select birthYear = new Select(driver.findElement(By.xpath(YEAR)));
    birthYear.selectByVisibleText(String.valueOf(birthDate.getYear()));*/
  }

  @Step("Заполнить пол")
  public void fillGender(boolean isMale) {
    Select birthDay = new Select(driver.findElement(
        By.xpath(GENDER)));
    birthDay.selectByValue(isMale ? "male" : "female");
  }

}
