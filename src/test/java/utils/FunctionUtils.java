package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.*;
import java.util.Iterator;

import static builders.Hooks.driver;

public class FunctionUtils {

    public static String getDataFromSheet(String xlsxPath, String row, String column) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(xlsxPath));
        Workbook workbook = new XSSFWorkbook(excelFile);

        try {
            Sheet datatypeSheet = workbook.getSheetAt(0);

            int rowNum = Integer.parseInt(row);
            int colNum = Integer.parseInt(column);

            if (rowNum < 1 || colNum < 1) {
                return "Invalid row or column number.";
            }

            Row currentRow = datatypeSheet.getRow(rowNum - 1);

            if (currentRow == null) {
                return "Specified row is not available.";
            }

            Cell currentCell = currentRow.getCell(colNum - 1);

            if (currentCell == null) {
                return "Specified column not available.";
            }

            return currentCell.getStringCellValue();
        } finally {
            workbook.close();
            excelFile.close();
        }
    }

    public void clearInputField(WebElement element) {
        try {
            Thread.sleep(1000);
            element.clear();
            Thread.sleep(1000);
            LoggerUtil.info(element + " field has been successfully clear.");
        } catch (Exception ex) {
            LoggerUtil.assertWithLogging(element + " field could not be clear.");
        }
    }

    public void pressKey(Keys key){
        Actions actions = new Actions(driver);
        actions.sendKeys(key).build().perform();
    }

    public void saveProductInfoInTxt(WebElement product, WebElement price) throws IOException {
        String productText = product.getText();
        String priceText = price.getText();

        File file = new File("productInfo.txt");

        try (BufferedWriter bWriter = new BufferedWriter(new FileWriter(file, false))) {
            bWriter.write(productText + ": " + priceText);
        }
    }

    public void scrollToElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            LoggerUtil.info(element + " scroll operation successful");
        } catch (Exception ex) {
            LoggerUtil.assertWithLogging(element + " could not be scrolling.");
        }
    }
}
