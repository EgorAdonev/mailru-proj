package ru.adonev.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import ru.adonev.api.ApiSetupService;
import ru.adonev.api.model.UserPersona;
import ru.adonev.api.repository.UserRepository;
import ru.adonev.api.steps.UserStepsService;

public class PropertyApiRequestService {
  private final ObjectMapper objectMapper = new ObjectMapper();
  @Autowired
  private UserRepository usrRepository;
  @Autowired
  private UserStepsService userStepsService;
  @Autowired
  private TestRestTemplate restTemplate;
  private final String fullUri = ApiSetupService.getAddr();

  public UserPersona sendHttpRequest(String propertyType, UserPersona user, long propertyId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    Charset encoding = StandardCharsets.UTF_8;
    HttpEntity<UserPersona> request = new HttpEntity<>(user, headers);

    String endpoint = String.format("/user/%s/%s/%s", user.id(),
        propertyType.equals("car") ? "/buyCar" : "/settle", propertyId);

    return restTemplate.postForObject(
        URLEncoder.encode(fullUri + endpoint, encoding),
        request,
        UserPersona.class
    );
  }
}
