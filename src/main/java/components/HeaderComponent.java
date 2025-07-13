package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderComponent {
    private WebDriver driver;

    @FindBy(xpath = "//p[text()='Личный Кабинет']")
    private WebElement personalAccountButton;

    @FindBy(xpath = "//div[contains(@class, 'AppHeader_header__logo')]")
    private WebElement logo;

    @FindBy(xpath = "//span[text()='Конструктор']")
    private WebElement constructorButton;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickPersonalAccountButton() {
        personalAccountButton.click();
    }

    public void clickLogo() {
        logo.click();
    }

    public void clickConstructorButton() {
        constructorButton.click();
    }
}
