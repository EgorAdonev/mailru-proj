package ru.adonev.ui;

import browser.factory.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;
import ru.adonev.ui.utils.Utils;

@Service
public class DriverSetupService {
    private WebDriver driver;

    public void setup(String browser) {
        System.setProperty("webdriver.chrome.driver", Utils.getProperty("webdriver.chrome.driver"));
        // ограничение одним браузером - не позволяет тестировать кроссбраузерно или другом
        // воспользовался фабрикой браузеров
        BrowserFactory.initBrowser(browser);
        driver = BrowserFactory.getBrowser();
    }

    public WebDriver getDriver(){
        return this.driver;
    }

    public void shutdown(){
        if(driver!=null){
            driver.close();
        }
    }
}
