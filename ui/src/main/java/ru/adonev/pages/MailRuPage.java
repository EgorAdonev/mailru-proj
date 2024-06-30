package ru.adonev.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MailRuPage extends BasePage {

  private final By registerButton = By.cssSelector(".grid__header a.ph-project__register");
  protected WebDriver driver;
  @FindBy(xpath = "//button[@type='submit']")
  private WebElement signInButton;
  @FindBy(xpath = "//nav//a[text()='Почта']")
  private WebElement mailButton;
  @FindBy(xpath = "//button[contains(@class, 'ph-login')]")
  private WebElement loginButton;
  @FindBy(xpath = "//input[@name='username']")
  private WebElement inputUsername;
  @FindBy(xpath = "//button[@data-test-id='auth-screen-vkid-btn']")
  private WebElement continueSignInButton;
  @FindBy(xpath = "//iframe[contains(@class, 'iframe')]")
  private WebElement signInModalWindow;


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

  @Step("Нажать кнопку входа")
  public void clickLoginButton() {
    loginButton.click();
  }

  @Step("Заполнить логин")
  public void fillLoginInfo(String login) {
    inputUsername.sendKeys(login);
  }

  @Step("Нажать кнопку продолжения входа")
  public void clickNext() {
    signInButton.click();
  }

  @Step("Нажать кнопку перехода на страницу аутенфикации")
  public void clickContinueButton() {
    continueSignInButton.click();
  }

  @Step("Перейти в модальное окно входа")
  public void goToSignInWindow() {
    driver.switchTo().frame(signInModalWindow);
  }

}
