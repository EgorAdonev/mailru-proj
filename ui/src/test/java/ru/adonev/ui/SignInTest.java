package ru.adonev.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.adonev.service.BrowserService;
import ru.adonev.steps.FormSteps;
import ru.adonev.steps.UiSteps;

@Epic("Тесты UI")
@Feature("Вход")
@Owner("Egor Adonev <EgorAdonev@github.com>")
@SpringBootTest(classes = {UiSteps.class, BrowserService.class, FormSteps.class})
public class SignInTest extends BaseUiTest {

  @Autowired
  private UiSteps steps;

  @Test
  @DisplayName("Вход в MailRu через VK ID")
  @Link(name = "MailRu", url = "https://account.mail.ru/signup?from=navi")
  @Link(name = "VK ID", url = "https://id.vk.com/auth")
  public void signInTest() {
    steps.goToMailRuLogIn();
    steps.signIn("egor.test.88", "+79953913638");

  }
}
