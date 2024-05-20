package ru.adonev.ui.login;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.adonev.ui.DriverSetupService;
import ru.adonev.ui.steps.UiSteps;

import java.time.LocalDate;

@Epic("Авторизация")
@Feature("Логин")
@Owner("Egor Adonev <EgorAdonev@github.com>")
@SpringBootTest
// вводит в заблуждение
public class LoginTest {
    @Autowired
    private UiSteps steps;
    @Autowired
    private DriverSetupService setupService;

    @BeforeEach
    public void setup() {
        setupService.setup();
    }

    @AfterEach
    public void shutdown() {
        setupService.shutdown();
    }

    @Test
    @DisplayName("Авторизация. Успешная авторизация")// убрать одинаковые аннотации с класса
    @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
    void login() {//указать что goTo mail ru
        int result = steps.goToMailRuLogIn();
        Assertions.assertEquals(0, result);
    }

    @Test
    @DisplayName("Создание электронного письма. Успешный кейс")// создание почтового ящика заменить на создание письма
    // сохрание
    @Link(name = "MailRu", url = "https://mail.ru/?from=logout&ref=main")
        //подумать над модификатором доступа
    void creationTest() {
        //вынести goTo в before
        //test data
        // random в кач-ве тестовых данных
        steps.
        steps.createMail("Egor", "Egor");
                LocalDate.of(1999, 12, 31), "Male",
                "egor39832921"
                //сохранение пароля в файл в зашифрованном виде, а лучше генерация рандом пароля
                , 9992324333L);*/
    }
}
