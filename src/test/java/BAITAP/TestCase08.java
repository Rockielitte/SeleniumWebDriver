package BAITAP;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TestCase08 {
    @Test
    public static void testcase08(){
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
            driver.findElement(By.xpath("//a[normalize-space()='My Orders']")).click();

//

            driver.findElement(By.xpath("//a[@class='link-reorder']")).click();

            WebElement quantity= driver.findElement(By.xpath("//input[@title='Qty']"));
            quantity.clear();
            quantity.sendKeys("5");
            driver.findElement(By.cssSelector("button[title='Update'] span span")).click();
//        driver.findElement(By.xpath(" //tr[@class='last even']//td[@class='product-cart-actions']//a[@class='link-wishlist use-ajax'][normalize-space()='Move to wishlist']")).click();


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
            WebElement rate=driver.findElement(By.cssSelector("label[for='s_method_flatrate_flatrate'] span[class='price']"));
            WebElement pretotal=driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(2) > span:nth-child(1)"));
            WebElement totalrate=driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(3) > tr:nth-child(2) > td:nth-child(2) > span:nth-child(1)"));
            WebElement totalgrand=driver.findElement(By.cssSelector("strong span[class='price']"));
            String rateText=rate.getText();
            String prerate=totalrate.getText();
            String grand=totalgrand.getText();

            //9. Select Shipping Cost, Update Total

            AssertJUnit.assertEquals(prerate,rateText);
            //10. Verify shipping cost is added to total

            AssertJUnit.assertEquals("$3,100.00",grand);


            driver.findElement(By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']")).click();


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
            Thread.sleep(3000);
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
