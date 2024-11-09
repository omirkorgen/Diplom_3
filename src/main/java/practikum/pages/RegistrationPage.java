package practikum.pages;

import io.qameta.allure.Step;
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
    private final By incorrectPasswordText = By.xpath("//p[text() = \"Некорректный пароль\"]");
    @Step("Заполнение имени")
    public void inputName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    @Step("Заполнение email")
    public void inputEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    @Step("Заполнение пароля")
    public void inputPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    @Step("Получение текста неверного пароля")
    public String getIncorrectPasswordText() {
        return driver.findElement(incorrectPasswordText).getText();
    }

    @Step("Клик по кнопке Регистрации")
    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void checkRegistrationButtonIsDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text() = \"Зарегистрироваться\"]")));
        assertTrue(driver.findElement(By.xpath("//a[text() = \"Зарегистрироваться\"]")).isDisplayed());
    }

}

