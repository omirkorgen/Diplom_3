package pages;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practikum.pages.LoginPage;
import practikum.pages.MainPage;
import practikum.pages.PersonalAccountPage;
import practikum.user.User;
import practikum.user.UserChecks;
import practikum.user.UserClient;
import practikum.user.UserService;
import static practikum.EnvConfig.BASE_URL;


public class PersonalAccountTest {
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
        userService = new UserService(client, check, user);
        driver = factory.getDriver();
        factory.login(user);
    }

    @After
    public void tearDown() {
        driver.quit();
        userService.deleteUser();
    }

    //Переход из личного кабинета в конструктор
    @Test
    public void testGoByClickConstructor(){
        var personalAccount = new PersonalAccountPage(driver);
        var mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        personalAccount.checkProfileIsVisible();
        personalAccount.clickConstructorButton();
        mainPage.clickPersonalAccountButton();
    }

    //Переход из личного кабинета на логотип Stellar Burgers
    @Test
    public void testGoByClickLogo(){
        var personalAccount = new PersonalAccountPage(driver);
        var mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        personalAccount.checkProfileIsVisible();
        personalAccount.clickPageLogo();
        mainPage.clickPersonalAccountButton();
    }

    //Выход из аккаунта
    @Test
    public void testLogout(){
        var personalAccount = new PersonalAccountPage(driver);
        var mainPage = new MainPage(driver);
        var loginPage = new LoginPage(driver);
        mainPage.clickPersonalAccountButton();
        personalAccount.checkProfileIsVisible();
        personalAccount.clickLogoutButton();
        loginPage.checkLoginButtonIsVisible();
    }

}
