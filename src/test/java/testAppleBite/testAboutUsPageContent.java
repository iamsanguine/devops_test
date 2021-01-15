package testAppleBite;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class testAboutUsPageContent { 

	//To access browser
	WebDriver driver;
	String urlAddress = "http://ec2-18-237-252-7.us-west-2.compute.amazonaws.com:9999/index.php";
	
	@BeforeMethod
	public void beforeMethod() {
		//Access and launch chrome browser		
		System.setProperty("webdriver.chrome.driver", "/usr/share/maven/chromedriver");
		ChromeOptions options = new ChromeOptions();				
		driver = new ChromeDriver(options);		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(urlAddress);
	}	


	@Test
	public void validateAboutUs() throws InterruptedException {		
		//Test "About Us" tab and match page content, and fail if page not accessible OR content different
		if(driver.getPageSource().contains("About Us")){
			driver.findElement(By.id("About Us")).click();
			if(driver.getPageSource().contains("PID-ab2-pg")) {
				String y = driver.findElement(By.id("PID-ab2-pg")).getText();
				System.out.println(y);
				Assert.assertEquals(y, "This is about page. Lorem Ipsum Dipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
				System.out.println("About us page validation passed");
			}
		}
		else {
			System.out.println("About us page validation failed");
		}
	}

	@AfterMethod
	public void afterMethod() {
		//Close Browser session
		driver.close();
	}

}
