package ru.adonev.ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Тесты UI")
@Feature("Вход")
@Owner("Egor Adonev <EgorAdonev@github.com>")
public class SignInTest extends BaseUiTest {

  @Test
  @DisplayName("Вход в MailRu через VK ID")
  @Link(name = "MailRu", url = "https://account.mail.ru/signup?from=navi")
  @Link(name = "VK ID", url = "https://id.vk.com/auth")
  public void signInTest() {
    steps.goToMailRuLogIn();
    steps.signIn("egor.test.88", "+79953913638");

  }
}
