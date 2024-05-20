package ru.adonev.ui.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class MailRuPage {
    protected WebDriver driver;
    private By usernameBy = By.name("user_name");
    // <input name="password" type="password" value="">

    public MailRuPage(WebDriver driver){
        this.driver = driver;
        if (!driver.getTitle().equals("Sign In Page")) {
            throw new IllegalStateException("This is not Sign In Page," +
                    " current page is: " + driver.getCurrentUrl());
        }
    }

    /**
     * Insert keys to field located by XPath
     *
     * @param field - xpath loc
     * @param data - UTF-8 string
     */
    public void fill(Locator field, String data) {
        driver.findElement(By.xpath(field.locator)).sendKeys(data);
    }

    public Select dateLocator(Locator datePicker){
        return new Select(driver.findElement(By.xpath(datePicker.locator)));
    }
     /*
    public HomePage inputKeysXpath(Locator field, String data) {
        driver.findElement(By.xpath(field.locator)).sendKeys(data);
        driver.findElement(usernameBy).sendKeys(userName);
        driver.findElement(passwordBy).sendKeys(password);
        driver.findElement(signinBy).click();
        return new HomePage(driver);
    }
    */
}
