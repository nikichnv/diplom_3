package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static config.RestConfig.FORGOT_PASSWORD;

public class RecoveryPassPage {

    private final By buttonSignInLocator = By.xpath("//*/div/p/a[text()='Войти']");
    private WebDriver webDriver;

    public RecoveryPassPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Открыть страницу восстановления пароля ")
    public void openPage() {
        webDriver.get(FORGOT_PASSWORD);
    }

    @Step("Выполнить переход на страницу входа нажав на кнопку Войти")
    public void clickButtonSignIn() {
        WebElement buttonSignIn = webDriver.findElement(buttonSignInLocator);
        buttonSignIn.click();
    }


}
