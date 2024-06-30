package ru.adonev.api.steps;

import io.qameta.allure.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adonev.api.model.UserPersona;
import ru.adonev.api.service.PropertyApiRequestService;

@Service
public class PropertyStepsService {

  @Autowired
  private PropertyApiRequestService userHttpRequestService;

  @Step("Покупка имущества (привязка имущества к пользователю)")
  public UserPersona sendPropertyPurchaseRequest(String propertyType, UserPersona user,
      long propertyId) {
    return userHttpRequestService.sendHttpRequest(propertyType, user, propertyId);
  }

  @Step("Создание имущества")
  public long createProperty(UserPersona user) {
    return 0;
  }
}
