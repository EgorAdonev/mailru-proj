package ru.adonev.steps;

import ru.adonev.ui.browser.factory.BrowserFactory;
import com.google.common.base.Preconditions;
import io.qameta.allure.Step;
import java.time.Duration;
import java.time.LocalDate;
import org.apache.commons.validator.routines.EmailValidator;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adonev.ui.DriverSetupService;
import ru.adonev.ui.elements.Locator;
import ru.adonev.ui.pages.MailRuPage;
import ru.adonev.ui.pages.RegisterPage;
import ru.adonev.utils.DateUtils;
import ru.adonev.utils.Utils;

@Service
public class UiSteps {

  private final DriverSetupService setupService;

  //предсмертный скриншот
  @Autowired
  public UiSteps(DriverSetupService setupService) {
    this.setupService = setupService;
  }

  @Step("Зайти на mail.ru")
  public boolean goToMailRuLogIn() {
    try {
      WebDriver driver = setupService.getDriver();

      driver.get(String.format("%s%s",
          BrowserFactory.loadApp(Utils.getProperty("host", "src/main/resources/driver.properties")),
          "/?from=logout&ref=main"));
      MailRuPage mailRuPage = new MailRuPage(driver);
      return mailRuPage.getTitle()
          .equals("Mail.ru: почта, поиск, новости, прогноз погоды, гороскоп, программа передач");
    } catch (ElementNotInteractableException e) {
      return false;
    }
  }

  @Step("Создать почту")
  public RegisterPage createEmailBox(String mail, String phoneNumber, String password) {
    //сделать так чтоб степ можно было использовать и в негатив
    WebDriver driver = setupService.getDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector(".grid__header a.ph-project__register")))
        .click();
    fillInitials("Egor", "Automation QA 555 3000");
    fillPass(password);
    fillMail(mail);
    fillPhoneNum(phoneNumber);
    fillBirthDate(LocalDate.of(2000, 10, 14));
    fillGender(true);
    driver.findElement(By.xpath(
        "//*[@id=\"root\"]//form//button[@type=\"submit\" "
            + "and not(@tabindex=\"-1\")]/span[text()=\"Создать\"]"));
    return new RegisterPage(driver);
  }
  @Step("Создать почту с ошибкой")
  public RegisterPage createEmailBoxUnsuccess(String mail, String phoneNumber, String password) {
    //сделать так чтоб степ можно было использовать и в негатив
    WebDriver driver = setupService.getDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector(".grid__header a.ph-project__register")))
        .click();
    fillInitials("Egor", "Automation QA 555 3000");
    fillPass(password);
    fillMail(mail);
    fillPhoneNum(phoneNumber);
    fillBirthDate(LocalDate.of(2000, 10, 14));
    fillGender(true);

    return new RegisterPage(driver);
  }

  @Step("Заполнить пароль")
  public void fillPass(String pass) {
    WebDriver driver = setupService.getDriver();
    driver.findElement(By.xpath(Locator.PASSWORD.locator)).sendKeys(pass);
  }

  @Step("Заполнить ФИО")
  public void fillInitials(String firstName, String secondName) {
    WebDriver driver = setupService.getDriver();
    driver.findElement(By.cssSelector("div > form .input-0-2-119[name=\"fname\"]"))
        .sendKeys(firstName);
    driver.findElement(By.cssSelector("div > form .input-0-2-119[name=\"lname\"]"))
        .sendKeys(secondName);
  }

  @Step("Заполнить пол")
  public void fillSex(String sex) {
    WebDriver driver = setupService.getDriver();
    if (sex.equals("male")) {
      driver.findElement(By.cssSelector(Locator.MALE_CSS.locator)).submit();
    } else {
      driver.findElement(By.cssSelector(Locator.FEMALE_CSS.locator)).submit();
    }
  }

  @Step("Заполнить почту")
  public void fillMail(String mail) {
    WebDriver driver = setupService.getDriver();
    driver.findElement(By.cssSelector("div > form > * input[name=\"partial_login\"]"))
        .sendKeys(mail);
  }

  @Step("Заполнить телефон")
  public void fillPhoneNum(String phoneNum) {
    WebDriver driver = setupService.getDriver();
    driver.findElement(By.cssSelector("div[class] > input[data-test-id=\"phone-input\"]"))
        .sendKeys(phoneNum);
  }

  @Step("Заполнить дату рождения")
  public void fillBirthDate(LocalDate birthDate) {
    WebDriver driver = setupService.getDriver();

    Select birthDay = new Select(
        driver.findElement(By.xpath("//*[@id=\"root\"]//form//span[text()=\"День\"]")));
    birthDay.selectByVisibleText(String.valueOf(birthDate.getDayOfMonth()));

    Select birthMonth = new Select(
        driver.findElement(By.xpath("//*[@id=\"root\"]//form//span[text()=\"Месяц\"]")));
    birthMonth.selectByVisibleText(DateUtils.getRusNameOfMonth(birthDate));

    Select birthYear = new Select(
        driver.findElement(By.xpath("//*[@id=\"root\"]//form//span[text()=\"Год\"]")));
    birthYear.selectByVisibleText(String.valueOf(birthDate.getYear()));
  }

  @Step("Заполнить пол")
  public void fillGender(boolean isMale) {
    WebDriver driver = setupService.getDriver();
    Select birthDay = new Select(driver.findElement(
        By.xpath("//*[@id=\"root\"]//form//input[@name=\"gender\"]")));
    birthDay.selectByValue(isMale ? "male" : "female");
  }

  @Step("Отправить электронное письмо")
  public MailRuPage sendEmail(String mail, String msgContent) {
    WebDriver driver = setupService.getDriver();
    Preconditions.checkArgument(EmailValidator.getInstance().isValid(mail),
        "Неверный формат почты");
    return new MailRuPage(driver);
  }
}