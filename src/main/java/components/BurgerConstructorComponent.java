package components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BurgerConstructorComponent {
    private WebDriver driver;

    @FindBy(xpath = "//span[text()='Булки']/parent::div")
    private WebElement bunsTab;

    @FindBy(xpath = "//span[text()='Соусы']/parent::div")
    private WebElement saucesTab;

    @FindBy(xpath = "//span[text()='Начинки']/parent::div")
    private WebElement fillingsTab;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab_type_current')]//span[text()='Булки']")
    private WebElement activeBunsTab;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab_type_current')]//span[text()='Соусы']")
    private WebElement activeSaucesTab;

    @FindBy(xpath = "//div[contains(@class, 'tab_tab_type_current')]//span[text()='Начинки']")
    private WebElement activeFillingsTab;

    public BurgerConstructorComponent(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickBunsTab() {
        bunsTab.click();
    }

    public void clickSaucesTab() {
        saucesTab.click();
    }

    public void clickFillingsTab() {
        fillingsTab.click();
    }

    public boolean isBunsSectionActive() {
        return activeBunsTab.isDisplayed();
    }

    public boolean isSaucesSectionActive() {
        return activeSaucesTab.isDisplayed();
    }

    public boolean isFillingsSectionActive() {
        return activeFillingsTab.isDisplayed();
    }
}