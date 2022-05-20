package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;

public class BaseTest {
	WebDriver driver;
	DriverFactory df;
	public Properties prop;
	public LoginPage loginPage;
	public AccountsPage accountsPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage registerPage;
	
	
	@BeforeTest
	public void setUp(String browserName, String browserVersion) {
	df=new DriverFactory();	
	prop=df.init_prop();
	String browser=prop.getProperty("browser");
	
	driver=df.init_Driver(browser, browserVersion);
	driver.get(prop.getProperty("url"));
	
	loginPage=new LoginPage(driver);
	
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
