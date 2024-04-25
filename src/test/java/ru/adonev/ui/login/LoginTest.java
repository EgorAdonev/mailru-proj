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
@Owner("")
@SpringBootTest
// вводит в заблуждение
public class LoginTest {
    @Autowired
    private UiSteps steps;
    @Test
    @Owner("Egor Adonev")
    // указать то
    @DisplayName("Тест логина")//Использовать нейминг типа 'Авторизация. Успешная авторизация'
    // убрать одинаковые аннотации с класса
    @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
    //убрать слово Test
    void loginTest() {
        //указать что goTo mail ru
        steps.goTo();
        // добавить проверку
    }
    @Test
    @Owner("Egor Adonev")
    @DisplayName("Создание почты")// создание почтового ящика заменить на создание письма
    // сохрание
    @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
    //подумать над модификатором доступа
    void creationTest() {
        //вынести goTo в before
        //cделать
        steps.goTo();
        //test data
        // random в кач-ве тестовых данных
        steps.createMail("Egor","Egor",
                LocalDate.of(1999,12,31),"Male",
                "egor39832921"
                //сохранение пароля в файл в зашифрованном виде
                ,9992324333L);
    }
}
