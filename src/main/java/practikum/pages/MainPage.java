package practikum.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import practikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MainPage {
    private final WebDriver driver;
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By personalAccountButton = By.xpath(".//p[text() = \"Личный Кабинет\"]");
    private final By bunsTab = By.cssSelector(".tab_tab__1SPyG:nth-child(1)");
    private final By sauceTab = By.cssSelector(".tab_tab__1SPyG:nth-child(2)");
    private final By fillingTab = By.cssSelector(".tab_tab__1SPyG:nth-child(3)");

    @Step("Клик по кнопке Личный Кабинет")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }
    @Step("Клик по вкладке Булки")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }
    @Step("Клик по вкладке Соусы")
    public void clickSauceTab() {
        driver.findElement(sauceTab).click();
    }
    @Step("Клик по вкладке Начинка")
    public void clickFillingTab() {
        driver.findElement(fillingTab).click();
    }
    @Step("Проверка отображения булок")
    public void checkBunsIsVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text() = \"Булки\"]")));
        assertTrue(driver.findElement(By.xpath("//span[text() = \"Булки\"]")).isDisplayed());
    }
    @Step("Проверка отображения начинок")
    public void checkFillingIsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.attributeContains(fillingTab, "class", "current"));
        String tabClass = driver.findElement(fillingTab).getAttribute("class");
        assertTrue("Вкладка с начинками не выбрана", tabClass.contains("current"));
    }
    @Step("Проверка отображения соусов")
    public void checkSauceIsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.attributeContains(sauceTab, "class", "current"));
        String tabClass = driver.findElement(sauceTab).getAttribute("class");
        assertTrue("Вкладка с соусами не выбрана", tabClass.contains("current"));
    }
    @Step("Проверка отображения булок")
    public void checkBunsIsSelected() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICIT_WAIT))
                .until(ExpectedConditions.attributeContains(bunsTab, "class", "current"));
        String tabClass = driver.findElement(bunsTab).getAttribute("class");
        assertTrue("Вкладка с соусами не выбрана", tabClass.contains("current"));
    }
}
