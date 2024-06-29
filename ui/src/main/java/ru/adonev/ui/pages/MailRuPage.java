package ru.adonev.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailRuPage extends BasePage {

  private final By registerButton = By.cssSelector(".grid__header a.ph-project__register");
  @FindBy(xpath = "//nav//a[text()='Почта']")
  private WebElement mailButton;
  protected WebDriver driver;

  public MailRuPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
    this.driver = driver;
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public WebElement getMailButton() {
    return mailButton;
  }

  public By getRegisterButton() {
    return registerButton;
  }
}
