package pages;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import practikum.pages.RegistrationPage;
import practikum.user.*;

import static org.junit.Assert.assertEquals;
import static practikum.EnvConfig.BASE_URL;

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
    public void setUp() {
        user = User.generateUser();
        driver = new ChromeDriver();
        userService = new UserService(client, check, user);
    }

    @After
    public void tearDown() {
        driver.quit();
        userService.deleteUser();
    }

    //Успешную регистрацию
    @Test
    public void successfullyRegistrationTest() {
        WebDriver driver = factory.getDriver();
        driver.get(REGISTRATION_BASE_URL);
        var registrationPage = new RegistrationPage(driver);
        registrationPage.inputName(user.getName());
        registrationPage.inputEmail(user.getEmail());
        registrationPage.inputPassword(user.getPassword());
        registrationPage.clickRegistrationButton();
        registrationPage.checkRegistrationButtonIsDisplayed();
    }

    //Ошибка для некорректного пароля. Минимальный пароль — шесть символов
    @Test
    public void incorrectPasswordTest() {
        WebDriver driver = factory.getDriver();
        driver.get(REGISTRATION_BASE_URL);
        var registrationPage = new RegistrationPage(driver);
        registrationPage.inputName(user.getName());
        registrationPage.inputEmail(user.getEmail());
        user.setPassword("wrong");
        registrationPage.inputPassword(user.getPassword());
        registrationPage.clickRegistrationButton();
        assertEquals("Некорректный пароль", registrationPage.getIncorrectPasswordText());

    }
}