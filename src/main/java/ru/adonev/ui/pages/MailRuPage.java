package ru.adonev.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.adonev.ui.elements.Locator;

public class MailRuPage {
    protected WebDriver driver;

    public MailRuPage(WebDriver driver){
        this.driver = driver;
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

    public String getTitle(){
        return driver.getTitle();
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
