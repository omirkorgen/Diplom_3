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
import practikum.pages.MainPage;
import practikum.pages.PersonalAccountPage;
import practikum.user.User;
import practikum.user.UserChecks;
import practikum.user.UserClient;
import practikum.user.UserService;

@DisplayName("Тесты страницы Личный Кабинет")
public class PersonalAccountTest {
    private static WebDriver driver;
    private User user;
    private UserService userService;
    private UserClient client = new UserClient();
    private UserChecks check = new UserChecks();

    @Rule
    public DriverRule factory = new DriverRule();

    @Before
    @Step("Создание и авторизация пользователя")
    public void setUp() {
        user = User.generateUser();
        check.checkCreated(client.createUser(user));
        userService = new UserService(client, check, user);
        driver = factory.getDriver();
        factory.login(user);
        EnvConfig.waitForSeconds(5);
    }

    @After
    @Step("Закрытие браузера и удаление пользователя")
    public void tearDown() {
        driver.quit();
        userService.deleteUser();
    }

    //Переход из личного кабинета в конструктор
    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void testGoByClickConstructor(){
        var personalAccount = new PersonalAccountPage(driver);
        var mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        personalAccount.checkProfileIsVisible();
        EnvConfig.waitForSeconds(5);
        personalAccount.clickConstructorButton();
        mainPage.clickPersonalAccountButton();
    }

    //Переход из личного кабинета на логотип Stellar Burgers
    @Test
    @DisplayName("Переход из личного кабинета на логотип Stellar Burgers")
    public void testGoByClickLogo(){
        var personalAccount = new PersonalAccountPage(driver);
        var mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        personalAccount.checkProfileIsVisible();
        EnvConfig.waitForSeconds(5);
        personalAccount.clickPageLogo();
        mainPage.clickPersonalAccountButton();
    }

    //Выход из аккаунта
    @Test
    @DisplayName("Выход из аккаунта")
    public void testLogout(){
        var personalAccount = new PersonalAccountPage(driver);
        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);
        mainPage.clickPersonalAccountButton();
        personalAccount.checkProfileIsVisible();
        EnvConfig.waitForSeconds(5);
        personalAccount.clickLogoutButton();
        loginPage.checkLoginButtonIsVisible();
    }

}
