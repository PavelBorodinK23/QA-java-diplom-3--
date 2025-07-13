package tests;

import api.AuthClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Stellar Burgers")
@Feature("Регистрация")
public class RegistrationTest extends BaseTest {
    private User testUser;
    private String accessToken;

    @AfterEach
    public void tearDown() {
        super.tearDown();
        deleteTestUser(accessToken);
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации нового пользователя")
    @Story("Позитивный сценарий регистрации")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        mainPage.header.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        testUser = createTestUser();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(testUser.getName(), testUser.getEmail(), testUser.getPassword());

        LoginPage newLoginPage = new LoginPage(driver);
        assertTrue(newLoginPage.getEmailInput().isDisplayed(), "После успешной регистрации должна отображаться страница входа");

        accessToken = getAccessToken(testUser);
    }

    @Test
    @DisplayName("Ошибка при некорректном пароле")
    @Description("Проверка отображения ошибки при вводе пароля менее 6 символов")
    @Story("Негативный сценарий регистрации")
    public void testRegistrationWithShortPassword() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        mainPage.header.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        testUser = new User("testuser" + System.currentTimeMillis() + "@mail.ru", "123", "TestUser");
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register(testUser.getName(), testUser.getEmail(), testUser.getPassword());

        assertEquals("Некорректный пароль", registerPage.getErrorMessage(), "Должно отображаться сообщение об ошибке для короткого пароля");
    }
}
