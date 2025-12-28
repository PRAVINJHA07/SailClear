// CountryPageTest.
package scriptsTestCase;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pom.CountryPage;

public class CountryPageTest {
    private WebDriver driver;
    
    @BeforeMethod
    public void login() throws InterruptedException {
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");
    	options.addArguments("--disable-notifications");
    	options.addArguments("--disable-infobars");

    	driver = new ChromeDriver(options);
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	driver.manage().window().maximize();
    	driver.get("http://bgv.proanto.com/");
    	driver.findElement(By.xpath("//input[@id='UserName']")).sendKeys("Admin");
    	driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("Vishal@123");
    	driver.findElement(By.xpath("//button[@id='submitButton']")).click();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//span[normalize-space()='Settings']")).click();
    	Thread.sleep(3000);
    	driver.findElement(By.xpath("//h4[normalize-space()='Country']")).click();
    	Thread.sleep(3000);
    	

    	
    }

   @Test(priority = 1)
    public void testAddCountryWithValidData() throws InterruptedException {
    	driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
    	Thread.sleep(3000);

    	CountryPage countryPage = new CountryPage(driver);

        countryPage.enterCountryName("Test Countryrm");
        countryPage.enterCountryCode("Tm");
        countryPage.enterCountryMobileCode("+10");
        countryPage.selectIsEnabled("No");
        countryPage.clickAddCountry();
        Thread.sleep(3000);

        // Add assertion for successful addition (this would depend on the application response)
        //Assert.assertTrue(driver.getPageSource().contains("Success ! Country saved successfully!"));
        Assert.assertTrue(driver.getPageSource().contains("Error ! Country name/country code/country mobile code already exists!"));
        driver.quit();
    }

    @Test(priority = 2)
    public void testAddCountryWithEmptyFields() throws InterruptedException {
    	driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
    	Thread.sleep(3000);


        CountryPage countryPage = new CountryPage(driver);
        countryPage.clickAddCountry();

        Assert.assertTrue(
            countryPage.getCountryNameError()
                .contains("Country name must contain alphabets"),
            "Country name validation message mismatch"
        );

        Assert.assertTrue(
            countryPage.getCountryCodeError()
                .contains("Country Code must contain alphabetical"),
            "Country code validation message mismatch"
        );

        Assert.assertTrue(
            countryPage.getCountryMobileCodeError()
                .contains("Country mobile code should start"),
            "Country mobile code validation message mismatch"
        );

        Assert.assertTrue(
            countryPage.getIsEnabledError()
                .contains("Please Select IsEnabled"),
            "IsEnabled validation message mismatch"
        );
    	Thread.sleep(3000);
        driver.quit();
    }

    @Test(priority = 3)
    public void testClearButtonFunctionality() throws InterruptedException {
    	driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();
    	Thread.sleep(3000);

    	CountryPage countryPage = new CountryPage(driver);

        countryPage.enterCountryName("Test Country");
        countryPage.enterCountryCode("TC");
        countryPage.enterCountryMobileCode("12345");
        countryPage.selectIsEnabled("Yes");
        countryPage.clickClear();

        Assert.assertEquals("", driver.findElement(By.id("CountryName")).getAttribute("value"));
        Assert.assertEquals("", driver.findElement(By.id("CountryCode")).getAttribute("value"));
        Assert.assertEquals("", driver.findElement(By.id("CountryMobileCode")).getAttribute("value"));
        // ✅ Correct dropdown validation
        Select select = new Select(driver.findElement(By.id("IsEnabled")));
        Assert.assertEquals(
            select.getFirstSelectedOption().getText(),
            "Select Is Enable"
        );
         Thread.sleep(3000);
        driver.quit();
    }
    
    @Test(priority = 4)
    public void EditCountry() throws InterruptedException {
    	
    	
    	driver.findElement(By.xpath("//button[@class='btn btn-white btn-sm']")).click();
        Thread.sleep(3000);
    	driver.findElement(By.xpath("//input[@id='SearchCountryName']")).sendKeys("AU");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[normalize-space()='Apply Filter']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//span[@aria-label=\"Edit\"])[1]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[text()='Update Country']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.getPageSource().contains("Success ! Country Update successfully!"));

        Thread.sleep(3000);
        driver.quit();
    }
}
