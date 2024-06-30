package ru.adonev.service;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.stereotype.Service;
import ru.adonev.utils.Utils;

@Service
//@PropertySource("classpath:src/main/resources/driver.properties")
public class BrowserService {

  private WebDriver driver;

//  @Value("${webdriver.chrome.driver}")
//  private String browserLocation;
//  @Value("${host}")
//  private String host;

  public WebDriver getDriver() {
    return this.driver;
  }

  public String getHost() {
    System.setProperty("webdriver.chrome.driver", Utils.getProperty("host",
        "src/main/resources/driver.properties"));
    return Utils.getProperty("host",
        "src/main/resources/driver.properties");
  }

  public void setup(String browser) {
    System.setProperty("webdriver.chrome.driver", Utils.getProperty("webdriver.chrome.driver",
        "src/main/resources/driver.properties"));
    this.driver = initBrowser(browser);
    this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
  }

  public void close() {
    if (this.driver != null) {
      this.driver.close();
      this.driver = null;
    }
  }

  private WebDriver initBrowser(String browser) {
    return switch (browser) {
      case "chrome" -> new ChromeDriver();
      case "firefox" -> new FirefoxDriver();
      case "ie" -> new InternetExplorerDriver();
      case "safari" -> new SafariDriver();
      default ->
          throw new NoSuchElementException("Браузер %s не поддерживается.".formatted(browser));
    };
  }

}
