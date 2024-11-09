package practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class PersonalAccountPage {
    private final WebDriver driver;
    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By constructorButton = By.xpath("//p[text() = \"Конструктор\"]");
    private final By pageLogo = By.xpath("//div[@class=\"AppHeader_header__logo__2D0X2\"]");
    private final By logoutButton = By.xpath("//button[@type = \"button\"]");

    @Step("Клик по кнопке Выхода")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
    @Step("Клик по кнопке Конструктор")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }
    @Step("Клик по логотипу")
    public void clickPageLogo() {
        driver.findElement(pageLogo).click();
    }
    @Step("Проверка отображения профиля")
    public void checkProfileIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = \"Профиль\"]")));
        assertTrue(driver.findElement(By.xpath("//a[text() = \"Профиль\"]")).isDisplayed());
    }
}
