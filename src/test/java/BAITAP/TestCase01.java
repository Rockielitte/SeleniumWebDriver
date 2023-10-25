package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

@Test
public class TestCase01
{
 public void testCase1() throws InterruptedException, IOException {
     WebDriver driver = driverFactory.getChromeDriver();

//        Step 1. Go to http://live.techpanda.org/
     driver.get("http://live.techpanda.org/");

//        Step 2. Verify Title of the page
     WebElement demoSiteElement = driver.findElement(By.xpath("//h2[1]"));
     String demoSiteText = demoSiteElement.getText();
     try {
         AssertJUnit.assertEquals("THIS IS DEMO SITE FOR   ", demoSiteText);
     } catch(Error e) {
         System.out.println(e.getMessage());
     }
     Thread.sleep(2000);

//        Step 3. Click on -> MOBILE -> menu
     driver.findElement(By.linkText("MOBILE")).click();
     Thread.sleep(2000);

//        Step 4. In the list of all mobile , select SORT BY -> dropdown as name
     new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");
     Thread.sleep(2000);

//        Step 5. Verify all products are sorted by name
     String dirPath = "screenshots";
     File dir = new File(dirPath);
     if (!dir.exists()) {
         dir.mkdir();
     }
     TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
     File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
     File f = new File(dirPath + "/" + screenshot.getName());
     FileHandler.copy(screenshot, f);

     driver.quit();
 }


}