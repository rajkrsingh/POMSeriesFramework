package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil elemUtil;


	private By header=By.cssSelector("#logo a");
	private By accountSectionsHeaders=By.cssSelector("div#content h2");
	private By accountSectionsList=By.cssSelector("div#content ul");
	private By searchTextField=By.cssSelector("div#search input");
	private By searchItemResult=By.cssSelector("div.product-layout div.product-thumb");
	private By resultsItem=By.cssSelector("div.product-thumb h4 a");
	private By SearchBtn=By.cssSelector("span.input-group-btn button");


	//constructor
	public AccountsPage(WebDriver driver) {

		this.driver=driver;
		elemUtil=new ElementUtil(this.driver);
	}
  
	@Step("Get accounts page  title test")
	public String getAccountsPageTitle() {
		return	elemUtil.waitForPageTitleToBePresent(Constants.ACCOUNT_PAGE_TITLE,5);
	}
    @Step("Get header value")
	public String getHeaderValue() {
		if(elemUtil.getElement(header).isDisplayed()) {
			return elemUtil.getElement(header).getText();
		}
		return null;
	}

	public int getAccountSectionsCount() {
		return elemUtil.getElements(accountSectionsHeaders).size();
	}

	public List<String> getAccountSectionsList() {
		List<String> accountSection=new ArrayList<>();
		List<WebElement> accountSectionList=elemUtil.getElements(accountSectionsHeaders);
		int totalCount=accountSectionList.size();
		for(WebElement e:accountSectionList) {
			String totalValue=e.getText();
			accountSection.add(totalValue);


		}
		return accountSection;
	}


	//Search feautures Page Actions
	public boolean doSearch(String productName) {

		elemUtil.doSendKeys(searchTextField,productName);
		elemUtil.doClick(SearchBtn);
		if(elemUtil.getElements(searchItemResult).size()>0) {
			return true;
		}
		return false;

	}

	public ProductInfoPage selectProductFromResults(String productName) {
		List<WebElement> resultItemList=elemUtil.getElements(resultsItem);
		System.out.println("total number of items displayed::"+resultItemList.size());

		for(WebElement e:resultItemList) {
			String text=e.getText();
			System.out.println("Item is::"+text);
			if(text.equals(productName)) {
				e.click();
				break;
			}
		}
		
		return new ProductInfoPage(driver);
	}
     

}
