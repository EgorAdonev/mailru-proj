package ru.adonev.ui.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.adonev.ui.DriverSetupService;
import ru.adonev.ui.elements.Buttons;

@Service
public class UiSteps {
    private final DriverSetupService setupService;

    @Autowired
    public UiSteps(DriverSetupService setupService) {
        this.setupService = setupService;
    }

    @Step("Логин в mail.ru")
    public void login() {
        setupService.setup();
        WebDriver driver = setupService.getDriver();
        driver.get(String.format("%s%s", setupService.getBaseUrl(),
                "/?from=logout&ref=main"));
        WebElement loginButton = driver.findElement(By.xpath(Buttons.LOGIN.locator));
        loginButton.click();
    }

}
