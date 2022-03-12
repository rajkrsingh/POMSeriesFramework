package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil elementUtil;

	private By productTitle=By.cssSelector("#content h1");
	private By productMetaData=By.cssSelector("#content ul.list-unstyled:nth-of-type(1) li");
	private By price=By.cssSelector("#content ul.list-unstyled:nth-of-type(2) li");
	private By quantity=By.cssSelector("input[name='quantity']");
	private By addToCartButton=By.id("input-quantity");
	private By productImages=By.cssSelector(".thumbnails img");


	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(driver);

	}
	public Map<String, String> getProductInformation() {
		Map<String,String> productInfoMap= new HashMap<String,String>();
		productInfoMap.put("name", elementUtil.doGetText(productTitle).trim());
		
		List<WebElement>  productMetaDataList=elementUtil.getElements(productMetaData);
		for(WebElement e:productMetaDataList) {
			//Brand:Apple
			String meta[]=e.getText().split(":");
			String metaName=meta[0].trim();
			String metaValue=meta[1].trim();
			productInfoMap.put(metaName, metaValue);

		}
		//price:
		List<WebElement> productPriceList=elementUtil.getElements(price);
		productInfoMap.put("price",productPriceList.get(0).getText().trim());
		productInfoMap.put("exTaxprice",productPriceList.get(1).getText().split(":")[1].trim());

		return productInfoMap;
	}


	public void selectQuantity(String qty) {

		elementUtil.doSendKeys(quantity,qty);
	}

	public void addToCart() {
		elementUtil.doClick(addToCartButton);
	}

	public int getProductImages() {
		int imageCount=elementUtil.getElements(productImages).size();
		System.out.println("Total iamge count is:"+imageCount);
		return imageCount;
	}

	public String getProductInfoPageTitle(String productName) {

		return elementUtil.waitForPageTitleToBePresent(productName,5);

	}
}
