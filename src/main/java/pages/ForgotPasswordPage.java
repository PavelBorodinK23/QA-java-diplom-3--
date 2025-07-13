package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='name']")
    private WebElement emailInput;

    @FindBy(xpath = "//button[text()='Восстановить']")
    private WebElement restoreButton;

    @FindBy(xpath = "//a[text()='Войти']")
    private WebElement loginLink;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Ввод email")
    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    @Step("Клик по кнопке 'Восстановить'")
    public void clickRestoreButton() {
        restoreButton.click();
    }

    @Step("Клик по ссылке 'Войти'")
    public void clickLoginLink() {
        loginLink.click();
    }
}
