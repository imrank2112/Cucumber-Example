package com.ik.mobile.android.selendroid;



import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SelendroidAppTest {

	public static WebDriver wd;
	public static DesiredCapabilities ds =null;
	public static WebElement txtElement=null;
	public static WebElement chkBox=null;
	@Given("^Appium Server is running$")
	public void Appium_Server_is_running() throws Throwable {
		// Express the Regexp above with the code you wish you had
		System.out.println(".............Appium server is starting................");
		System.out.println(".............Appium server is running.................");
	}

	@Given("^Android emulator or the device is up and running$")
	public void Android_emulator_or_the_device_is_up_and_running() throws Throwable {
		// Express the Regexp above with the code you wish you had
		System.out.println("................Emualtor starting up.................");
		System.out.println("................Emualtor started.....................");
	}

	@Given("^Desired capabilities are set and application file \"([^\"]*)\" loaded on appium$")
	public void Desired_capabilities_are_set_and_application_is_file_loaded_on_appium(String AndroidAppPath) throws Throwable {
		// Express the Regexp above with the code you wish you had
		File app = new File(AndroidAppPath);

		ds =new DesiredCapabilities();
		ds.setCapability(CapabilityType.BROWSER_NAME, "");
		ds.setCapability("deviceName", "emualtor-5554");
		ds.setCapability("platformVersion", "emualtor-5554");
		ds.setCapability("platformName", "Android");
		ds.setCapability("app", app.getAbsolutePath());
	}

	@When("^the url \"([^\"]*)\" is hit and App is loaded$")
	public void the_url_is_hit_and_App_is_loaded(String url) throws Throwable {
		// Express the Regexp above with the code you wish you had
		wd = new AndroidDriver(new URL(url),ds);
		wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@When("^then enter the Mobile version \"([^\"]*)\" in textbox$")
	public void then_enter_the_Mobile_version_Nexus_in_textbox(String mobileName) throws Throwable {
		// Express the Regexp above with the code you wish you had
		txtElement = wd.findElement(By.className("android.widget.EditText"));
		txtElement.sendKeys("Nexus 5");
	}

	@When("^click the Checkbox$")
	public void click_the_Checkbox() throws Throwable {
		// Express the Regexp above with the code you wish you had
		chkBox = wd.findElement(By.className("android.widget.CheckBox"));
		chkBox.click();
	}


	@Then("^the textbox should contain the text \"([^\"]*)\"$")
	public void the_textbox_should_contain_the_text(String mobileName) throws Throwable {
		// Express the Regexp above with the code you wish you had
		Assert.assertTrue(mobileName.equalsIgnoreCase(txtElement.getText()));
	}

	@Then("^checkbox should have been unchecked.$")
	public void checkbox_should_have_been_checked() throws Throwable {
		// Express the Regexp above with the code you wish you had
		Assert.assertTrue(!chkBox.isSelected());
	}

}
