package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private WebDriver driver;

    @FindBy(xpath = "//fieldset[contains(., 'Имя')]//input")
    private WebElement nameInput;

    @FindBy(xpath = "//fieldset[contains(., 'Email')]//input")
    private WebElement emailInput;

    @FindBy(xpath = "//fieldset[contains(., 'Пароль')]//input")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = "//p[contains(@class, 'input__error')]")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод имени")
    public void setName(String name) {
        nameInput.sendKeys(name);
    }

    @Step("Ввод email")
    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    @Step("Ввод пароля")
    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        loginLink.click();
    }

    @Step("Получение сообщения об ошибке")
    public String getErrorMessage() {
        return errorMessage.getText();
    }

    @Step("Регистрация пользователя")
    public void register(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }
}
