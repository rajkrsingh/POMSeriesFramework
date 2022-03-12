package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//locators or Object Repository
	private By firstName=By.id("input-firstname");
	private By lastName=By.id("input-lastname");
	private By email=By.id("input-email");
    private By telephone=By.id("input-telephone");
    private By password=By.id("input-password");
    private By confirmPassword=By.id("input-confirm");
    
    private By subscribeYes=By.xpath("//label[1][@class='radio-inline']/input");
    private By subscribeNo=By.xpath("//label[2][@class='radio-inline']/input");
    
    private By privacyPolicy=By.cssSelector("input[name='agree']");
    private By continueBtn=By.cssSelector("input[value='Continue']");
    
    private By successMsg=By.cssSelector("#content h1");
    private By logout=By.linkText("Logout");
    private By register=By.linkText("Register");
    
    
    public RegisterPage(WebDriver driver) {
    	this.driver=driver;
    	elementUtil=new ElementUtil(this.driver);
    }
    
    public boolean accountRegistration(String firstName,String lastName, String email, String telephone, String password, String subscribe) {
    	elementUtil.doSendKeys(this.firstName,firstName);
    	elementUtil.doSendKeys(this.lastName,lastName);
    	elementUtil.doSendKeys(this.email,email);
    	elementUtil.doSendKeys(this.telephone,telephone);
    	elementUtil.doSendKeys(this.password,password);
    	elementUtil.doSendKeys(this.confirmPassword,password);
    	
    	if(subscribe.equals("yes")) {
    	     elementUtil.doClick(subscribeYes);
    	}else {
    		elementUtil.doClick(subscribeNo);
    	}
    	elementUtil.doClick(privacyPolicy);
    	elementUtil.doClick(continueBtn);
    	
    	String text=elementUtil.doGetText(successMsg);
    	System.out.println("Success message is:"+text);
    	
    	if(text.equals(Constants.ACCOUNT_SUCCESS_MSG)){
    		elementUtil.doClick(logout);
    		elementUtil.doClick(register);
    		return true;
    	}
    	
    	return false;
    	
    	
    }
}
