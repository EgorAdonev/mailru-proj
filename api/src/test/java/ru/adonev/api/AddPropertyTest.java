package ru.adonev.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import java.math.BigDecimal;
import java.net.URI;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import ru.adonev.api.model.UserPersona;
import ru.adonev.api.model.UserPersonaNoId;
import ru.adonev.api.repository.UserRepository;
import ru.adonev.api.steps.PropertyStepsService;
import ru.adonev.api.steps.UserStepsService;

@Epic("Тесты API")
@Feature("Изменение принадлежности имущества")
@Owner("Egor Adonev <EgorAdonev@github.com>")
@SpringBootTest
public class AddPropertyTest {

  private static final String testName = "Johnny";
  private static final String testSecondName = "Doer";
  private static final int testAge = 29;
  private static final String testSex = "MALE";
  private static final int testMoney = 770000;
  private static final String addr = ApiSetupService.getAddr();
  @Autowired
  private PropertyStepsService propertyStepsService;
  @Autowired
  private UserRepository usrRepository;
  @Autowired
  private UserStepsService userStepsService;
  @Autowired
  private TestRestTemplate restTemplate;

  @BeforeEach
  public void setUp() throws JSONException, JsonProcessingException {
    userStepsService.createUser(
        new UserPersonaNoId(testName, testSecondName, testAge, testSex, new BigDecimal(testMoney)),
        restTemplate,
        addr + "/user"
    );
  }

  @Test
  @DisplayName("Операции с имуществом. Успешное приобретение имущества")
  @Link(name = "API стенд", url = "/user/%s/buyCar/%s")
  @Link(name = "API стенд", url = "/user/%s/settle/%s")
  public void addPropertySuccess() throws JsonProcessingException {
    assertThat(
        propertyStepsService.sendPropertyPurchaseRequest("car",
            new UserPersona(usrRepository.findLastUser(), testName, testSecondName, testAge,
                testSex, new BigDecimal(testMoney)),
            restTemplate, URI.create(addr), 1794))
        .isEqualTo(
            new UserPersona(2, "Vasiliy", "Rubenstein", 42, "MALE",
                BigDecimal.valueOf(testMoney).subtract(BigDecimal.valueOf(212.30))));
  }

  @Test
  @DisplayName("Операции с имуществом. Неуспешное приобретение имущества. Нехватка средств")
  @Link(name = "API стенд", url = "/user/%s/buyCar/%s")
  @Link(name = "API стенд", url = "/user/%s/settle/%s")
  public void addPropertyUnsuccess() throws JsonProcessingException {
    long zeroBalance = 0;
    assertThat(
        propertyStepsService.sendPropertyPurchaseRequest("house",
            new UserPersona(usrRepository.findLastUser(), "Antavius", "testSecondName", 21,
                "FEMALE", new BigDecimal(zeroBalance)),
            restTemplate, URI.create(addr), 1794))
        .isEqualTo(
            new UserPersona(2, "Vasiliy", "Rubenstein", 42, "MALE",
                BigDecimal.valueOf(testMoney).subtract(BigDecimal.valueOf(212.30))));
  }

}
