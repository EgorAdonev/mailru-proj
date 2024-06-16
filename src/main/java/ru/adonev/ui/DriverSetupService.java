package ru.adonev.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;
import ru.adonev.ui.utils.Utils;

@Service
public class DriverSetupService {
    private WebDriver driver;

    private String baseUrl;

    public void setup() {
        System.setProperty("webdriver.chrome.driver", Utils.getProperty("webdriver.chrome.driver"));
        // ограничение одним браузером- не позволяет тестировать кроссбраузерно или другом
        driver = new ChromeDriver();
        baseUrl = Utils.getProperty("host");
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
    public void shutdown(){
        if(driver!=null){
            driver.close();
        }
    }
}
