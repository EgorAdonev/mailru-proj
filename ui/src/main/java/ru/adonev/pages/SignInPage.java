package ru.adonev.pages;

import io.qameta.allure.Link;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Link(url = "https://id.vk.com/auth")
public class SignInPage extends BasePage {

  @FindBy(xpath = "//input[@name='phone']")
  private WebElement phoneInput;
  @FindBy(xpath = "//button[@type='submit']")
  private WebElement signInButton;
  @FindBy(xpath = "//button[data-test-id='continue-as-button']")
  private WebElement continueButton;

  public SignInPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  @Step("Заполнить телефон")
  public void fillPhoneNumber(String phoneNum) {
    phoneInput.sendKeys(phoneNum);
  }

  @Step("Нажать кнопку входа")
  public void clickSignIn() {
    signInButton.click();
  }

  @Step("Нажать кнопку продолжить")
  public void clickContinue() {
    continueButton.click();
  }
}
