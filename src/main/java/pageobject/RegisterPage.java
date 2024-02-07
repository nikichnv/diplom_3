package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static config.RestConfig.REGISTRATION;

public class RegisterPage {
    private final By buttonSignInLocator = By.xpath("//*/div/p/a[text()='Войти']");
    private final By fieldNameLocator = By.xpath("//fieldset[1]//input");
    private final By fieldEmailLocator = By.xpath("//fieldset[2]//input");
    private final By fieldPasswordLocator = By.xpath("//fieldset[3]//input");
    private final By buttonRegistrationInFormLocator = By.xpath("//form/button[text()='Зарегистрироваться']");
    private final By incorrectPasswordLabelLocator = By.xpath("//*/fieldset[3]/div/p[text()='Некорректный пароль']");
    private WebDriver webDriver;

    public RegisterPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void openPage() {
        webDriver.get(REGISTRATION);
    }

    @Step("Нажать на кнопку войти в форме регистрации")
    public void clickButtonSignIn() {
        WebElement buttonSignIn = webDriver.findElement(buttonSignInLocator);
        buttonSignIn.click();
    }

    @Step("Заполнить поле имя")
    public void enterFieldName(String name) {
        WebElement fieldName = webDriver.findElement(fieldNameLocator);
        fieldName.sendKeys(name);
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

    @Step("Нажать на кнопку Зарегистрироваться в форме регистрации")
    public void clickButtonRegistrationInForm() {
        WebElement buttonRegistrationInForm = webDriver.findElement(buttonRegistrationInFormLocator);
        buttonRegistrationInForm.click();
    }

    @Step("Убедится в видимости лейбла невалидного пароля")
    public boolean incorrectPasswordLabel() {
        return webDriver.findElement(incorrectPasswordLabelLocator).isDisplayed();
    }
}
