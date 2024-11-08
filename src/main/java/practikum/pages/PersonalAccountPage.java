package practikum.pages;

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

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickPageLogo() {
        driver.findElement(pageLogo).click();
    }

    public void checkProfileIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = \"Профиль\"]")));
        assertTrue(driver.findElement(By.xpath("//a[text() = \"Профиль\"]")).isDisplayed());
    }
}
