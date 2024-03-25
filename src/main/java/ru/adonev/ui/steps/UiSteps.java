package ru.adonev.ui.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adonev.ui.DriverSetupService;
import ru.adonev.ui.elements.Buttons;

import java.nio.CharBuffer;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class UiSteps {
    private final DriverSetupService setupService;

    @Autowired
    public UiSteps(DriverSetupService setupService) {
        this.setupService = setupService;
    }

    @Step("Зайти в mail.ru")
    public void goTo() {
        setupService.setup();
        WebDriver driver = setupService.getDriver();
        driver.get(String.format("%s%s", setupService.getBaseUrl(),
                "/?from=logout&ref=main"));
        WebElement loginButton = driver.findElement(By.xpath(Buttons.LOGIN.locator));
        loginButton.click();
    }
    @Step("Создать почту")
    public void createMail(String firstName, String secondName,
                           LocalDate birthDate, String sex, String mail, char[] password, long phoneNumber) {
        WebDriver driver = setupService.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.xpath(Buttons.CREATE_MAIL_ACC_XPATH.locator)).click();
        driver.findElement(By.xpath(Buttons.FIRST_NAME.locator)).sendKeys(firstName);
        driver.findElement(By.xpath(Buttons.LAST_NAME.locator)).sendKeys(secondName);
        Select birthDay = new Select(driver.findElement(By.xpath(Buttons.DAY.locator)));
        birthDay.selectByVisibleText(String.valueOf(birthDate.getDayOfMonth()));
        Select birthMonth = new Select(driver.findElement(By.xpath(Buttons.MONTH.locator)));
        birthMonth.selectByVisibleText(birthDate.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru")));
        Select birthYear = new Select(driver.findElement(By.xpath(Buttons.YEAR.locator)));
        birthYear.selectByVisibleText(String.valueOf(birthDate.getYear()));
        WebElement sexRadioBtn = sex.equals("Male") ? driver.findElement(By.cssSelector(Buttons.MALE_CSS.locator))
                : driver.findElement(By.cssSelector(Buttons.FEMALE_CSS.locator));
        sexRadioBtn.click();
        WebElement mailField = driver.findElement(By.xpath(Buttons.MAIL.locator));
        mailField.sendKeys(mail);
        WebElement passwordForm = driver.findElement(By.xpath(Buttons.PASSWORD.locator));
        CharBuffer buf = CharBuffer.wrap(password);
        passwordForm.sendKeys(buf);
        buf.clear();
        WebElement phoneNumFieldWithoutCountryCode = driver.findElement(By.xpath(Buttons.PHONE_NUMBER.locator));
        phoneNumFieldWithoutCountryCode.sendKeys(String.valueOf(phoneNumber));
        WebElement submit = driver.findElement(By.xpath(Buttons.SUBMIT_MAIL_CREATION.locator));
        submit.submit();
        setupService.shutdown();
    }

}
