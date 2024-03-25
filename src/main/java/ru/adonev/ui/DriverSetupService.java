package ru.adonev.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

@Service
public class DriverSetupService {
    private WebDriver driver;

    private String baseUrl;

    public void setup() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\IDP_proj\\ui\\src\\main\\resources\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://mail.ru/";
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}