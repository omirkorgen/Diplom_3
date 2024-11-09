package pages;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import practikum.EnvConfig;
import practikum.pages.MainPage;
import practikum.pages.PersonalAccountPage;
import practikum.user.User;
import practikum.user.UserChecks;
import practikum.user.UserClient;
import practikum.user.UserService;

@DisplayName("Тест перехода из главной страницы")
public class MainPageTest {
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

    //Переход в личный кабинет
    @Test
    @DisplayName("Переход в личный кабинет")
    public void testGoToPersonalAccount(){
        var mainPage = new MainPage(driver);
        var personalAccount = new PersonalAccountPage(driver);
        mainPage.clickPersonalAccountButton();
        personalAccount.checkProfileIsVisible();
    }
}
