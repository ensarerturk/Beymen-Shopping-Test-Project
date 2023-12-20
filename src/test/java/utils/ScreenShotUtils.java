package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static builders.Hooks.driver;

public class ScreenShotUtils {

    public static void TakeScreenshot(String testName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Date d = new Date();
        String TimeStamp = d.toString().replace(":", "_").replace(" ", "_");
        try {
            FileUtils.copyFile(srcFile, new File("./Screenshots/" + testName + "_" + TimeStamp + ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
