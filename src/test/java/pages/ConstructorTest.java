package pages;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import practikum.EnvConfig;
import practikum.pages.MainPage;
import practikum.user.User;
import practikum.user.UserChecks;
import practikum.user.UserClient;
import practikum.user.UserService;

@DisplayName("Тест переходов по разделам")
public class ConstructorTest {
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

    //Переход в раздел с Начинками
    @Test
    @DisplayName("Переход в раздел с Начинками")
    public void testFillingIsSelected(){
        var mainPage = new MainPage(driver);
        mainPage.checkBunsIsVisible();
        mainPage.clickFillingTab();
        mainPage.checkFillingIsSelected();
    }

    //Переход в раздел с Соусами
    @Test
    @DisplayName("Переход в раздел с Соусами")
    public void testSauceIsSelected(){
        var mainPage = new MainPage(driver);
        mainPage.checkBunsIsVisible();
        mainPage.clickSauceTab();
        mainPage.checkSauceIsSelected();
    }

    //Переход в раздел с Булками
    @Test
    @DisplayName("Переход в раздел с Булками")
    public void testBunsIsSelected(){
        var mainPage = new MainPage(driver);
        mainPage.checkBunsIsVisible();
        mainPage.clickFillingTab();
        mainPage.checkFillingIsSelected();
        mainPage.clickBunsTab();
        mainPage.checkBunsIsSelected();
    }
}
