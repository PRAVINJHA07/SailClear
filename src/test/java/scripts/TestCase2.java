package scripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestCase2 {
	
	@Test
	public void ContactUs() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://sailclear.com/");
		
		List<WebElement> closeBtn =
		        driver.findElements(By.xpath("//button[text()='X']"));

		if (!closeBtn.isEmpty()) {
		    closeBtn.get(0).click();
		    System.out.println("Popup closed");
		} else {
		    System.out.println("Popup not displayed");
		}
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("(//a[text()='Login'])[1]")).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Email']")).sendKeys("aman@email.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Test@123");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
		Thread.sleep(3000);
		
		driver.close();
	}

}
