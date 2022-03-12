package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Raj
 *
 */
public class DriverFactory {
	
	private static final Logger LOGGER = Logger.getLogger(String.valueOf(DriverFactory.class));
	WebDriver driver;
	Properties prop;
	public static String highlight;
	OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<>();
	
	/**
	 * This method is used to initialize the webdriver on the basis of given browser name
	 * @param browserName
	 * @return
	 */
	
	public WebDriver init_Driver(Properties prop) {
		String browserName=prop.getProperty("browser");
		LOGGER.info("Browser name is:"+browserName);
		optionsManager=new OptionsManager(prop);
		highlight=prop.getProperty("highlight");
		
		if(browserName.equals("chrome")) {
			LOGGER.info("setup chrome browser...");
			WebDriverManager.chromedriver().setup();
			//driver=new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equals("firefox")) {
			LOGGER.info("setup firefox browser...");
			WebDriverManager.firefoxdriver().setup();
			//driver=new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}
		
		else if(browserName.equals("safari")) {
			LOGGER.info("setup safari browser...");
			//driver=new SafariDriver();
			tlDriver.set(new SafariDriver());		}
		else
		{
			System.out.println("Please pass the correct browser"+browserName);
			
		}
		
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		
		return getDriver();
		
	}
	
	 public static synchronized WebDriver getDriver() {
		 return tlDriver.get();
	 }
	/**
	 * This method is used to initialize the properties from config file
	 * @return returns properties prop
	 */
	
	public Properties init_prop() {
		
		prop=new Properties();
		try {
			FileInputStream ip= new FileInputStream("./src/test/resources/config/config.properties"); //make the connection between config.properties and java
			prop.load(ip); //load the properties one by one
		} catch (FileNotFoundException e) {
			LOGGER.error("file not found at the given location....");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}
	
	/**
	 * Take screenshot
	 * @return 
	 */
	
	public String getScreenshot() {
	
		 String src=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BASE64);
		 File srcFile=new File(src);
		 String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(srcFile,destination);
			} catch (IOException e) {
				LOGGER.error("some exception is coming while creating the screenshot");
				e.printStackTrace();
			}
			return path;
		}

	
}	
