package pages;

import components.HeaderComponent;
import components.BurgerConstructorComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private WebDriver driver;

    @FindBy(xpath = "//button[text()='Оформить заказ']")
    private WebElement orderButton;

    @FindBy(xpath = "//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    public HeaderComponent header;
    public BurgerConstructorComponent constructor;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
        this.constructor = new BurgerConstructorComponent(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isOrderButtonDisplayed() {
        return orderButton.isDisplayed();
    }
}
