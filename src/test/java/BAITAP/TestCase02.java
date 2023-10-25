package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.Objects;

public class TestCase02 {
    @Test
    void test() {
        WebDriver driver = driverFactory.getChromeDriver();

//        Step 1. Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

//        2. Click on  MOBILE  menu
        driver.findElement(By.linkText("MOBILE")).click();


//      3. In the list of all mobile , read the cost of Sony Xperia mobile (which is $100)
        String XPerialPrice = driver.findElement(By.cssSelector("span[id='product-price-1'] span[class='price']")).getText();

//        4. Click on Sony Xperia mobile
        driver.findElement(By.id("product-collection-image-1")).click();

//        5. Read the Sony Xperia mobile from detail page.
        String detailPrice = driver.findElement(By.cssSelector("span.price")).getText();

//        6. Compare Product value in list and details page should be equal ($100).
        AssertJUnit.assertEquals(XPerialPrice, detailPrice);
        if (Objects.equals(detailPrice, XPerialPrice)) {
            System.out.println("The price is " + detailPrice);
        }

        driver.quit();
    }
}