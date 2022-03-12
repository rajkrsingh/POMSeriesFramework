package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.ProductInfoPage;

public class ProductInfoTest extends BaseTest{
	
	@BeforeClass
	public void accountsPageSetup() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	public void goToProductInfoPage(String productName) {
		accountsPage.doSearch(productName);
		productInfoPage=accountsPage.selectProductFromResults(productName);
		
	}
	@Test(priority=1)
	public void productInfoPageTitleTest_iMac() {
		accountsPage.doSearch("iMac");
		productInfoPage=accountsPage.selectProductFromResults("iMac");
		//productInfoPage.getProductInfoPageTitle("iMac")
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("iMac"), "iMac");
		
	}
	
	@Test(priority=2)
	public void productInfoPageTitleTest_MacbookAir() {
		accountsPage.doSearch("MacBook Air");
		productInfoPage=accountsPage.selectProductFromResults("MacBook Air");
		//productInfoPage.getProductInfoPageTitle("iMac")
		Assert.assertEquals(productInfoPage.getProductInfoPageTitle("MacBook Air"), "MacBook Air");
		
	}
	
	@Test(priority=3)
	public void productImageTest() {
		String productName="iMac";
		accountsPage.doSearch(productName);
		accountsPage.selectProductFromResults("iMac");
		Assert.assertTrue(productInfoPage.getProductImages()==3);
		
		
	}
	@Test(priority=4)
		public void productInfoTest() {
		
		String productName="iMac";
		goToProductInfoPage(productName);
		Map<String,String> productInfoMap=productInfoPage.getProductInformation();
        //System.out.println(productInfoMap);		
        productInfoMap.forEach((k,v)-> System.out.println(k + " : "+v));
        
        /**
         * Brand=Apple
         * Availability=Out Of Stock
         * exTaxprice=$100.00
         * price=$100.00
         * name=iMac
         * Product Code=Product 14
         */
		SoftAssert softAssert=new SoftAssert();	
		softAssert.assertEquals(productInfoMap.get("name"), productName);
		softAssert.assertEquals(productInfoMap.get("Brand"),"Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"),"Out Of Stock");
		softAssert.assertEquals(productInfoMap.get("price"),"$100.00");
		softAssert.assertEquals(productInfoMap.get("Product Code"),"Product 14");
		softAssert.assertAll();
        
        
		}
}

