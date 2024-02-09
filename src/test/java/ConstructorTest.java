import browser.Browser;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pageobject.MainPage;

public class ConstructorTest{
    private WebDriver webDriver;
    private Browser browser = new Browser();

    @Before
    public void setUp() {
        webDriver = browser.getWebDriver();
    }

    @Test
    @DisplayName("Переход на раздел Соусы")
    @Description("Проверка перехода на раздел Соусы возвращением текста")
    public void transitionsSectionsSousTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickSectionSouse();
        Assert.assertEquals("Соусы", mainPage.getTextSelectedMenu());
    }

    @Test
    @DisplayName("Переход на раздел Булки")
    @Description("Проверка перехода на раздел Булки возвращением текста")
    public void transitionsSectionsBunsTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickSectionSouse();
        mainPage.clickSectionBuns();
        Assert.assertEquals("Булки", mainPage.getTextSelectedMenu());
    }

    @Test
    @DisplayName("Переход на раздел Начинки")
    @Description("Проверка перехода на раздел Начинки возвращением текста")
    public void transitionsSectionsFillingsTest() {
        MainPage mainPage = new MainPage(webDriver);
        mainPage.openPage();
        mainPage.clickSectionFillings();
        Assert.assertEquals("Начинки", mainPage.getTextSelectedMenu());
    }

    @After
    public void close() {
        webDriver.quit();
    }
}
