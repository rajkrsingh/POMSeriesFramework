package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class SignupPage {
	
	private By registration=By.id("registration");
	
	public SignupPage() {
		System.out.println("This is sigup page constructor....");
	}	
	
	public void doRegisrationPage() {
		System.out.println("This is registration page....");
		System.out.println("Practicing master and signup branch concept");
	}

}
