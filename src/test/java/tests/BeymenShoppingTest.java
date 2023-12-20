package tests;

import org.junit.Test;
import org.openqa.selenium.Keys;
import utils.ConfigReader;
import utils.ScreenShotUtils;

import java.io.IOException;

public class BeymenShoppingTest extends CommonTest{

    @Test
    public void beymenShoppingTest() throws IOException, InterruptedException {
        commonPage.checkUrl(ConfigReader.getProperty("baseURL"));
        commonPage.elementVisible(commonPage.acceptCookiesBtn);
        commonPage.clickAcceptCookiesButton();
        commonPage.clickClosePopup();

        commonPage.elementVisible(homePage.beymenLogo);
        commonPage.elementVisible(homePage.productSearchBox);
        homePage.searchAndSetProductFromExcel("./product.xlsx",  "1", "1");
        ScreenShotUtils.TakeScreenshot("searchSort");

        functionUtils.clearInputField(homePage.productSearchBox);
        homePage.searchAndSetProductFromExcel("./product.xlsx",  "1", "2");
        ScreenShotUtils.TakeScreenshot("searchShirt");

        functionUtils.pressKey(Keys.ENTER);
        searchPage.seeShirtSearch();
        searchPage.selectAndSetRandomProduct();
        searchPage.saveProductInfoAndPrice();
        searchPage.findAndClickRandomProduct();

        commonPage.elementVisible(searchPage.sSizeProduct);
        searchPage.sSizeSelection();
        ScreenShotUtils.TakeScreenshot("selectProductAndAddBasket");
        searchPage.addBasketProduct();

        commonPage.elementVisible(homePage.addedToBasketConfirmationText);
        ScreenShotUtils.TakeScreenshot("productSuccessfullyAddedToTheCart");

        functionUtils.scrollToElement(homePage.showBasketButton);
        commonPage.clickElement(homePage.showBasketButton);
        commonPage.elementVisible(basketPage.shoppingCartTitle);

        basketPage.checkProductTextAndPriceExpected();
        basketPage.clickProductQuantitySelectionBox();
        basketPage.clickSecondInProductQuantity();
        basketPage.compareProductPrices();
        ScreenShotUtils.TakeScreenshot("comparisonProcessSuccessful");

        basketPage.seeRemoveProductBtn();
        basketPage.removeProductInBasket();
        basketPage.checkProductDeleted();
        ScreenShotUtils.TakeScreenshot("deletedProcessSuccessful");

        basketPage.checkBasketIsEmpty();
        ScreenShotUtils.TakeScreenshot("basketIsEmpty");
    }

}
