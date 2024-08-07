package ru.adonev.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Тесты UI")
@Feature("Создание")
@Feature("Отправка")
@Owner("Egor Adonev <EgorAdonev@github.com>")
public class SendEmailTest extends BaseUiTest {

  @Test
  @DisplayName("Создание электронного письма. Успешный кейс")
  @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
  public void sendEmail() {
    steps.sendEmail("test@mail.ru",
        "Yamaha fabulous kitchen prescribed physician warranty eric, convergence pure visiting. ");
  }
}
