package pages;

import models.Number;
import models.Product;
import org.hamcrest.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.LoggerUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static builders.Hooks.driver;
import static tests.CommonTest.functionUtils;

public class SearchPage extends CommonPage {


    @FindBy(how = How.XPATH, using = "//*[@id='productListTitle']")
    public WebElement productListTitle;
    @FindBy(how = How.XPATH, using = "//*[@id='productList']/div//span[@class='m-productCard__desc']")
    public List<WebElement> productName;
    @FindBy(how = How.XPATH, using = "//*[@id='productList']/div//span[@class='m-productCard__newPrice']")
    public List<WebElement> productPrice;
    @FindBy(how = How.XPATH, using = "//div[@class='m-productCard__stockCartIcon']")
    public List<WebElement> productCart;
    @FindBy(how = How.XPATH, using = "(//*[contains(@class,'m-variation__item') and not(contains(@class,'-disabled'))])[1]")
    public WebElement sSizeProduct;
    @FindBy(how = How.XPATH, using = "//button[@id='addBasket']")
    public WebElement addToBasketButton;

    public void seeShirtSearch() {
        productListTitle.isDisplayed();
    }

    public void selectAndSetRandomProduct() {
        int size = ThreadLocalRandom.current().nextInt(1, productPrice.size());
        number.setRandomNumber(size);
    }

    public void saveProductInfoAndPrice() throws IOException {
        setProductNameAndPrice();
        functionUtils.saveProductInfoInTxt(productName.get(number.getRandomNumber()), productPrice.get(number.getRandomNumber()));
    }

    public void findAndClickRandomProduct() {
        WebElement webElement = productCart.get(number.getRandomNumber());
        webElement.findElement(By.xpath(".")).click();
    }

    public void sSizeSelection() {
        sSizeProduct.click();
    }

    public void addBasketProduct() {
        addToBasketButton.click();

    }

    private void setProductNameAndPrice() {
        WebElement productNameElement = productName.get(number.getRandomNumber());
        WebElement productPriceElement = productPrice.get(number.getRandomNumber());

        if (productNameElement != null && productPriceElement != null) {
            product.setProductName(productNameElement.getText());
            product.setPrice(productPriceElement.getText());
        } else {
            LoggerUtil.assertWithLogging("productNameElement or productPriceElement is null.");
        }
    }
}
