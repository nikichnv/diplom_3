package browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Browser {
    private WebDriver webDriver;
    private String browserName;

    public Browser() {
        this.browserName = System.getProperty("browser");
    }
    public WebDriver getWebDriver() {
        if (browserName == null) {
            browserName = "chrome";
        }
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                webDriver.manage().window().maximize();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                webDriver = new ChromeDriver();
                webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                webDriver.manage().window().maximize();

                break;
        }
        return webDriver;

    }
}
