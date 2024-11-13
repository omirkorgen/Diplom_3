package pages;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practikum.EnvConfig;
import practikum.pages.LoginPage;
import practikum.user.User;
import practikum.user.UserChecks;
import practikum.user.UserClient;
import practikum.user.UserService;

import static practikum.EnvConfig.BASE_URL;

@DisplayName("Тесты по входу")
public class LoginPageTest {

    private static WebDriver driver;
    private User user;
    private UserService userService;
    private UserClient client = new UserClient();
    private UserChecks check = new UserChecks();

    @Rule
    public DriverRule factory = new DriverRule();

    @Before
    @Step("Создание пользователя")
    public void setUp() {
        user = User.generateUser();
        check.checkCreated(client.createUser(user));
        driver = factory.getDriver();
        userService = new UserService(client, check, user);
    }

    @After
    @Step("Закрытие браузера и удаление пользователя")
    public void tearDown() {
        driver.quit();
        userService.deleteUser();
    }

    //Вход по кнопке «Войти в аккаунт» на главной
    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void testLoginByButtonLoginToAccountInMainPage() {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URL);
        var loginPage = new LoginPage(driver);
        loginPage.clickLoginButton(loginPage.getLoginButtonInMainPage());
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickLoginButton(loginPage.getLoginButton());
        loginPage.checkCreateOrderIsVisible();
    }

    //Вход через кнопку «Личный кабинет»
    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void testLoginByPersonalAccountButtonInMainPage() {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URL);
        var loginPage = new LoginPage(driver);
        loginPage.clickLoginButton(loginPage.getPersonalAccountButton());
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickLoginButton(loginPage.getLoginButton());
        loginPage.checkCreateOrderIsVisible();
    }

    //Вход через кнопку в форме восстановления пароля
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void testLoginInRecoveryPage() {
        WebDriver driver = factory.getDriver();
        var loginPage = new LoginPage(driver);
        driver.get(BASE_URL + "forgot-password");
        EnvConfig.waitForSeconds(5);
        loginPage.clickLoginButton(loginPage.getLoginButtonInRecoveryPage());
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickLoginButton(loginPage.getLoginButton());
        loginPage.checkCreateOrderIsVisible();
    }

    //Вход через кнопку в форме регистрации
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void testLoginInRegistrationPage() {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URL + "register");
        var loginPage = new LoginPage(driver);
        EnvConfig.waitForSeconds(5);
        loginPage.clickLoginButton(loginPage.getLoginButtonInRegistrationPage());
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickLoginButton(loginPage.getLoginButton());
        loginPage.checkCreateOrderIsVisible();
    }

}