package ru.adonev.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.adonev.ui.pages.RegisterPage;
import ru.adonev.ui.steps.UiSteps;

@Epic("Тесты UI")
@Feature("Создание")
@Owner("Egor Adonev <EgorAdonev@github.com>")
@SpringBootTest
public class CreationTest {

  @Autowired
  private UiSteps steps;
  @Autowired
  private DriverSetupService setupService;

  @BeforeEach
  public void setup() {
    setupService.setup("chrome");
    boolean result = steps.goToMailRuLogIn();
    Assertions.assertTrue(result, "Ресурс недоступен. Тестирование невозможно.");
  }

  @AfterEach
  public void shutdown() {
    setupService.shutdown();
  }

  @Test
  @DisplayName("Создание электронного почтового ящика. Негативный кейс(недостаточно данных)")
  @Link(name = "MailRu", url = "https://account.mail.ru/signup?from=navi")
  //подумать над модификатором доступа
  public void creationTest() {
    // test data
    // random в кач-ве тестовых данных
    WebDriver driver = setupService.getDriver();
    System.out.println(driver.getTitle());

    String email = String.format("egor-%s@mail.ru","providers");
    String password = "RIGvZ5d3LQWeHf";
    RegisterPage regPage = steps.createEmailBox(email, "9992324333", password);
    System.out.println(regPage.getBaseUrl());
    //верификация
    String expectedTitle = "Почта Mail.ru";
    Assertions.assertTrue(regPage.getTitle().contains(expectedTitle));

  }
}