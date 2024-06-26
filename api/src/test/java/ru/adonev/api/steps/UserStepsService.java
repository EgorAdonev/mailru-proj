package ru.adonev.api.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import ru.adonev.api.model.UserPersona;
import ru.adonev.api.model.UserPersonaNoId;
import ru.adonev.api.repository.UserRepository;

@Service
public class UserStepsService {

  private final ObjectMapper objectMapper = new ObjectMapper();
  @Autowired
  private UserRepository usrRepository;

  @Step("Создание пользователя")
  public long createUser(UserPersonaNoId user, TestRestTemplate restTemplate,
      String createPersonUrl) throws JSONException, JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    JSONObject personJsonObject = new JSONObject();

    personJsonObject.put("firstName", user.firstName());
    personJsonObject.put("lastName", user.lastName());
    personJsonObject.put("age", user.age());
    personJsonObject.put("sex", user.sex());
    personJsonObject.put("money", user.money());

    HttpEntity<String> request = new HttpEntity<>(personJsonObject.toString(), headers);

    UserPersona personResultAsJsonStr = restTemplate.postForObject(createPersonUrl, request,
        UserPersona.class);
    JsonNode root = objectMapper.readTree(personResultAsJsonStr.toString());

    Assertions.assertNotNull(personResultAsJsonStr);
    Assertions.assertNotNull(root);
    Assertions.assertNotNull(root.path("id").asText());
    Assertions.assertEquals(usrRepository.findLastUser() + 1, root.path("id").asLong());
    return personResultAsJsonStr.id();
  }
}
