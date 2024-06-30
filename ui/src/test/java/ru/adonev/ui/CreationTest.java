package ru.adonev.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.adonev.pages.RegisterPage;

@Epic("Тесты UI")
@Feature("Создание")
@Owner("Egor Adonev <EgorAdonev@github.com>")
public class CreationTest extends BaseUiTest {

  private static final String EXPECTED_TITLE = "Почта Mail.ru";

  @Test
  @DisplayName("Создание электронного почтового ящика.")
  @Link(name = "MailRu", url = "https://account.mail.ru/signup?from=navi")
  public void creationTest() {
    steps.goToMailRuLogIn();

    String email = String.format("egor-%s@mail.ru", "devita-veatch");
    String password = "tWQEnPz9sL";
    RegisterPage regPage = steps.createEmailBox(email, "9992324333", password);

    Assertions.assertTrue(regPage.getTitle().contains(EXPECTED_TITLE),
        getErrorMessage(regPage));
  }

  @Test
  @DisplayName("Создание электронного почтового ящика. Негативный кейс. Отсутствие имени ящика")
  @Link(name = "MailRu", url = "https://account.mail.ru/signup?from=navi")
  public void creationTestNegative() {
    steps.goToMailRuLogIn();

    String emptyEmail = "";
    String password = "C5mJcMOaTJ";
    RegisterPage regPage = steps.createEmailBox(emptyEmail, "9992324332", password);

    Assertions.assertTrue(regPage.getTitle().contains(EXPECTED_TITLE),
        getErrorMessage(regPage));
  }

  private String getErrorMessage(RegisterPage regPage) {
    return String.format("Ожидаемое название страницы: %s, фактическое: %s",
        EXPECTED_TITLE, regPage.getTitle());
  }
}
