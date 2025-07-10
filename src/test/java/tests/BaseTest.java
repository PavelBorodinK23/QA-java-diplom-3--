package tests;

import config.WebDriverConfig;
import config.YandexWebDriverConfig;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(AllureJunit5.class)
public class BaseTest {
    protected WebDriver driver;
    protected boolean useYandexBrowser = false;

    @BeforeEach
    public void setUp() {
        if (useYandexBrowser) {
            driver = YandexWebDriverConfig.createYandexDriver();
        } else {
            driver = WebDriverConfig.createChromeDriver();
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
