package ru.adonev.steps;

import com.google.common.base.Preconditions;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter.Mode;
import java.time.LocalDate;
import org.apache.commons.validator.routines.EmailValidator;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adonev.pages.MailRuPage;
import ru.adonev.pages.RegisterPage;
import ru.adonev.pages.SignInPage;
import ru.adonev.service.BrowserService;

@Service
public class UiSteps {

  public static final String MAIN_PAGE_EXPECTED_TITLE = "Mail.ru: почта, поиск, новости, прогноз погоды, гороскоп, программа передач";
  private static final Logger LOGGER = LoggerFactory.getLogger(UiSteps.class);
  private final BrowserService browserService;

  //предсмертный скриншот
  @Autowired
  public UiSteps(BrowserService browserService) {
    this.browserService = browserService;
  }

  @Step("Зайти на mail.ru")
  public void goToMailRuLogIn() {
    WebDriver driver = browserService.getDriver();
    driver.get(String.format("%s%s", browserService.getHost(), "/?from=logout&ref=main"));
    MailRuPage mailRuPage = new MailRuPage(driver);
    Assertions.assertEquals(MAIN_PAGE_EXPECTED_TITLE, mailRuPage.getTitle(),
        "Ресурс недоступен. Тестирование невозможно.");
  }

  @Step("Создать почту")
  public RegisterPage createEmailBox(String mail, String phoneNumber,
      @Param(mode = Mode.MASKED) String password) {
    WebDriver driver = browserService.getDriver();
    MailRuPage mailRuPage = new MailRuPage(driver);
    mailRuPage.click();
    RegisterPage registerPage = new RegisterPage(driver);
    registerPage.fillInitials("Egor", "Automation QA 555 3000");
    //Не всегда запрашивается пароль при регистрации
    try {
      registerPage.fillPass(password);
    } catch (NoSuchElementException e) {
      LOGGER.info("Страница регистрации не содержит поля для ввода пароля. Пропускаем заполнение");
    }
    registerPage.fillMail(mail);
    registerPage.fillPhoneNum(phoneNumber);
    registerPage.fillBirthDate(LocalDate.of(2000, 10, 14));
    registerPage.fillGender(true);
    registerPage.registerEmail();

    return new RegisterPage(driver);
  }


  @Step("Войти в mail.ru без пароля")
  public SignInPage signIn(String mail, String phoneNum) {
    WebDriver driver = browserService.getDriver();
    MailRuPage mailRuPage = new MailRuPage(driver);
    mailRuPage.clickLoginButton();
    mailRuPage.goToSignInWindow();
    mailRuPage.fillLoginInfo(mail);
    mailRuPage.clickNext();
    mailRuPage.clickContinueButton();

    SignInPage signInPage = new SignInPage(driver);
    signInPage.clickSignIn();
    signInPage.fillPhoneNumber(phoneNum);

    return new SignInPage(driver);
  }

  @Step("Отправить электронное письмо")
  public MailRuPage sendEmail(String mail, String msgContent) {
    WebDriver driver = browserService.getDriver();
    Preconditions.checkArgument(EmailValidator.getInstance().isValid(mail),
        "Неверный формат почты");
    return new MailRuPage(driver);
  }
}
