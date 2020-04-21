package com.seleniumCI.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Field;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;

public class TestClass1 {
	//public String seleniumHub="http://192.168.0.18:4444/wd/hub";
	//public String webUrl="https://automationtalks.com/";
	//public String webUrl="https://www.selenium.dev/";

	public static WebDriver driver;
	public WebElement element;
   //Browser Stack setting
	public static final String USERNAME = "sachin654";
	public static final String AUTOMATE_KEY = "bpypqx4VRCSMvxP96tSd";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	
	
	@BeforeMethod
	public void launchDriver() throws MalformedURLException {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "10");
		caps.setCapability("browser", "Firefox");
		caps.setCapability("browser_version", "76.0 beta");
		caps.setCapability("browserstack.local", "false");
		caps.setCapability("browserstack.selenium_version", "3.5.2");


//		DesiredCapabilities caps = new DesiredCapabilities();
//		caps.setCapability("browser", "Chrome");
//		caps.setCapability("browser_version", "80.0");
//		caps.setCapability("os", "OS X");
//		caps.setCapability("os_version", "Mojave");
//		caps.setCapability("resolution", "1024x768");
//		caps.setCapability("name", "Bstack-[Java] Sample Test");

		driver = new RemoteWebDriver(new URL(URL), caps);
//----------------------------------------------------------------------------------------------
//		DesiredCapabilities dr = null;
//		dr = DesiredCapabilities.chrome();
//		dr.setBrowserName("chrome");
//		dr.setPlatform(Platform.MAC);
//		System.setProperty("webdriver.chrome.driver", "chromedriver");
//
// ----------------------------------------------------------------------------------------------

//		dr = DesiredCapabilities.firefox();
//		dr.setBrowserName("firefox");
//		dr.setPlatform(Platform.MAC);
//		System.setProperty("webdriver.firefox.driver", "geckodriver");
//
//		driver = new RemoteWebDriver(new URL(seleniumHub), dr);
//----------------------------------------------------------------------------------------------
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().fullscreen();
	}
	
	@Test
	public void TestOne() {
		driver.navigate().to("http://www.google.com/ncr");
		System.out.println("Test 1 title is "+driver.getTitle());
		element = driver.findElement(By.name("q"));

		element.sendKeys("BrowserStack");
		element.submit();

		System.out.println(driver.getTitle());
	}
	
	@Test
	public void TestTwo() {
		driver.navigate().to("https://automationtalks.com/");
		System.out.println("Test 2 title is "+driver.getTitle());
	}

	@Test
	public void TestThree() {
		driver.navigate().to("https://automationtalks.com/");
		System.out.println("Test 3 title is "+driver.getTitle());
		Assert.assertEquals("Expected title", driver.getTitle());
	}
	
	@AfterMethod
	public void quit(ITestResult result) throws JiraException {
		driver.quit();
		//if test case fails then log the defect in JIRA
		if(result.getStatus() == ITestResult.FAILURE) {
			
			BasicCredentials creds = new BasicCredentials("admin", "admin");
			JiraClient jira = new JiraClient("http://localhost:8081", creds);
			Issue issueName = jira.createIssue("AUT", "Bug").field(Field.SUMMARY, result.getMethod().getMethodName() +"is failed due to: "+ result.getThrowable().toString()).field(Field.DESCRIPTION, "get the description").execute();
			System.out.println("Issue is created in JIRA with issue key: "+issueName.getKey());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
