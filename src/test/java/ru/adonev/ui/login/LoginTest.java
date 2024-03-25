package ru.adonev.ui.login;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.adonev.ui.steps.UiSteps;

@Epic("Авторизация")
@Feature("Логин")
@SpringBootTest
public class LoginTest {
    @Autowired
    private UiSteps steps;

    @Test
    @Owner("Egor Adonev")
    @DisplayName("Тест логина")
    @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
    void loginTest() {
        steps.login();
    }
}
