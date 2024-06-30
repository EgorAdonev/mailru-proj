package ru.adonev.api.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import java.math.BigDecimal;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adonev.api.model.UserPersona;
import ru.adonev.api.model.UserPersonaNoId;
import ru.adonev.api.repository.UserRepository;
import ru.adonev.api.service.UserApiRequestService;

@Service
public class UserStepsService {

  private static final String testName = "Johnny";
  private static final String testSecondName = "Doer";
  private static final int testAge = 29;
  private static final String testSex = "MALE";
  public static final int testMoney = 770000;
  private final ObjectMapper objectMapper = new ObjectMapper();
  @Autowired
  private UserRepository usrRepository;
  @Autowired
  private UserApiRequestService userService;

  @Step("Создание пользователя")
  public long createUser(UserPersonaNoId user) throws JsonProcessingException {
    UserPersona userPersona = userService.sendHttpRequest(user);
    Assertions.assertNotNull(userPersona);
    JsonNode actualPerson = userService.parsePersonAsJson(userPersona);
    Assertions.assertNotNull(actualPerson);
    Assertions.assertNotNull(actualPerson.path("id").asText());
    Assertions.assertEquals(userService.getUserIncrement(), actualPerson.path("id").asLong());
    return userPersona.id();
  }
  @Step("Создание пользователя c ИД")
  public long createUser(UserPersona user) throws JsonProcessingException {
    UserPersona userPersona = userService.sendHttpRequest(user);
    Assertions.assertNotNull(userPersona);
    JsonNode actualPerson = userService.parsePersonAsJson(userPersona);
    Assertions.assertNotNull(actualPerson);
    Assertions.assertNotNull(actualPerson.path("id").asText());
    Assertions.assertEquals(userService.getUserIncrement(), actualPerson.path("id").asLong());
    return userPersona.id();
  }

  @Step("Получения пользователя для теста")
  public UserPersona getUserForTest() throws JSONException {
    return new UserPersona(userService.getUserIncrement(), testName, testSecondName, testAge, testSex, new BigDecimal(testMoney));
  }
}
