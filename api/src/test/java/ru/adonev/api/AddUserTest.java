package ru.adonev.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import java.math.BigDecimal;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ru.adonev.api.model.UserPersonaNoId;
import ru.adonev.api.repository.UserRepository;
import ru.adonev.api.steps.UserStepsService;

@Epic("Тесты API")
@Feature("Добавление пользователей")
@Owner("Egor Adonev <EgorAdonev@github.com>")
@SpringBootTest
public class AddUserTest {
  @Autowired
  private UserRepository usrRepository;
  @Autowired
  private UserStepsService userStepsService;
  @Autowired
  private TestRestTemplate restTemplate;
  private static final String addr = ApiSetupService.getAddr();

  @Test
  @DisplayName("Операции с пользователями. Успешное добавление пользователя")
  @Link(name = "API стенд", url = "/user")
  public void addUserSuccess() throws JsonProcessingException, JSONException {
    userStepsService.createUser(
        new UserPersonaNoId("testName", "testSecondName", 34, "MALE", new BigDecimal(390000)),
        restTemplate,
        addr + "/user"
    );

  }

}
