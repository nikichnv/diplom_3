package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static config.RestConfig.LOGIN;

public class LoginPage {
    private final By fieldEmailLocator = By.xpath("//fieldset[1]//input");
    private final By fieldPasswordLocator = By.xpath("//fieldset[2]//input");
    private final By buttonLoginLocator = By.xpath("//*/form/button[text()='Войти']");
    private final By labelEntryLocator = By.xpath("//*/div/h2[text()='Вход']");
    private WebDriver webDriver;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу входа")
    public void openPage() {
        webDriver.get(LOGIN);
    }

    @Step("Заполнить поле Email")
    public void enterFieldEmail(String email) {
        WebElement fieldEmail = webDriver.findElement(fieldEmailLocator);
        fieldEmail.sendKeys(email);
    }

    @Step("Заполнить поле Пароль")
    public void enterFieldPassword(String Password) {
        WebElement fieldPassword = webDriver.findElement(fieldPasswordLocator);
        fieldPassword.sendKeys(Password);
    }

    @Step("Нажать на кнопку Войти в форме входа")
    public void clickButtonLogin() {
        WebElement buttonLogin = webDriver.findElement(buttonLoginLocator);
        buttonLogin.click();
    }

    @Step("Убедится что выполнен переход на страницу входа")
    public boolean labelEntryIsVisible() {
        return webDriver.findElement(labelEntryLocator).isDisplayed();
    }

}
