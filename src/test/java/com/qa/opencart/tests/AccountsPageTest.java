package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class AccountsPageTest extends BaseTest{
	
	@BeforeTest
	public void accountsPageSetup() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	@Test(priority=1)
	@Description("Verify accounts page title  test...")
	@Severity(SeverityLevel.MINOR)
	public void accountsPageTitleTest() {
		
		String title=accountsPage.getAccountsPageTitle();
		System.out.println("Accounts page title:"+title);
		Assert.assertEquals(title,Constants.ACCOUNT_PAGE_TITLE);
		
	}
	
	@Test(priority=2)
	@Description("Verify accounts page header test")
	@Severity(SeverityLevel.MINOR)
	public void verifyAccountsPageHeaderTest() {
	String headerText=accountsPage.getHeaderValue();
	System.out.println("Header text is:"+headerText);
	Assert.assertEquals(headerText, Constants.ACCOUNT_PAGE_HEADER);
	}
    
	@Test(priority=3)
	@Description("Verify accounts page count test")
	@Severity(SeverityLevel.NORMAL)
    public void verifyAccountsPageSectionCountTest() {
      Assert.assertTrue(accountsPage.getAccountSectionsCount() == Constants.ACCOUNT_SECTION_COUNT);	
    }
	
	@Test(priority=4)
	@Description("Verify accounts page section list test")
	@Severity(SeverityLevel.CRITICAL)
	public void verifyAccountsPageSectionListTest() {
	List<String> accountList=accountsPage.getAccountSectionsList();
	System.out.println(accountList);
	Assert.assertEquals(accountList,Constants.getAccSectionsList(), Errors.HEADER_NOT_MATCHED_ERROR);
	
	}
	
//	@Test(priority=5)
//	@Description("Verify accounts page search test for iMac")
//	@Severity(SeverityLevel.CRITICAL)
//	public void verifySearchTest_iMac() {
//		Assert.assertTrue(accountsPage.doSearch("iMac"));
//		
//		
//	}

	@DataProvider
	public Object[][] searchData() {
		return new Object[][] {{"iMac"},
			                   {"Macbook"},
			                   {"iPhone"}};
		
	}
	@Test(priority=6,dataProvider="searchData")
	@Description("Verify accounts page search test for macbook air")
	@Severity(SeverityLevel.CRITICAL)
	public void verifySearchTest(String productName) {
		Assert.assertTrue(accountsPage.doSearch(productName));
		
		
	}
	
	@Test(priority=7)
	@Description("Verify product results test")
	@Severity(SeverityLevel.BLOCKER)
	public void verifyProductResultsTest() {
		accountsPage.doSearch("iMac");
		accountsPage.selectProductFromResults("iMac");
		
	}
}
