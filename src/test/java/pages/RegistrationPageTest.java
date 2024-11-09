package pages;

import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practikum.EnvConfig;
import practikum.pages.RegistrationPage;
import practikum.user.*;

import static org.junit.Assert.assertEquals;
import static practikum.EnvConfig.BASE_URL;

@DisplayName("Тесты по регистрации")
public class RegistrationPageTest {
    private static WebDriver driver;
    private User user;
    private UserService userService;
    private UserClient client = new UserClient();
    private UserChecks check = new UserChecks();
    public static final String REGISTRATION_BASE_URL = BASE_URL + "register";

    @Rule
    public DriverRule factory = new DriverRule();

    @Before
    @Step("Создание пользователя")
    public void setUp() {
        user = User.generateUser();
        driver = factory.getDriver();
        userService = new UserService(client, check, user);
    }

    @After
    @Step("Закрытие браузера и удаление пользователя")
    public void tearDown() {
        driver.quit();
        userService.deleteUser();
    }

    //Успешную регистрацию
    @Test
    @DisplayName("Успешную регистрацию")
    public void successfullyRegistrationTest() {
        WebDriver driver = factory.getDriver();
        driver.get(REGISTRATION_BASE_URL);
        var registrationPage = new RegistrationPage(driver);
        registrationPage.inputName(user.getName());
        registrationPage.inputEmail(user.getEmail());
        registrationPage.inputPassword(user.getPassword());
        EnvConfig.waitForSeconds(5);
        registrationPage.clickRegistrationButton();
        registrationPage.checkRegistrationButtonIsDisplayed();
    }

    //Ошибка для некорректного пароля. Минимальный пароль — шесть символов
    @Test
    @DisplayName("Ошибка для некорректного пароля")
    public void incorrectPasswordTest() {
        WebDriver driver = factory.getDriver();
        driver.get(REGISTRATION_BASE_URL);
        var registrationPage = new RegistrationPage(driver);
        registrationPage.inputName(user.getName());
        registrationPage.inputEmail(user.getEmail());
        user.setPassword("wrong");
        registrationPage.inputPassword(user.getPassword());
        EnvConfig.waitForSeconds(5);
        registrationPage.clickRegistrationButton();
        assertEquals("Некорректный пароль", registrationPage.getIncorrectPasswordText());

    }
}