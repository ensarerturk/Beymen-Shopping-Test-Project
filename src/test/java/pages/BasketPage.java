package pages;

import models.Product;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.LoggerUtil;

public class BasketPage extends CommonPage {

    @FindBy(how = How.XPATH, using = "//*[@class='m-basket__header--title' and text()='ALIŞVERİŞ SEPETİM']")
    public WebElement shoppingCartTitle;

    @FindBy(how = How.XPATH, using = "//span[@class='m-basket__productInfoName']")
    public WebElement basketProductName;

    @FindBy(how = How.XPATH, using = "//span[@class='m-productPrice__salePrice']")
    public WebElement basketProductPrice;

    @FindBy(how = How.XPATH, using = "//select[@id='quantitySelect0-key-0']")
    public WebElement itemQuantitySelector;

    @FindBy(how = How.XPATH, using = "//option[@value='2']")
    public WebElement twoProductQuantityChoice;

    @FindBy(how = How.XPATH, using = "(//button[@id='removeCartItemBtn0-key-0'])[1]")
    public WebElement removeProductBtn;

    @FindBy(how = How.XPATH, using = "//*[text()='Sepetinizden ürün başarılı bir şekilde silinmiştir.']")
    public WebElement removedProductSuccessfullyMsg;

    @FindBy(how = How.XPATH, using = "//*[text()='Sepetinizde Ürün Bulunmamaktadır']")
    public WebElement emptyBasketText;

    public void checkProductTextAndPriceExpected() {
        verifyProductTextForBasket(basketProductName, product.getProductName());
        verifyPriceForBasket(basketProductPrice, product.getPrice());
    }

    public void clickProductQuantitySelectionBox() {
        clickElement(itemQuantitySelector);
        LoggerUtil.info("Clicking on the Product Quantity Selection Box is successful.");
    }

    public void clickSecondInProductQuantity() {
        clickElement(twoProductQuantityChoice);
        LoggerUtil.info("Clicking on the second Product Quantity is successful.");
    }

    public void compareProductPrices() throws InterruptedException {
        verifyAndCompareProductPrices(product.getPrice(), basketProductPrice);
    }

    public void seeRemoveProductBtn() {
        elementVisible(removeProductBtn);
    }


    public void removeProductInBasket() {
        clickElement(removeProductBtn);
        LoggerUtil.info("The click for product deletion is successful.");
    }

    public void checkProductDeleted() {
        elementVisible(removedProductSuccessfullyMsg);
    }

    public void checkBasketIsEmpty() {
        elementVisible(emptyBasketText);
    }

    private void verifyProductTextForBasket(WebElement element, String expectedText) {
        String elementText = element.getText();
        LoggerUtil.assertEquals(elementText, expectedText);
    }

    private void verifyPriceForBasket(WebElement productPrice, String expectedPrice) {
        int firstPrice = parsePriceString(expectedPrice);

        String[] priceParts = productPrice.getText().split(",");
        int basketPrice = parsePriceString(priceParts[0]);

        Assert.assertEquals("The initial price of the product and the price added to the cart are not equal.", firstPrice, basketPrice);
    }

    private void verifyAndCompareProductPrices(String oneProductPrice, WebElement twoProductPriceElement) throws InterruptedException {
        Thread.sleep(2000);

        int firstPrice = parsePriceString(oneProductPrice);

        String[] priceParts = twoProductPriceElement.getText().split(",");
        int twoProductPrice = parsePriceString(priceParts[0]);

        // Doubling and controlling prices
        Assert.assertEquals("The initial price of the product is not equal to twice the price of the product added to the cart.", firstPrice * 2, twoProductPrice);
    }

    private int parsePriceString(String priceString) {
        // Remove space, "T", "L", and "." characters
        return Integer.parseInt(priceString.replaceAll(" ", "").replace("T", "")
                .replace("L", "").replace(".", ""));
    }
}
