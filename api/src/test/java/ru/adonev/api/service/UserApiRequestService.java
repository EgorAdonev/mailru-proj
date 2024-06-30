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
import org.springframework.stereotype.Service;
import ru.adonev.api.ApiSetupService;
import ru.adonev.api.model.UserPersona;
import ru.adonev.api.model.UserPersonaNoId;
import ru.adonev.api.repository.UserRepository;
import ru.adonev.api.steps.UserStepsService;

@Service
public class UserApiRequestService {

  private final ObjectMapper objectMapper = new ObjectMapper();
  private final String fullUri = ApiSetupService.getAddr();
  @Autowired
  private UserRepository usrRepository;
  @Autowired
  private UserStepsService userStepsService;
  @Autowired
  private TestRestTemplate restTemplate;

  public UserPersona sendHttpRequest(UserPersonaNoId personaNoId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    Charset encoding = StandardCharsets.UTF_8;
    HttpEntity<UserPersonaNoId> request = new HttpEntity<>(personaNoId, headers);

    return restTemplate.postForObject(
        URLEncoder.encode(fullUri + "/user", encoding),
        request,
        UserPersona.class);
  }

  public UserPersona sendHttpRequest(UserPersona personaNoId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    Charset encoding = StandardCharsets.UTF_8;
    HttpEntity<UserPersona> request = new HttpEntity<>(personaNoId, headers);

    return restTemplate.postForObject(
        URLEncoder.encode(fullUri + "/user", encoding),
        request,
        UserPersona.class);
  }

  public UserPersona getUser(UserPersonaNoId personaNoId) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    Charset encoding = StandardCharsets.UTF_8;
    HttpEntity<UserPersonaNoId> request = new HttpEntity<>(personaNoId, headers);
    //    JsonNode root = objectMapper.readTree(personResultAsJsonStr.toString());

    return restTemplate.postForObject(
        URLEncoder.encode(fullUri + "/user", encoding),
        request,
        UserPersona.class);
  }

  public JsonNode parsePersonAsJson(UserPersona persona) throws JsonProcessingException {
    return objectMapper.readTree(persona.toString());
  }

  public long getUserIncrement() {
    return usrRepository.findLastUser() + 1;
  }

}
