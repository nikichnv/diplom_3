package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static config.RestConfig.PROFILE;

public class PersonalAreaPage {
    private final WebDriver webDriver;

    private final By buttonLogoLocator = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By buttonConstructorLocator = By.xpath("//*/li[1]/a/p[text()='Конструктор']");
    private final By buttonExitLocator = By.xpath("//*/li[3]/button[text()='Выход']");

    public PersonalAreaPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу личного кабинета зарегистрированным пользователем")
    public void openPage() {
        webDriver.get(PROFILE);
    }

    @Step("Выполнить переход на главную страницу нажав на логотип")
    public void clickButtonLogo() {
        WebElement buttonLogo = webDriver.findElement(buttonLogoLocator);
        buttonLogo.click();
    }

    @Step("Выполнить переход на главную страницу нажав на кнопку конструктор")
    public void clickButtonConstructor() {
        WebElement buttonConstructor = webDriver.findElement(buttonConstructorLocator);
        buttonConstructor.click();
    }

    @Step("Выполнить выход из учетной записи")
    public void clickButtonExit() {
        WebElement buttonExit = webDriver.findElement(buttonExitLocator);
        buttonExit.click();
    }


}
