package ru.adonev.api.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import ru.adonev.api.model.UserPersona;

public class PropertyStepsService {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Step("Покупка имущества (привязка имущества к пользователю)")
  public UserPersona sendPropertyPurchaseRequest(String propertyType, UserPersona user,
      TestRestTemplate restTemplate, URI fullUri, long propertyId)
      throws JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<String> request = new HttpEntity<>(headers);

    String endpoint = String.format("/user/%s/%s/%s", user.id(),
        propertyType.equals("car") ? "/buyCar" : "/settle", 1794);
    UserPersona personResultAsJsonStr = restTemplate.postForObject(
        URLEncoder.encode(fullUri + endpoint, StandardCharsets.UTF_8), request,
        UserPersona.class);
    JsonNode root = objectMapper.readTree(personResultAsJsonStr.toString());
    return personResultAsJsonStr;
  }

  @Step("Создание имущества")
  public long createProperty(UserPersona user) {

    return 0;
  }
}
