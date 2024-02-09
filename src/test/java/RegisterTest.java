import browser.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegisterPage;

import java.util.concurrent.TimeUnit;

public class RegisterTest{
    private static String email = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    private static String password = RandomStringUtils.randomNumeric(6);
    private static String incorrectPassword = RandomStringUtils.randomNumeric(5); //генерирует пароль с 5 цифрами для теста с неправильным паролем
    private static String name = RandomStringUtils.randomAlphabetic(10);
    private WebDriver webDriver;
    private Browser browser = new Browser();


    @Before
    public void setUp() {
        webDriver = browser.getWebDriver();
    }

    @Test
    @DisplayName("Регистрация с валидными значениями")
    @Description("Проверка успешной регистрации")
    public void registrationTest() {
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.openPage();
        registerPage.enterFieldName(name);
        registerPage.enterFieldEmail(email);
        registerPage.enterFieldPassword(password);
        registerPage.clickButtonRegistrationInForm();
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openPage();
        loginPage.enterFieldEmail(email);
        loginPage.enterFieldPassword(password);
        loginPage.clickButtonLogin();
        MainPage mainPage = new MainPage(webDriver);
        Assert.assertTrue(mainPage.mainPageIsOpen());
    }

    @Test
    @DisplayName("Регистрация с невалидными значениями")
    @Description("Проверка невозможности зарегистировать пользователя с некорректным паролем ")
    public void errorIncorrectPasswordTest() {
        RegisterPage registerPage = new RegisterPage(webDriver);
        registerPage.openPage();
        registerPage.enterFieldName(name);
        registerPage.enterFieldEmail(email);
        registerPage.enterFieldPassword(incorrectPassword);
        registerPage.clickButtonRegistrationInForm();
        Assert.assertTrue(registerPage.incorrectPasswordLabel());
    }

    @After
    public void tearDown() {
        webDriver.quit();
    }
}
