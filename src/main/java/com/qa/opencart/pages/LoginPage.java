package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.log4j.Logger;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private static final Logger LOGGER = Logger.getLogger(String.valueOf(LoginPage.class));
	
	private WebDriver driver;
	ElementUtil elemUtil;
	
	
	//locator or object repository
	
	private By username=By.id("input-email");
    private	By password=By.id("input-password");
    private By loginBtn=By.cssSelector("input[type='submit']");
    private By forgotPwdLink=By.cssSelector(".form-group a[href*='account/forgotten']");
    private By registerLink=By.linkText("Register");
    
    //Constructor of page class
    
    public LoginPage(WebDriver driver) {
    	this.driver=driver;
    	elemUtil=new ElementUtil(this.driver);
    	
    }
    
    //3. page actions/method/libs
    @Step("get login page title")
    public String getLoginPageTitle() {
    	LOGGER.info("getting page title...");
    	//return elemUtil.waitForPageTitle(getLoginPageTitle(), 5);
    	return elemUtil.waitForPageTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 5);
    }
    
    @Step("verify forgot password link")
    public boolean isForgotPwdLinkExist() {
    	LOGGER.info("checking forgot password link");
    	return elemUtil.getElement(forgotPwdLink).isDisplayed();
    }
    
    @Step("login with username:{0} and password :{1}")
    public AccountsPage doLogin(String un,String pwd) {
    	LOGGER.info("Login with:"+un +"  "+pwd);
//    	driver.findElement(username).sendKeys(un);
//    	driver.findElement(password).sendKeys(pwd);
//    	driver.findElement(loginBtn).click();
    	
    	elemUtil.doSendKeys(username, un);
    	elemUtil.doSendKeys(password, pwd);
    	elemUtil.doClick(loginBtn);
    	
    	return new AccountsPage(driver);
    }
    
    @Step("navigate to Register page")
    public RegisterPage navigateToRegisterPage() {
    	LOGGER.info("navigating to register page...");
    	elemUtil.doClick(registerLink);
    	return new RegisterPage(driver);
    	
    }
    

}
