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
import ru.adonev.ui.pages.RegisterPage;

@Epic("Тесты UI")
@Feature("Создание")
@Owner("Egor Adonev <EgorAdonev@github.com>")
@SpringBootTest(classes = {UiSteps.class, BrowserService.class, FormSteps.class})
public class CreationTest extends BaseUiTest {

  private static final String EXPECTED_TITLE = "Почта Mail.ru";
  @Autowired
  private UiSteps steps;

  @Test
  @DisplayName("Создание электронного почтового ящика.")
  @Link(name = "MailRu", url = "https://account.mail.ru/signup?from=navi")
  public void creationTest() {
    boolean result = steps.goToMailRuLogIn();
    Assertions.assertTrue(result, "Ресурс недоступен. Тестирование невозможно.");

    String email = String.format("egor-%s@mail.ru", "providers");
    String password = "RIGvZ5d3LQWeHf";
    RegisterPage regPage = steps.createEmailBox(email, "9992324333", password);

    Assertions.assertTrue(regPage.getTitle().contains(EXPECTED_TITLE),
        getErrorMessage(regPage));
  }

  @Test
  @DisplayName("Создание электронного почтового ящика. Негативный кейс. Отсутствие имени ящика")
  @Link(name = "MailRu", url = "https://account.mail.ru/signup?from=navi")
  public void creationTestNegative() {
    boolean result = steps.goToMailRuLogIn();
    Assertions.assertTrue(result, "Ресурс недоступен. Тестирование невозможно.");

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
