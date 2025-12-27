package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CountryPage {

    private WebDriver driver;

    // Locators
    private By countryNameField = By.id("CountryName");
    private By countryCodeField = By.id("CountryCode");
    private By countryMobileCodeField = By.id("CountryMobileCode");
    private By isEnabledDropdown = By.id("IsEnabled");   // ✅ FIXED
    private By addCountryButton = By.id("btncountry");
    private By clearButton = By.xpath("//button[text()='Clear']");

    private By countryNameError = By.cssSelector(".CountryName");
    private By countryCodeError = By.cssSelector(".CountryCode");
    private By countryMobileCodeError = By.cssSelector(".CountryMobileCode");
    private By isEnabledError = By.cssSelector(".IsEnabled");

    public CountryPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterCountryName(String countryName) {
        driver.findElement(countryNameField).sendKeys(countryName);
    }

    public void enterCountryCode(String countryCode) {
        driver.findElement(countryCodeField).sendKeys(countryCode);
    }

    public void enterCountryMobileCode(String mobileCode) {
        driver.findElement(countryMobileCodeField).sendKeys(mobileCode);
    }

    // ✅ Proper Dropdown Handling
    public void selectIsEnabled(String value) {
        Select select = new Select(driver.findElement(isEnabledDropdown));
        select.selectByVisibleText(value);   // "Yes" / "No"
    }

    public void clickAddCountry() {
        driver.findElement(addCountryButton).click();
    }

    public void clickClear() {
        driver.findElement(clearButton).click();
    }

    public String getCountryNameError() {
        return driver.findElement(countryNameError).getText();
    }

    public String getCountryCodeError() {
        return driver.findElement(countryCodeError).getText();
    }

    public String getCountryMobileCodeError() {
        return driver.findElement(countryMobileCodeError).getText();
    }

    public String getIsEnabledError() {
        return driver.findElement(isEnabledError).getText();
    }
}
