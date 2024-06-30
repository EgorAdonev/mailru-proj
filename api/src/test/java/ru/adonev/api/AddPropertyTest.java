package ru.adonev.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import java.math.BigDecimal;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.adonev.api.model.UserPersona;
import ru.adonev.api.repository.UserRepository;
import ru.adonev.api.steps.PropertyStepsService;
import ru.adonev.api.steps.UserStepsService;

@Epic("Тесты API")
@Feature("Изменение принадлежности имущества")
@Owner("Egor Adonev <EgorAdonev@github.com>")
@SpringBootTest()
public class AddPropertyTest {

  public static final int testMoney = 770000;
  private static final String addr = ApiSetupService.getAddr();
  @Autowired
  private PropertyStepsService propertyStepsService;
  @Autowired
  private UserStepsService userStepsService;
  @Autowired
  private UserRepository usrRepository;

  @BeforeEach
  public void setUp() throws JSONException, JsonProcessingException {
    userStepsService.createUser(userStepsService.getUserForTest());
  }

  @Test
  @DisplayName("Операции с имуществом. Успешное приобретение имущества")
  @Link(name = "API стенд", url = "/user/%s/buyCar/%s")
  @Link(name = "API стенд", url = "/user/%s/settle/%s")
  public void addPropertySuccess() throws JSONException {
    UserPersona testUser = userStepsService.getUserForTest();
    propertyStepsService.sendPropertyPurchaseRequest("car", testUser, 1794);
    UserPersona expected = new UserPersona(2, "Vasiliy", "Rubenstein", 42, "MALE",
        BigDecimal.valueOf(testMoney).subtract(BigDecimal.valueOf(212.30)));
    assertThat(testUser).isEqualTo(expected);

  }

  @Test
  @DisplayName("Операции с имуществом. Неуспешное приобретение имущества. Нехватка средств")
  @Link(name = "API стенд", url = "/user/%s/buyCar/%s")
  @Link(name = "API стенд", url = "/user/%s/settle/%s")
  public void addPropertyUnsuccess() throws JSONException {
    long zeroBalance = 0;
    UserPersona testUser = userStepsService.getUserForTest();
    propertyStepsService.sendPropertyPurchaseRequest("house", testUser, 1794);
    UserPersona user = new UserPersona(usrRepository.findLastUser(), "Antavius", "testSecondName",
        21,
        "FEMALE", new BigDecimal(zeroBalance));
    assertThat(propertyStepsService.sendPropertyPurchaseRequest("house", user, 1794))
        .isEqualTo(user);
  }

}
