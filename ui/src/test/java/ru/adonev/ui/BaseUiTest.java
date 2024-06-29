package ru.adonev.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import ru.adonev.service.BrowserService;
@SpringBootTest
@ContextConfiguration(classes = BrowserService.class)
public class BaseUiTest {


  @Autowired
  private BrowserService setupService;

  @BeforeEach
  protected void setup() {
    setupService.setup("chrome");
  }

  @AfterEach
  protected void shutdown() {
    setupService.quit();
  }
}
