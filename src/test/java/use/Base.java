package use;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	WebDriver driver;
	public Properties prop;

	public WebDriver initializeDriver() throws IOException {

		String proppath = System.getProperty("user.dir") + "\\src\\test\\java\\use\\data.properties";
		FileInputStream fis = new FileInputStream(proppath);
		prop = new Properties();
		prop.load(fis);

		String browserName = prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("Chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if(browserName.equalsIgnoreCase("Firefox")) {
			
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		} else if(browserName.equalsIgnoreCase("IE")) {
			
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			
		} else if(browserName.equalsIgnoreCase("Edge")) {
			
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		} else if(browserName.equalsIgnoreCase("Safari")) {
			
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}
	
	public String takeScreenshot(String testName, WebDriver driver) throws IOException {
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String destFilepath = System.getProperty("user.dir")+"\\screenshots\\"+testName+".png";
		
		FileUtils.copyFile(srcFile, new File(destFilepath));
		
		return destFilepath;
		
	}

}
