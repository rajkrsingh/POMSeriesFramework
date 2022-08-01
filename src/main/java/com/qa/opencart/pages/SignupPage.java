package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class SignupPage {
	
	private By registration=By.id("registration");
	private By fName=By.xpath("//div[@class='fname']");
	public SignupPage() {
		System.out.println("This is sigup page constructor....");
	}	
	
	public void doSignUpProcess() {
		System.out.println("This is registration page....");
		System.out.println("Practicing master and signup branch concept");
		System.out.println("Purpose of this method to check branching concept at remote side");
	}

}
