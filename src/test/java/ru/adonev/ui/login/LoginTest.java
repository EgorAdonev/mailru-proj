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

import java.time.LocalDate;

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
        steps.goTo();
    }
    @Test
    @Owner("Egor Adonev")
    @DisplayName("Создание почты")
    @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
    void creationTest() {
        steps.goTo();
        steps.createMail("Egor","Egor",
                LocalDate.of(1999,12,31),"Male","egor39832921",
                new char[]{'p','f','%','d','d','_','j','A','p','o','r','a'},9992324333L);
    }
}
