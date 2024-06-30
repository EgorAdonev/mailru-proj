package ru.adonev.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.adonev.service.BrowserService;
import ru.adonev.steps.FormSteps;
import ru.adonev.steps.UiSteps;

@Owner("Egor Adonev <EgorAdonev@github.com>")
@Epic("Тесты UI")
@Feature("Доступность ресурса")
@SpringBootTest(classes = {UiSteps.class, BrowserService.class, FormSteps.class})
public class AvailabilityTest extends BaseUiTest {

  @Autowired
  private UiSteps steps;

  @Test
  @DisplayName("Доступность ресурса. Ресурс доступен")
  @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
  public void checkAvailability() {
    boolean result = steps.goToMailRuLogIn();
    Assertions.assertTrue(result, "Ресурс недоступен. Тестирование невозможно.");
  }

}
