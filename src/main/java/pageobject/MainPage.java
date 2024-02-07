package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static config.RestConfig.BASE_URI;

public class MainPage {
    private final By sectionIngredientLocator = By.xpath("//*/main/section[1]");
    private final By buttonSignInAccountLocator = By.xpath("//*/main/section[2]/div/button[text()='Войти в аккаунт']");
    private final By buttonPersonalAreaLocator = By.xpath("//*/nav/a/p[text()='Личный Кабинет']");
    private final By accountTextLocator = By.xpath("//*/div/nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By sectionSouseLocator = By.xpath("//*/section[1]/div[1]/div[2]/span[text()='Соусы']");
    private final By sectionBunsLocator = By.xpath("//*/section[1]/div[1]/div[1]/span[text()='Булки']");
    private final By sectionFillingsLocator = By.xpath("//*/section[1]/div[1]/div[3]/span[text()='Начинки']");
    private final By selectedMenu = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");
    private WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть главную страницу сайта")
    public void openPage() {
        webDriver.get(BASE_URI);
    }

    @Step("Убедиться что открыта главная страница сайта")
    public boolean mainPageIsOpen() {
        return webDriver.findElement(sectionIngredientLocator).isDisplayed();
    }

    @Step("Нажать на кнопку войти в аккаунт на главной странице")
    public void clickButtonSignInAccount() {
        WebElement buttonSignInAccount = webDriver.findElement(buttonSignInAccountLocator);
        buttonSignInAccount.click();
    }

    @Step("Нажать на кнопку Личный кабинет на главной странице")
    public void clickButtonPersonalArea() {
        WebElement buttonPersonalArea = webDriver.findElement(buttonPersonalAreaLocator);
        buttonPersonalArea.click();
    }

    @Step("Убедиться что выполнен переход в личный кабинет зарегистрированным пользователем")
    public boolean accountTextIsVisible() {
        return webDriver.findElement(accountTextLocator).isDisplayed();
    }

    @Step("Выполнить переход в конструкторе в раздел Соусы")
    public void clickSectionSouse(){
        WebElement sectionSouse = webDriver.findElement(sectionSouseLocator);
        sectionSouse.click();
    }
    @Step("Выполнить переход в конструкторе в раздел Булки")
    public void clickSectionBuns(){
        WebElement sectionBuns = webDriver.findElement(sectionBunsLocator);
        sectionBuns.click();
    }
    @Step("Выполнить переход в конструкторе в раздел Начинки")
    public void clickSectionFillings(){
        WebElement sectionFillings = webDriver.findElement(sectionFillingsLocator);
        sectionFillings.click();
    }

    @Step("Получить текст выбранного раздела")
    public String getTextSelectedMenu(){
        return webDriver.findElement(selectedMenu).getText();
    }

}
