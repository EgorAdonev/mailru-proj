package ru.adonev.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Owner("Egor Adonev <EgorAdonev@github.com>")
@Epic("Тесты UI")
@Feature("Доступность ресурса")
//добавить suite
public class AvailabilityTest extends BaseUiTest {

  @Test
  @DisplayName("Доступность ресурса. Ресурс доступен")
  @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
  public void checkAvailability() {
    steps.goToMailRuLogIn();
  }

}
