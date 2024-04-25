package ru.adonev.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class DriverSetupService {
    private WebDriver driver;

    private String baseUrl;

    public void setup() {
        //получение через resources
        System.setProperty("webdriver.chrome.driver",
                "C:\\IDP_proj\\mailru-proj\\src\\main\\resources\\chromedriver-win64\\chromedriver.exe");
        // ограничение одним браузером- не позволяет тестировать кроссбраузерно или другом
        driver = new ChromeDriver();
        // убрать в properties, в хост
        baseUrl = "https://mail.ru/";
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
