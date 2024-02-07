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
import pageobject.RecoveryPassPage;
import pageobject.RegisterPage;

import java.util.concurrent.TimeUnit;

public class LoginTest{
    public static String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    public static String password = RandomStringUtils.randomNumeric(6);
    public static String name = RandomStringUtils.randomAlphabetic(10);
    public WebDriver webDriver;
    public UserSteps userSteps;
    public String accessToken;
    private Browser browser = new Browser();

    @Before
    public void setUp() {
        webDriver = browser.getWebDriver();
        userSteps = new UserSteps(new UserClient());
        ValidatableResponse validatableResponse = userSteps.create(email, password, name);
        accessToken = userSteps.getAccessToken(validatableResponse);
    }

    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка возможности входа по кнопке «Войти в аккаунт» на главной")
    public void loginButtonSignInAccountTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonSignInAccount();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }

    @Test
    @DisplayName("вход по кнопке «Личный кабинет»")
    @Description("Проверка возможности входа по кнопке «Личный кабинет»")
    public void loginButtonPersonalAreaTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonPersonalArea();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }

    @Test
    @DisplayName("вход по кнопке в форме восстановления пароля")
    @Description("Проверка возможности входа по кнопке в форме восстановления пароля")
    public void loginInFormForgotPasswordTest() {
        RecoveryPassPage recoveryPassPage = new RecoveryPassPage(webDriver);
        recoveryPassPage.openPage();
        recoveryPassPage.clickButtonSignIn();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        MainPage mainPage = new MainPage(webDriver);
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }

    @Test
    @DisplayName("вход по кнопке в форме регистрации")
    @Description("Проверка возможности входа по кнопке в форме регистрации")
    public void loginInFormRegisterTest() {
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.openPage();
        registerPage.clickButtonSignIn();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        MainPage mainPage = new MainPage(webDriver);
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }


    @After
    public void close() {
        userSteps.delete();
        webDriver.quit();
    }
}
