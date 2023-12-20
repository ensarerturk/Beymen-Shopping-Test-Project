package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import utils.FunctionUtils;

import java.io.IOException;

public class HomePage extends CommonPage {

    @FindBy(how = How.XPATH, using = "//img[@alt='Beymen']")
    public WebElement beymenLogo;

    @FindBy(how = How.XPATH, using = "(//*[@class='o-header__search--input'])[last()]")
    public WebElement productSearchBox;

    @FindBy(how = How.XPATH, using = "//*[text()='Ürün sepetinize eklenmiştir.']")
    public WebElement addedToBasketConfirmationText;

    @FindBy(how = How.XPATH, using = "//*[@title='Sepetim']")
    public WebElement showBasketButton;

    public void searchAndSetProductFromExcel(String filePath, String row, String column) throws IOException {
        String searchData = FunctionUtils.getDataFromSheet(filePath, row, column);
        productSearchBox.sendKeys(searchData);
    }
}
