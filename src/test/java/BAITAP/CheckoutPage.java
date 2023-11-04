package BAITAP;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {
    private WebDriver driver;

    private By firstname = By.id("billing:firstname");
    private By middlename = By.id("billing:middlename");
    private By lastname = By.id("billing:lastname");
    private By company = By.id("billing:company");
    private By street1 = By.id("billing:street1");
    private By street2 = By.id("billing:street2");
    private By city = By.id("billing:city");
    private By region_id = By.id("billing:region_id");
    private By zip = By.id("billing:postcode");
    private By country = By.id("billing:country_id");
    private By telephone = By.id("billing:telephone");
    private By fax = By.id("billing:fax");
    private By shipping = By.id("billing:use_for_shipping_no");
    private By continueBtn = By.cssSelector(".button.validation-passed");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement firstName() {
        return driver.findElement(firstname);
    }

    public WebElement middleName() {
        return driver.findElement(middlename);
    }

    public WebElement lastName() {
        return driver.findElement(lastname);
    }
    public WebElement company() {
        return driver.findElement(company);
    }
    public WebElement street1() {
        return driver.findElement(street1);
    }
    public WebElement street2() {
        return driver.findElement(street2);
    }
    public WebElement city() {
        return driver.findElement(city);
    }
    public WebElement region_id() {return driver.findElement(region_id);}
    public WebElement zip() {
        return driver.findElement(zip);
    }
    public WebElement country() {
        return driver.findElement(country);
    }
    public WebElement telephone() {
        return driver.findElement(telephone);
    }
    public WebElement fax() {
        return driver.findElement(fax);
    }
    public WebElement shipping() {
        return driver.findElement(shipping);
    }
    public WebElement continueBtn() {
        return driver.findElement(continueBtn);
    }

    public void inputFirstname(String firstName){
        firstName().sendKeys(firstName);
    }

    public void inputMiddlename(String middleName){
        middleName().sendKeys(middleName);
    }

    public void inputLastname(String lastName){
        lastName().sendKeys(lastName);
    }

    public void inputCompany(String company){
        company().sendKeys(company);
    }

    public void inputStreet1(String street1){
        street1().sendKeys(street1);
    }

    public void inputCity(String city){
        city().sendKeys(city);
    }

    public void inputStreet2(String street2){
        street2().sendKeys(street2);
    }

    public void inputRegion(String region_id){
        new Select(region_id()).selectByVisibleText(region_id);
    }

    public void inputCountry(String country){
        new Select(country()).selectByVisibleText(country);
    }
    public void inputZip(String zip){
        zip().sendKeys(zip);
    }

    public void inputTelephone(String telephone){
        telephone().sendKeys(telephone);
    }
    public void inputFax(String fax){
        fax().sendKeys(fax);
    }
    public void inputShipping(){
        shipping().click();
    }

    public void clickContinueBtn(){
        continueBtn().click();
    }

}
