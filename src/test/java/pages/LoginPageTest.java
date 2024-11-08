package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practikum.pages.LoginPage;
import practikum.user.User;
import practikum.user.UserChecks;
import practikum.user.UserClient;
import practikum.user.UserService;

import static practikum.EnvConfig.BASE_URL;

public class LoginPageTest {

    private static WebDriver driver;
    private User user;
    private UserService userService;
    private UserClient client = new UserClient();
    private UserChecks check = new UserChecks();

    @Rule
    public DriverRule factory = new DriverRule();

    @Before
    public void setUp() {
        user = User.generateUser();
        check.checkCreated(client.createUser(user));
        driver = new ChromeDriver();
        userService = new UserService(client, check, user);
    }

    @After
    public void tearDown() {
        driver.quit();
        userService.deleteUser();
    }

    //Вход по кнопке «Войти в аккаунт» на главной
    @Test
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
    public void testLoginInRecoveryPage() {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URL + "forgot-password");
        var loginPage = new LoginPage(driver);
        loginPage.clickLoginButton(loginPage.getLoginButtonInRecoveryPage());
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickLoginButton(loginPage.getLoginButton());
        loginPage.checkCreateOrderIsVisible();
    }

    //Вход через кнопку в форме регистрации
    @Test
    public void testLoginInRegistrationPage() {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URL + "register");
        var loginPage = new LoginPage(driver);
        loginPage.clickLoginButton(loginPage.getLoginButtonInRegistrationPage());
        loginPage.inputEmail(user.getEmail());
        loginPage.inputPassword(user.getPassword());
        loginPage.clickLoginButton(loginPage.getLoginButton());
        loginPage.checkCreateOrderIsVisible();
    }

}