package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver=driver;
	   jsUtil= new JavaScriptUtil(this.driver);
		

	}

	public  WebElement getElement(By locator) {
		WebElement element= driver.findElement(locator);
		if(DriverFactory.highlight.equals("true")) {
			jsUtil.flash(element);
		}
		return element;

	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator,String value) {
		WebElement element=getElement(locator);
		element.clear();
		element.sendKeys(value);
		

	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	//****Send/Click Keys with ACTIONS CLASS ******************** 
	public void doActionsSendKeys(By locator,String value) {
		Actions action=new Actions(driver);
		action.sendKeys(getElement(locator),value).perform();

	}

	public void doActionsClick(By locator) {
		Actions action=new Actions(driver);
		action.click(getElement(locator)).perform();
	}
	public String doGetText(By locator) {
    return getElement(locator).getText();
	}
	//***********Drop Down with Select Class*******************


	public void doSelectFromDropDownVisibleText(By locator,String value){
		Select select=new Select(getElement(locator));
		select.selectByVisibleText(value);

	}

	public void doSelectFromDropDownByIndex(By locator,int index){
		Select select=new Select(getElement(locator));
		select.selectByIndex(index);

	}

	public void doSelectFromDropDownByValue(By locator,String value){
		Select select=new Select(getElement(locator));
		select.selectByValue(value);

	}

	//************Drop Down without Select Class**********************

	public void selectDropDownValue(By locator,String value) {

		List<WebElement> optionsList=getElements(locator);
		//int totalCountry=countryList.size();
		//System.out.println("Total country count:"+totalCountry);

		for(WebElement e:optionsList) {
			if(e.getText().equals(value)){
				e.click();
				break;
			}
		}

	}


	public void selectFromSuggestionList(By suggestionList, String value) {

		List<WebElement> totalSuggestion=getElements(suggestionList);

		for(WebElement e:totalSuggestion) {
			System.out.println(e.getText());

			if(e.getText().equals(value)) {
				e.click();
				break;
			}
		}

	}

	//********This method will handle java script alert pop *******************************************

	public void jsAlertPopUpHandle() {

		Alert alert=driver.switchTo().alert();
		alert.accept(); //clicks on OK button
		//alert.dismiss(); // clicks on cancel button
		//alert.getText(); //get the text message from java script pop up window
		//alert.sendKeys("Testing"); //It will entered text in JS prompt 
	}


	//*********Handling Frame if frame name is exist(not applicable, if frame id is exist) **************** 

	public void frameHandle(By locator,String name) {

		driver.switchTo().frame(name);
		WebElement title=getElement(locator);
		String text=title.getText();
		System.out.println(text);

		driver.switchTo().defaultContent();
	}
	public List<String> getOptionsTextList(By locator) {
		List<String> optionsList=new ArrayList<>();

		Select select=new Select(getElement(locator));
		List<WebElement> options_list=select.getOptions();
		System.out.println(options_list.size());

		for(WebElement e:options_list) {
			String text=e.getText();
			System.out.println(text);
			optionsList.add(text);

		}

		return optionsList;

	}

	//** verify links

	public List<String> languageLinks(By locator) {
		List<String> language_links= new ArrayList<>();	
		List<WebElement> language_List=getElements(locator);
		for(WebElement e:language_List) {	
			String text=e.getText();
			System.out.println(text);
			language_links.add(text);
		}

		return language_links;

	}

	//3.function: which will return :the list of footer links text
	public List<String> bottomFooterLinks(By locator) {

		List<String> bottomLinkText=new ArrayList<>();
		List<WebElement> bottomLinks=getElements(locator);

		for(WebElement e:bottomLinks) {
			String text=e.getText();
			System.out.println(text);
			bottomLinkText.add(text);;



		}

		return bottomLinkText;

	}

	//*****File Upload*************************//

	public void fileUploadPopUp(By locator,String path) {
		getElement(locator).sendKeys(path);


	}

	//*******To handle Basic Auth *******************//

	public void authPopUpConcept() {

		//after @ selenium consider the url of the application so  password should not
		// contains @ like admin@123--in this case it won't work
		//url - https://admin:admin@the-internet.herokuapp.com/basic_auth


		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth"); 
	}


	//******JQuery Drop Down Handle ************************
	/**
	 * 
	 * 1.Pass the single string value for single selection
	 * 2.Pass the multiple string value for multiple selection
	 * 3.Pass "All" for all selection values from drop down
	 * Here Sting... value -> it behaves like a String array
	 * For multi selection we call method like "optionListMultiSelect(multi_select,"choice 1","choice 2 1","choice 3","choice 4"); "
	 * For All selection we call method like "optionListMultiSelect(multi_select,"All"); "
	 * @param locator
	 * @param value
	 * @throws InterruptedException
	 */


	public  void optionListMultiSelect(By locator,String...value) throws InterruptedException {

		List<WebElement> multi_select_options=getElements(locator);
		if(!value[0].equalsIgnoreCase("All")) {

			for(int i=0;i<multi_select_options.size(); i++) {
				String text=multi_select_options.get(i).getText();
				if(!text.isEmpty()) {
					System.out.println(text); 

					for(int j=0;j<value.length;j++) {
						if(text.equals(value[j])) {
							multi_select_options.get(i).click();
							break;
						}

					}
				}

			}
		}
		else {
			try {
				for(WebElement e:multi_select_options) {
					String text1=e.getText();
					if(!text1.isEmpty())
					{
						//Thread.sleep(2000);
						e.click();

					}

				}
			}
			catch(Exception e) { }
		}

	}


	//****************** waits utils ****************************//

	public  String waitForPageTitle(String title,int timeOut) {

		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();

	}

	public String waitForPageTitleToBePresent(String title,int timeOut) {

		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();

	}

	public String waitForPageUrl(String urlValue,int timeOut) {

		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.urlContains(urlValue));
		return driver.getCurrentUrl();

	}

	public  Alert isAlertPresent(int timeOut) {

		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());


	}

	public String getAlertText(int timeOut) {
		return isAlertPresent(timeOut).getText();

	}

	public void doAcceptAlert(int timeOut) {
		isAlertPresent(timeOut).accept();

	}

	public  void doDismissAlert(int timeOut) {
		isAlertPresent(timeOut).dismiss();
	}


	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public  WebElement waitForElementPresent(By locator,int timeOut) {

		WebDriverWait wait=new WebDriverWait(driver,timeOut);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));


	}


	/**
	 * An expectation for checking that an element, known to be present on the DOM of a page, is visible.
	 * Visibility means that the element is not only displayed but also has a height and width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 */

	public void waitForElementVisible(By locator,int timeOut) {

		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}
	/**
	 * An expectation for checking that all elements present on the web page that match the locator are visible.
	 * Visibility means that the elements are not only displayed but also have a height and width that is greater than 0.
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> visibilityOfAllElements(By locator,int timeOut) {

		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void getPageElementsText(By locator,int timeOut) {

		List<WebElement> pageElements=visibilityOfAllElements(locator,timeOut);
		pageElements.stream().forEach(ele->System.out.println(ele.getText()));

	}
	public int getPageElementsCount(By locator,int timeOut) {

		return visibilityOfAllElements(locator,timeOut).size();


	}
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param locator
	 * @param timeOut
	 */
	public void actionsClickWhenElementReady(By locator,int timeOut) {

		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		WebElement element= wait.until(ExpectedConditions.elementToBeClickable(locator));
		Actions act=new Actions(driver);
		act.moveToElement(element).click().build().perform();

	}

	public void clickWhenElementReady(By locator,int timeOut) {

		WebDriverWait wait=new WebDriverWait(driver,timeOut);
		WebElement element= wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();


	}


	/**
	 * This is a custom method to provide the dynamic wait to find the WebElement
	 * @return 
	 * @throws InterruptedException 
	 */

	public WebElement retryingElement(By locator){

		WebElement element=null;
		int attempts=0;

		while(attempts<30){
			try {
				element=driver.findElement(locator);
				break;
			}
			catch(NoSuchElementException e) {
				try {
					Thread.sleep(500);

				}catch(InterruptedException e1) {

				}

			}
			catch(StaleElementReferenceException e) {
				try {
					Thread.sleep(500);
				}

				catch(InterruptedException e1) {

				}
			}
			System.out.println("element is not found:"+(attempts+1)); 

			attempts++;

		}

		return element;
	}

	//******FluentWait*****************
	public WebElement waitForElementWithFluentWait(By locator,int timeOut,int pollingTime) {

		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut)) 
				.pollingEvery(Duration.ofSeconds(pollingTime)) //polling means total number of attempts in every 2 seconds
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));


	}

}
