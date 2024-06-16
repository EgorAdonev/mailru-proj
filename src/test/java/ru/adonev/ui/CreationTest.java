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
    setupService.setup();
    int result = steps.goToMailRuLogIn();
    Assertions.assertEquals(0, result);
  }

  @AfterEach
  public void shutdown() {
    setupService.shutdown();
  }

  @Test
  @DisplayName("Создание электронного почтового ящика. Успешный кейс")
  @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
  //подумать над модификатором доступа
  public void creationTest() {
    // test data
    // random в кач-ве тестовых данных
    WebDriver driver = setupService.getDriver();
    System.out.println(driver.getTitle());
    steps.createEmailBox("Egor", 9992324333L);
  }
}
