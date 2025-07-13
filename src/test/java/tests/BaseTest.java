package tests;

import api.AuthClient;
import config.WebDriverConfig;
import config.YandexWebDriverConfig;
import io.qameta.allure.junit5.AllureJunit5;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

@ExtendWith(AllureJunit5.class)
public class BaseTest {
    protected WebDriver driver;
    protected boolean useYandexBrowser = Boolean.parseBoolean(System.getProperty("yandexBrowser", "false"));

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

    protected User createTestUser() {
        String email = "testuser" + System.currentTimeMillis() + "@mail.ru";
        String password = "password123";
        String name = "TestUser";
        return new User(email, password, name);
    }

    protected String getAccessToken(User user) {
        return AuthClient.login(user)  // Изменено с loginUser на login
                .then()
                .extract()
                .path("accessToken");
    }

    protected void deleteTestUser(String accessToken) {
        if (accessToken != null) {
            AuthClient.delete(accessToken);  // Изменено с deleteUser на delete
        }
    }
}
