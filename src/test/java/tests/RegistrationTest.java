package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
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

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("ТестовыйПользователь", "testuser" + System.currentTimeMillis() + "@mail.ru", "password123");

        LoginPage newLoginPage = new LoginPage(driver);
        assertTrue(newLoginPage.getEmailInput().isDisplayed(), "После успешной регистрации должна отображаться страница входа");
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

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("ТестовыйПользователь", "testuser" + System.currentTimeMillis() + "@mail.ru", "123");

        assertEquals("Некорректный пароль", registerPage.getErrorMessage(), "Должно отображаться сообщение об ошибке для короткого пароля");
    }
}
