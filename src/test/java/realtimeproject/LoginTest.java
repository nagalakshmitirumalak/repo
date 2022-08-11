package realtimeproject;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import realtimepageobjects.LandingPage;
import realtimepageobjects.LoginPage;
import realtimepageobjects.MyAccountPage;
import use.Base;

public class LoginTest extends Base {

	public WebDriver driver;
	Logger log;

	@BeforeMethod
	public void openApplication() throws IOException {

		driver = initializeDriver();
		
		log = LogManager.getLogger(LoginTest.class.getName());
		log.debug("Browser got launched");

		driver.get(prop.getProperty("url"));
		log.debug("Navigated to Tutorials Application");

	}

	@Test(dataProvider = "data")
	public void login(String username, String password, String expectedResult) {
		
		LandingPage landingPage = new LandingPage(driver);
		
		landingPage.myAccountDropdown().click();
		log.debug("Clicked on My Account dropdown");
		landingPage.loginOption().click();
		log.debug("Clicked on login Button");
		log.debug("Navigated to Login Page");
		
		LoginPage loginPage = new LoginPage(driver);
		
		loginPage.emailField().sendKeys(username);
		log.debug("Entered Email address");
		loginPage.passwordField().sendKeys(password);
		log.debug("Entered Password");
		loginPage.loginButton().click();
		log.debug("Clicked on login Button");
		
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		
		String actualResult = null;
		
		try{
			
			if(myAccountPage.myAccountBreadCrumb().isDisplayed()) {
			
			   actualResult = "successfull";
			   log.info("Login got Successfull");
			   
		    }
			
		}catch(Exception e) {
			
			actualResult = "failure";
			log.error("Login got Failed : not able to login");
			
		}
		
		Assert.assertEquals(actualResult,expectedResult);		
		log.info("Login Test got passed");
	}

	@DataProvider
	public Object[][] data() {
		
		Object[][] loginData = {{"arun.selenium@gmail.com","Second@123","successfull"},{"dummy@gmail.com","dummy","failure"}};
		return loginData;
		
	}
	
	
	@AfterMethod
	public void closure() {

		driver.quit();
		log.debug("Browser got closed");

	}

}
