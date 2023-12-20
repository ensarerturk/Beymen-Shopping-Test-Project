package tests;

import org.junit.BeforeClass;
import org.junit.Rule;
import pages.BasketPage;
import pages.CommonPage;
import pages.HomePage;
import pages.SearchPage;
import utils.ConfigReader;
import utils.FunctionUtils;

import static builders.Hooks.getDriver;

public class CommonTest {

    public static CommonPage commonPage;
    public static HomePage homePage;
    public static BasketPage basketPage;
    public static FunctionUtils functionUtils;
    public static SearchPage searchPage;


    @BeforeClass
    public static void setup() {
        commonPage = new CommonPage();
        homePage = new HomePage();
        basketPage = new BasketPage();
        functionUtils = new FunctionUtils();
        searchPage = new SearchPage();
        getDriver().get(ConfigReader.getProperty("baseURL"));
    }
}
