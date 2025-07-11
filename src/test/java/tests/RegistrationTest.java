package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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
    private String testEmail;

    @AfterEach
    public void tearDown() {
        if (testEmail != null) {
            // Удаление тестового пользователя через API
            RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
            Response response = RestAssured.given()
                    .contentType("application/json")
                    .body("{\"email\":\"" + testEmail + "\",\"password\":\"password123\"}")
                    .post("/api/auth/login");

            if (response.statusCode() == 200) {
                String token = response.path("accessToken");
                RestAssured.given()
                        .header("Authorization", token)
                        .delete("/api/auth/user");
            }
        }
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

        testEmail = "testuser" + System.currentTimeMillis() + "@mail.ru";
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("ТестовыйПользователь", testEmail, "password123");

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

        testEmail = "testuser" + System.currentTimeMillis() + "@mail.ru";
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("ТестовыйПользователь", testEmail, "123");

        assertEquals("Некорректный пароль", registerPage.getErrorMessage(), "Должно отображаться сообщение об ошибке для короткого пароля");
    }
}
