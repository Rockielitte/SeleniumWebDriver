package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import java.io.File;

@Test
public class TestCase10 {
    public static void testcase10(){
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try{

            //1. Go to http://live.techpanda.org/index.php/backendlogin
            driver.get("http://live.techpanda.org/index.php/backendlogin");

            //2. Login the credentials provided
            WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
            username.click();
            username.clear();
            username.sendKeys("user01");

            WebElement password = driver.findElement(By.xpath("//input[@id='login']"));
            password.click();
            password.clear();
            password.sendKeys("guru99com");

            driver.findElement(By.xpath("//input[@title='Login']")).click();
            //debug purpose only
            Thread.sleep(2000);

            //3. Go to Sales-> Orders menu
            driver.findElement(By.cssSelector("a[title='close'] span")).click();
            //debug purpose only
            Thread.sleep(2000);

            driver.findElement(By.xpath("//span[normalize-space()='Sales']")).click();
            driver.findElement(By.xpath("//span[normalize-space()='Orders']")).click();


            //4. Input OrderId and FromDate -> ToDate
            WebElement orderId = driver.findElement(By.xpath("//input[@id='sales_order_grid_filter_real_order_id']"));
            orderId.click();
            orderId.clear();
            orderId.sendKeys("100021133");
            //debug purpose only
            Thread.sleep(2000);

            WebElement from = driver.findElement(By.name("created_at[from]"));
            from.click();
            from.clear();
            from.sendKeys("11/6/2023");

            WebElement to = driver.findElement(By.name("created_at[to]"));
            to.click();
            to.clear();
            to.sendKeys("11/8/2023");

            //debug purpose only
            Thread.sleep(2000);

            //5. Click Search button
            driver.findElement(By.xpath("//span[contains(text(),'Search')]")).click();
            //debug purpose only
            Thread.sleep(2000);

            //6. Screenshot capture.
            String dirPath = "screenshots";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdir();
            }
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            File f = new File(dirPath + "/" + screenshot.getName());
            FileHandler.copy(screenshot, f);
            //debug purpose only
            Thread.sleep(2000);

        }catch (Exception e) {
            e.printStackTrace();
        }
        //Quit browser session
        driver.quit();
    }
}
