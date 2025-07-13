package tests;

import api.AuthClient;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.ForgotPasswordPage;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Stellar Burgers")
@Feature("Вход в систему")
public class LoginTest extends BaseTest {
    private User testUser;
    private String accessToken;

    @BeforeEach
    public void setUp() {
        super.setUp();
        testUser = createTestUser();
        AuthClient.register(testUser); // Изменено с registerUser на register
    }

    @AfterEach
    public void tearDown() {
        deleteTestUser(accessToken);
        super.tearDown();
    }

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    @Description("Проверка входа через кнопку на главной странице")
    @Story("Сценарии входа в систему")
    public void testLoginViaMainPageButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        accessToken = getAccessToken(testUser);
        assertTrue(mainPage.isOrderButtonDisplayed(), "После успешного входа должна отображаться кнопка оформления заказа");
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа через кнопку в личном кабинете")
    @Story("Сценарии входа в систему")
    public void testLoginViaPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.header.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(testUser.getEmail(), testUser.getPassword());

        accessToken = getAccessToken(testUser);
        assertTrue(mainPage.isOrderButtonDisplayed(), "После успешного входа должна отображаться кнопка оформления заказа");
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через ссылку на странице регистрации")
    @Story("Сценарии входа в систему")
    public void testLoginViaRegisterForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.header.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.clickLoginLink();

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        accessToken = getAccessToken(testUser);
        assertTrue(mainPage.isOrderButtonDisplayed(), "После успешного входа должна отображаться кнопка оформления заказа");
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через ссылку на странице восстановления пароля")
    @Story("Сценарии входа в систему")
    public void testLoginViaForgotPasswordForm() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.header.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickForgotPasswordLink();

        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.clickLoginLink();

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        accessToken = getAccessToken(testUser);
        assertTrue(mainPage.isOrderButtonDisplayed(), "После успешного входа должна отображаться кнопка оформления заказа");
    }
}
