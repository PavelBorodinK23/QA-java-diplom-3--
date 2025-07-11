package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Stellar Burgers")
@Feature("Конструктор")
public class ConstructorTest extends BaseTest {
    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка перехода к разделу с булками")
    @Story("Навигация по конструктору")
    public void testNavigateToBunsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        mainPage.constructor.clickSaucesTab();
        assertTrue(mainPage.constructor.isSaucesSectionDisplayed(), "Должен отображаться раздел с соусами");

        mainPage.constructor.clickBunsTab();
        assertTrue(mainPage.constructor.isBunsSectionDisplayed(), "Должен отображаться раздел с булками");
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу с соусами")
    @Story("Навигация по конструктору")
    public void testNavigateToSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        mainPage.constructor.clickSaucesTab();
        assertTrue(mainPage.constructor.isSaucesSectionDisplayed(), "Должен отображаться раздел с соусами");
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу с начинками")
    @Story("Навигация по конструктору")
    public void testNavigateToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();

        mainPage.constructor.clickFillingsTab();
        assertTrue(mainPage.constructor.isFillingsSectionDisplayed(), "Должен отображаться раздел с начинками");
    }
}
