package mercurytours;



import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestMercury {

	WebDriver driver  = null;
	TestMercury testMercury =null;
	
	@Given("^user is on Mercury tours site$")
	public void user_is_on_Mercury_tours_site() throws Throwable {
	    // Express the Regexp above with the code you wish you had
		driver = new FirefoxDriver();
		driver.get("http://newtours.demoaut.com/mercurywelcome.php");
		driver.manage().window().maximize();
	}

	@Given("^check the url of the browser being displayed with the text '(.+)'$")
	public void check_the_url_of_the_browser_being_displayed(String verifyUrl) throws Throwable {
	    // Express the Regexp above with the code you wish you had
		String text = driver.findElement(By.xpath("//form[@name='home']/table/tbody/tr[4]/td/table/tbody/tr[1]/td/font/b")).getText();
	  Assert.assertTrue(verifyUrl.equalsIgnoreCase(text));
	}
	
	
	@Given("^The user is registered with username '(.+)' and password '(.+)'$")
	public void The_user_is_registered_with_username_test_and_password_test_(String username,String password) throws Throwable {
		// Express the Regexp above with the code you wish you had
//		testMercury = new TestMercury();
//		testMercury.setup();	   
		
		System.out.println("Registered with username "+ username+" Successfully");
	}


	@When("^the User logins with username '(.+)' and password '(.+)'$")
	public void the_User_logins_with_username_test_and_password_test(String username,String password) throws Throwable {
		// Express the Regexp above with the code you wish you had	    
		driver.findElement(By.name("userName")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);		
	}

	@When("^Clicks on the login button$")
	public void Clicks_on_the_login_button() throws Throwable {
		// Express the Regexp above with the code you wish you had
		driver.findElement(By.name("login")).click();
	}

	@Then("^the message shows '(.+)'$")
	public void the_message_shows_Flight_Details(String text) throws Throwable {
		// Express the Regexp above with the code you wish you had
		String verifyText = driver.findElement(By.xpath("//form[@name='findflight']/table/tbody/tr[1]/td/font/font/b/font/font")).getText();
		Assert.assertTrue("Logged in Successfully",text.equalsIgnoreCase(verifyText));
	}
	
	
	
	@Then("^it should be redirected to registration page and should give a message '(.+)'$")
	public void the_message_Registration(String text) throws Throwable {
		// Express the Regexp above with the code you wish you had
		String verifyText = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p/font/a")).getText();
		Assert.assertTrue("Logged in Successfully",text.equalsIgnoreCase(verifyText));
	}

}
