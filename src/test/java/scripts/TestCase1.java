package scripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class TestCase1 {

	@Test
	public void ContactUs() throws InterruptedException {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://sailclear.com/");
		
		driver.findElement(By.xpath("//button[text()='X']")).click();
		Thread.sleep(3000);
		
		
		driver.findElement(By.xpath("(//a[text()='Contact Us'])[1]")).click();
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,2000)");
		
		driver.findElement(By.xpath("//input[@placeholder='Name *']")).sendKeys("Aman");
		driver.findElement(By.xpath("//input[@placeholder='Email *']")).sendKeys("aman@yopmail.com");
		
		driver.findElement(By.xpath("//input[@placeholder='Subject *']")).sendKeys("Need To Go");
		
		driver.findElement(By.xpath("//textarea[@placeholder='Message *']")).sendKeys("I am subham");
		
		driver.findElement(By.xpath("//button[normalize-space()='Send Message']")).click();
		
		Thread.sleep(3000);
		
		driver.close();
		
		
	}
}
