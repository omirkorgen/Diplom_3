package pages;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practikum.pages.LoginPage;
import practikum.pages.MainPage;
import practikum.pages.PersonalAccountPage;
import practikum.user.User;
import practikum.user.UserChecks;
import practikum.user.UserClient;
import practikum.user.UserService;

import static org.junit.Assert.assertTrue;
import static practikum.EnvConfig.BASE_URL;

public class MainPageTest {
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

    //Переход в личный кабинет
    @Test
    public void testGoToPersonalAccount(){
        var mainPage = new MainPage(driver);
        var personalAccount = new PersonalAccountPage(driver);
        mainPage.clickPersonalAccountButton();
        personalAccount.checkProfileIsVisible();
    }


    




}
