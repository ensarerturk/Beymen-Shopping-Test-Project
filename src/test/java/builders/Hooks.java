package builders;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import tests.CommonTest;
import utils.LoggerUtil;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Hooks {
    public static WebDriver driver = null;
    private String osName = System.getProperty("os.name");
    private static final int DEFAULT_WAIT = 10;

    @Before
    public void setup() {
        CommonTest.setup();
        getOSNameAndSetProperty();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
        driver.get("https://www.beymen.com/");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().deleteAllCookies();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_WAIT));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(DEFAULT_WAIT));
        }
        return driver;
    }

    private void getOSNameAndSetProperty() {
        if (osName.contains("Windows")) {
            osName = OSName.WINDOWS.toString();
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\ensar\\Downloads\\chromedriver-win64\\chromedriver-win64/chromedriver.exe");
        } else if (System.getProperty("os.name").contains("MAC")) {
            osName = OSName.MAC.toString();
            System.setProperty("webdriver.chrome.driver", "driver/macOS/chrome_107.exe");
        } else {
            LoggerUtil.assertWithLogging("Failed to pair OS with Windows and MAC. || OS Name => " + osName);
        }
    }
}
