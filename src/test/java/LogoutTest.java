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

public class LogoutTest{
    private static String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    private static String password = RandomStringUtils.randomNumeric(6);
    private static String name = RandomStringUtils.randomAlphabetic(10);
    public WebDriver webDriver;
    private Browser browser = new Browser();
    private UserSteps userSteps;
    private String accessToken;

    @Before
    public void setUp() {
        webDriver = browser.getWebDriver();
        userSteps = new UserSteps(new UserClient());
        ValidatableResponse validatableResponse = userSteps.create(email, password, name);
        accessToken = userSteps.getAccessToken(validatableResponse);
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка возможности выхода по кнопке «Выйти» в личном кабинете")
    public void logoutTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickButtonPersonalArea();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        mainPage.clickButtonPersonalArea();
        PersonalAreaPage personalAreaPage = new PersonalAreaPage(webDriver);
        personalAreaPage.clickButtonExit();
        Assert.assertTrue(loginPage.labelEntryIsVisible());
    }

    @After
    public void close() {
        userSteps.delete();
        webDriver.quit();
    }
}
