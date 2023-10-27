package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class TestCase03  {

    @Test
    public static void testcase3() throws InterruptedException, IOException {
        String dirPath = "screenshots";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        WebDriver driver = driverFactory.getChromeDriver();
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;

//      Step 1. Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

//      2. Click on  MOBILE  menu
        driver.findElement(By.linkText("MOBILE")).click();


//      3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile
        driver.findElement(By.xpath("//li[2]//div[1]//div[3]//button[1]")).click();

//      4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed
        driver.findElement(By.xpath("//input[@title='Qty']")).clear();
        driver.findElement(By.xpath("//input[@title='Qty']")).sendKeys("1000");

        driver.findElement(By.xpath("//button[@title='Update']")).click();
        String errorMsg = driver.findElement(By.cssSelector(".item-msg.error")).getText();

        if (!errorMsg.isEmpty()) {
            System.out.println("ErrorMsg: " + errorMsg);
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            // creates a new File object named f by combining a directory path (dirPath) with the name of the screenshot file
            File f = new File(dirPath + "/" + screenshot.getName());
            FileHandler.copy(screenshot, f);

//          5. Verify the error message
            AssertJUnit.assertEquals("The requested quantity for \"Sony Xperia\" is not available.", errorMsg);
        }

//      6.  Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.
        driver.findElement(By.xpath("//span[contains(text(),'Empty Cart')]")).click();
        String emptyMsg = driver.findElement(By.xpath("//div[@class='cart-empty']")).getText();
        System.out.println("emptyMsg: " + emptyMsg);
        AssertJUnit.assertEquals("SHOPPING CART IS EMPTY", emptyMsg);

        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File f = new File(dirPath + "/" + screenshot.getName());
        FileHandler.copy(screenshot, f);

        driver.quit();

    }
}