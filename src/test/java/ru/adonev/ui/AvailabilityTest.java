package ru.adonev.ui;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.adonev.steps.UiSteps;

@Owner("Egor Adonev <EgorAdonev@github.com>")
@SpringBootTest
// вводит в заблуждение
public class AvailabilityTest {

  @Autowired
  private UiSteps steps;
  @Autowired
  private DriverSetupService setupService;

  @BeforeEach
  public void setup() {
    setupService.setup("chrome");
  }

  @AfterEach
  public void shutdown() {
    setupService.shutdown();
  }

  @Test
  @Epic("Тесты UI")
  @Feature("Доступность ресурса")
  @DisplayName("Доступность ресурса. Ресурс доступен")
  @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
  public void checkAvailability() {
    boolean result = steps.goToMailRuLogIn();
    Assertions.assertTrue(result, "Ресурс недоступен. Тестирование невозможно.");
  }

}
