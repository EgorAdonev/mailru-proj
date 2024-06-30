package ru.adonev.pages;

import io.qameta.allure.Step;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuPage extends BasePage {


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
  @FindBy(css = "a.ph-project__register")
  private WebElement registerButton;


  public MailRuPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public String getTitle() {
    return driver.getTitle();
  }

  public WebElement getMailButton() {
    return mailButton;
  }

  public WebElement getRegisterButton() {
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

  @Step("Ожидаем доступность кнопки {selector} для клика")
  public void click() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
  }

}
