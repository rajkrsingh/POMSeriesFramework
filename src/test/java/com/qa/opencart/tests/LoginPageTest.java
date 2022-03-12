package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic-101:Feature name :Login page for Demo Shop Applicaion")
@Story("User story - 300:Design login page for application with different test cases")
public class LoginPageTest extends BaseTest{
	
	
	
	@Test
	@Description("login page title test")
	@Severity(SeverityLevel.MINOR)
	public void loginPageTitleTest() {
		
		String title=loginPage.getLoginPageTitle();
		System.out.println("Title of the page is:"+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test
	@Description("verify forgot password link test")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkTest() {
	Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Test
	@Description("Verify login page test...")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
	AccountsPage accountsPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	Assert.assertEquals(accountsPage.getAccountsPageTitle(),Constants.ACCOUNT_PAGE_TITLE);
	}

}
