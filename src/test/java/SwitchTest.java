import api.UserClient;
import api.UserSteps;
import browser.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PersonalAreaPage;

import java.util.concurrent.TimeUnit;

public class SwitchTest {
    public static String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    public static String password = RandomStringUtils.randomNumeric(6);
    public static String name = RandomStringUtils.randomAlphabetic(10);
    public WebDriver webDriver;
    private Browser browser = new Browser();
    public UserSteps userSteps;
    public String accessToken;

    @Before
    public void setUp() {
        userSteps = new UserSteps(new UserClient());
        ValidatableResponse validatableResponse = userSteps.create(email, password, name);
        accessToken = userSteps.getAccessToken(validatableResponse);
        webDriver = browser.getWebDriver();
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка возможности перехода в личный кабинет")
    public void personalAreaSwitchTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonSignInAccount();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        mainPage.clickButtonPersonalArea();
        Assert.assertTrue(mainPage.accountTextIsVisible());
    }

    @Test
    @DisplayName("Переход в конструктор")
    @Description("Проверка возможности перехода из личного кабинета по клику на «Конструктор»")
    public void constructorSwitchTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonSignInAccount();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        mainPage.clickButtonPersonalArea();
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(webDriver);
        personalAreaPage.clickButtonConstructor();
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверка возможности перехода из личного кабинета по клику на логотип Stellar Burgers")
    public void logoSwitchTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonSignInAccount();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        mainPage.clickButtonPersonalArea();
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(webDriver);
        personalAreaPage.clickButtonLogo();
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }

    @After
    public void close() {
        userSteps.delete();
        webDriver.quit();
    }
}
