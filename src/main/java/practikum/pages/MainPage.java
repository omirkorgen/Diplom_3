package practikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MainPage {
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By personalAccountButton = By.xpath(".//p[text() = \"Личный Кабинет\"]");

    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }


}
