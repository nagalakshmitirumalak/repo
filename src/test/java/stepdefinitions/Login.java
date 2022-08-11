package stepdefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import realtimepageobjects.LandingPage;
import realtimepageobjects.LoginPage;
import realtimepageobjects.MyAccountPage;
import use.Base;

public class Login extends Base {

	WebDriver driver;
	LoginPage loginPage;

	@Given("^Open any Broswer$")
	public void open_any_broswer() throws IOException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url"));

	}

	@And("^Navigate to Login Page$")
	public void navigate_to_login_page() {

		LandingPage landingPage = new LandingPage(driver);

		landingPage.myAccountDropdown().click();
		landingPage.loginOption().click();
		
	}

	@When("^User enters username as \"([^\"]*)\" and password as \"([^\"]*)\" into the fields$")
	public void user_enters_username_as_something_and_password_as_something_into_the_fields(String username,
			String password) {

		loginPage = new LoginPage(driver);

		loginPage.emailField().sendKeys(username);
		loginPage.passwordField().sendKeys(password);

	}

	@And("^User clicks on Login button$")
	public void user_clicks_on_login_button() {
		
		loginPage.loginButton().click();

	}

	@Then("^verify user is able to successfully$")
	public void verify_user_is_able_to_successfully() {
		
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		
		Assert.assertTrue(myAccountPage.myAccountBreadCrumb().isDisplayed());

	}
	
	@After
	public void browserclose() {
		
		driver.close();
		
	}
	
}
