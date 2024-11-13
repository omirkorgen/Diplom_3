package practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class LoginPage {
    private final WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By loginButtonInMainPage = By.xpath(".//button[text() = \"Войти в аккаунт\"]");
    private final By personalAccountButton = By.xpath("//p[text() = \"Личный Кабинет\"]");
    private final By loginButtonInRegistrationPage = By.xpath("//a[text() = \"Войти\"]");
    private final By loginButtonInRecoveryPage = By.xpath("//a[text() = \"Войти\"]");
    //Elements on Login Page
    private final By loginButton = By.xpath(".//button[text()=\"Войти\"]");
    private final By inputEmail = By.xpath("//label[text()=\"Email\"]/following-sibling::input");
    private final By inputPassword = By.xpath("//input[@name = \"Пароль\"]");

    @Step("Клик по кнопке Войти")
    public void clickLoginButton(By button) {
        driver.findElement(button).click();
    }
    @Step("Заполнение email")
    public void inputEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }
    @Step("Заполнение password")
    public void inputPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }




    //Getter
    public By getLoginButtonInMainPage() {
        return loginButtonInMainPage;
    }

    public By getPersonalAccountButton() {
        return personalAccountButton;
    }

    public By getLoginButtonInRegistrationPage() {
        return loginButtonInRegistrationPage;
    }

    public By getLoginButtonInRecoveryPage() {
        return loginButtonInRecoveryPage;
    }

    public By getLoginButton() {
        return loginButton;
    }

    @Step("Проверка отображения кнопки оформить заказ")
    public void checkCreateOrderIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Оформить заказ\"]")));
        assertTrue(driver.findElement(By.xpath("//button[text()=\"Оформить заказ\"]")).isDisplayed());
    }
    @Step("Проверка отображения кнопки Войти")
    public void checkLoginButtonIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()=\"Войти\"]")));
        assertTrue(driver.findElement(By.xpath("//button[text()=\"Войти\"]")).isDisplayed());
    }

}
