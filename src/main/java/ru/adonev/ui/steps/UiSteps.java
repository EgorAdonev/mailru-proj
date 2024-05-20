package ru.adonev.ui.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adonev.ui.DriverSetupService;
import ru.adonev.ui.elements.Locator;
import ru.adonev.ui.elements.MailRuPage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class UiSteps {
    private final DriverSetupService setupService;
    // можно убрать autowired если внедряется 1 зависимость
    //предсмертный скриншот
    @Autowired
    public UiSteps(DriverSetupService setupService) {
        this.setupService = setupService;
    }

    // зайти или войти ?
    //разделить на
    // действие и проверка результата
    @Step("Зайти на mail.ru")
    public int goToMailRuLogIn() {
        //setup перенести в before в тест
        try {
            WebDriver driver = setupService.getDriver();
            driver.get(String.format("%s%s",
                    // выделить получение урла в другой класс
                    setupService.getBaseUrl(),
                    "/?from=logout&ref=main"));
            WebElement loginButton = driver.findElement(By.xpath(Locator.LOGIN.locator));
            loginButton.click();
            return 0;
        } catch (ElementNotInteractableException e) {
            return -1;
        }
    }

    //сделать метод fill по заполнению поля
    //pageobject
    //добавить проверку
    @Step("Создать почту")
    public MailRuPage createMail(String mail, long phoneNumber) {
        //декомпозировать
        //сделать так чтоб степ можно было использовать и в негатив
//        HomePage homePage = mailLoginPage.loginValidUser("userName", "password");
//        assertThat(homePage.getMessageText(), is("Hello userName"));
        WebDriver driver = setupService.getDriver();
        MailRuPage mailLoginPage = new MailRuPage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebElement mailField = driver.findElement(By.xpath(Locator.MAIL.locator));
        mailField.sendKeys(mail);

        WebElement phoneNumFieldWithoutCountryCode = driver.findElement(By.xpath(Locator.PHONE_NUMBER.locator));
        phoneNumFieldWithoutCountryCode.sendKeys(String.valueOf(phoneNumber));
        WebElement submit = driver.findElement(By.xpath(Locator.SUBMIT_MAIL_CREATION.locator));
        submit.submit();
        return new MailRuPage(driver);
        //в after в тесте setupService.shutdown();
    }
    @Step("Заполнить пароль")
    public void fillPass(String pass){
        WebDriver driver = setupService.getDriver();
        MailRuPage mailLoginPage = new MailRuPage(driver);
        WebElement passwordForm = driver.findElement(By.xpath(Locator.PASSWORD.locator));
        passwordForm.sendKeys(pass);
    }
    @Step("Заполнить ФИО")
    public void fillInitials(String firstName, String secondName){
        WebDriver driver = setupService.getDriver();
        MailRuPage mailLoginPage = new MailRuPage(driver);

        driver.findElement(By.xpath(Locator.CREATE_MAIL_ACC_XPATH.locator)).click();
        mailLoginPage.fill(Locator.FIRST_NAME, firstName);
        mailLoginPage.fill(Locator.LAST_NAME, secondName);
    }
    @Step("Заполнить дату рождения")
    public void fillBirthdateAndSex(LocalDate birthDate, String sex){
        WebDriver driver = setupService.getDriver();
        MailRuPage mailLoginPage = new MailRuPage(driver);

        Select birthDay = mailLoginPage.dateLocator(Locator.DAY);
        birthDay.selectByVisibleText(String.valueOf(birthDate.getDayOfMonth()));

        Select birthMonth = mailLoginPage.dateLocator(Locator.MONTH);
        birthMonth.selectByVisibleText(birthDate
                .getMonth()
                .getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru")));

        Select birthYear = mailLoginPage.dateLocator(Locator.YEAR);
        birthYear.selectByVisibleText(String.valueOf(birthDate.getYear()));
    }
}
