package browser.factory;

import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class BrowserFactory {

  private static WebDriver driver;
  private static final HashMap<String, WebDriver> drivers = new HashMap<>();

  public static WebDriver getBrowser() {
    if (driver == null) {
      throw new NullPointerException("Драйвер - null. Сначала вызовите initBrowser.");
    }
    return driver;
  }

  public static String loadApp(String url) {
    driver.get(url);
    return driver.getCurrentUrl();
  }

  public static void initBrowser(String browser) {
    switch (browser) {
      case "chrome":
        driver = new ChromeDriver();
        drivers.put("chrome", driver);
        break;
      case "firefox":
        driver = new FirefoxDriver();
        drivers.put("firefox", driver);
        break;
      case "ie":
        driver = new InternetExplorerDriver();
        drivers.put("ie", driver);
        break;
      case "safari":
        driver = new SafariDriver();
        drivers.put("safari", driver);
        break;
    }

  }

}
