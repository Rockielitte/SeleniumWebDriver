package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import BAITAP.CheckoutPage;

import java.io.File;
import java.io.IOException;

@Test
public class TestCase06 {
    public static void testcase06(){
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();

        try{

            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");

            //2. Click on my account link
            driver.findElement(By.xpath("//span[@class='label'][normalize-space()='Account']")).click();
            driver.findElement(By.xpath("//div[@id='header-account']//a[@title='My Account'][normalize-space()='My Account']")).click();

            //3. Login in application using previously created credential
            driver.findElement(By.id("email")).sendKeys("quangtnse171198@gmail.com");
            driver.findElement(By.id("pass")).sendKeys("123456");
            driver.findElement(By.id("send2")).click();

            //4. Click on MY WISHLIST link
            driver.findElement(By.xpath("//div[@class='block-content']//a[normalize-space()='My Wishlist']")).click();


            //5. In next page, Click ADD TO CART link

            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@title='Add to Cart']")).click();
            Thread.sleep(2000);
            WebElement quantity= driver.findElement(By.xpath("//input[@title='Qty']"));
            quantity.clear();
            quantity.sendKeys("1");
            driver.findElement(By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]")).click();
            Thread.sleep(2000);

            //6. Enter general shipping country, state/province and zip for the shipping cost estimate
            new Select(
                    driver.findElement(By.xpath("//select[@id='country']"))
            ).selectByVisibleText("United States");
            new Select(
                    driver.findElement(By.xpath("//select[@id='region_id']"))
            ).selectByVisibleText("Alabama");
            WebElement postCode=driver.findElement(By.xpath("//input[@id='postcode']"));
            postCode.clear();
            postCode.sendKeys("1111");

            //7. Click Estimate
            driver.findElement(By.xpath("//span[contains(text(),'Estimate')]")).click();
            Thread.sleep(2000);

            //8. Verify Shipping cost generated
            driver.findElement(By.xpath("//input[@id='s_method_flatrate_flatrate']")).click();
            driver.findElement(By.xpath("//span[contains(text(),'Update Total')]")).click();
            Thread.sleep(2000);
            WebElement rate=driver.findElement(By.xpath("//label[@for='s_method_flatrate_flatrate']//span[@class='price'][normalize-space()='$5.00']"));
            WebElement pretotal=driver.findElement(By.xpath("//td[@class='a-right']//span[@class='price'][normalize-space()='$615.00']"));
            WebElement totalrate=driver.findElement(By.xpath("//td[@class='a-right']//span[@class='price'][normalize-space()='$5.00']"));
            WebElement totalgrand=driver.findElement(By.xpath("//span[normalize-space()='$620.00']"));
            String rateText=rate.getText();
            String prerate=totalrate.getText();
            String grand=totalgrand.getText();

            //9. Select Shipping Cost, Update Total

            AssertJUnit.assertEquals(prerate,rateText);
            //10. Verify shipping cost is added to total

            AssertJUnit.assertEquals("$620.00",grand);

            //11. Click "Proceed to Checkout"
            driver.findElement(By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']")).click();

            //12a. Enter Billing Information, and click Continue
            new Select(driver.findElement(By.cssSelector("#billing-address-select"))).selectByVisibleText("New Address");
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.inputFirstname("lin");
            checkoutPage.inputMiddlename("lin");
            checkoutPage.inputLastname("lin");
            checkoutPage.inputCompany("fpt");
            checkoutPage.inputStreet1("asdfg");
            checkoutPage.inputStreet2("asdfg");
            checkoutPage.inputCity("California");
//            Thread.sleep(3000);
            checkoutPage.inputRegion("California");
//            Thread.sleep(1000);
            checkoutPage.inputZip("1111");
            checkoutPage.inputCountry("United States");
            checkoutPage.inputTelephone("0987654321");
            checkoutPage.inputFax("11223344");
            checkoutPage.inputShipping();
            checkoutPage.clickContinueBtn();

            //12b. Enter Shipping Information, and click Continue
            Thread.sleep(2000);
            driver.findElement(By.xpath("//button[@onclick='shipping.save()']")).click();
            Thread.sleep(2000);
            // 13. In Shipping Method, Click Continue
            WebElement element = driver.findElement(By.xpath("//button[@onclick='shippingMethod.save()']//span//span[contains(text(),'Continue')]"));
            element.click();
            Thread.sleep(2000);
            // 14. In Payment Information select 'Check/Money Order' radio button. Click Continue

            element = driver.findElement(By.xpath("//label[@for='p_method_checkmo']"));
            element.click();
            Thread.sleep(2000);
            element = driver.findElement(By.xpath("//button[@onclick='payment.save()']"));
            element.click();
            Thread.sleep(2000);
            // 15. Click 'PLACE ORDER' button
            element = driver.findElement(By.xpath("//button[@title='Place Order']"));
            element.click();




        }catch (Exception e) {
            e.printStackTrace();
        }
        //Quit browser session
        driver.quit();
    }
}
