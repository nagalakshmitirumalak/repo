package realtimeproject;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import use.Base;

public class ThreeTest extends Base {

	WebDriver driver;
	
	@Test
	public void threeTest() throws IOException {
		
		System.out.println("Inside ThreeTest");
		driver = initializeDriver();
		
		driver.get(prop.getProperty("urlForThree"));
		
		driver.findElement(By.id("paheeABC")).click();

	}
	
	@AfterTest
	public void closeBrowser() {
		
		driver.close();
		
	}
	
}
