package pages;

import models.Number;
import models.Product;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import builders.Hooks;
import utils.LoggerUtil;

import static builders.Hooks.driver;


public class CommonPage {
    public CommonPage() {
        PageFactory.initElements(Hooks.getDriver(), this);
    }

    Number number = new Number();
    Product product = new Product();

    @FindBy(how = How.XPATH, using = "//*[@id='onetrust-accept-btn-handler']")
    public WebElement acceptCookiesBtn;

    @FindBy(how = How.XPATH, using = "//*[@class='o-modal__closeButton bwi-close']")
    public WebElement closePopupBtn;

    public void checkUrl(String expectedUrl) {
        LoggerUtil.info("Expected Link: " + expectedUrl + "\nActual Link:" + driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
    }

    public void elementVisible(WebElement element) {
        try {
            element.isDisplayed();
            LoggerUtil.info(element + "is displayed.");
        } catch (Exception ex) {
            LoggerUtil.assertWithLogging(element + " is not displayed");
        }
    }

    public void clickAcceptCookiesButton() {
        acceptCookiesBtn.click();
    }

    public void clickClosePopup() {
        closePopupBtn.click();
    }

    public void clickElement(WebElement element) {
        try {
            element.click();
            LoggerUtil.info(element + "is click successful.");
        } catch (Exception ex) {
            LoggerUtil.assertWithLogging(element + " clicking on the element could not be performed.");
        }
    }
}
