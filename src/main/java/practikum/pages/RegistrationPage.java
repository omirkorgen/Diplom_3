package practikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By nameField = By.xpath("//label[text()=\"Имя\"]/following-sibling::input");
    private final By emailField = By.xpath(".//label[text()=\"Email\"]/following-sibling::input");
    private final By passwordField = By.xpath(".//input[@name=\"Пароль\"]");
    private final By registrationButton = By.xpath(".//button[contains(@class, 'button_button__33qZ0') and text()='Зарегистрироваться']");

    public void inputName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void inputEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }
    public void checkRegistrationButtonIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = \"Зарегистрироваться\"]")));
        assertTrue(driver.findElement(By.xpath("//a[text() = \"Зарегистрироваться\"]")).isDisplayed());
    }

    public void checkIncorrectPasswordTextIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text() = \"Некорректный пароль\"]")));
        assertTrue(driver.findElement(By.xpath("//p[text() = \"Некорректный пароль\"]")).isDisplayed());
    }
}
