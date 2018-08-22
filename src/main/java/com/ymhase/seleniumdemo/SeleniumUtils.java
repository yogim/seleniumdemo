package com.ymhase.seleniumdemo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ymhase.seleniumdemo.model.MessageModel;
import com.ymhase.seleniumdemo.model.MsgCsvFormatModel;

public class SeleniumUtils {

	public static void openPath(String path) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();

		String basePath = PropertiesManager.sharedManager().getProperty(
				"basePath");
		String pageHash = path;

		driver.get(basePath + pageHash);
	}
	
	public static void clearTextFieldValue(String cssSelector) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement field = driver.findElement(By.cssSelector(cssSelector));
		
		field.clear();
	}

	public static void setTextFieldValue(String cssSelector, String value) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement field = driver.findElement(By.cssSelector(cssSelector));
		field.sendKeys(value);
	}

	public static void waitTillVisible(int timeOutSeconds,
			final String cssSelector) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();

		(new WebDriverWait(driver, timeOutSeconds))
				.until(new ExpectedCondition<Boolean>() {
					public Boolean apply(WebDriver d) {
						WebElement element = null;

						System.out.println("Waiting on Visible " + cssSelector
								+ " : " + element);

						try {
							element = d.findElement(By.cssSelector(cssSelector));
						} catch (Exception e) {
							// Do nothing.
						}

						return element.isDisplayed();
					}
				});
	}

	public static void waitTillNull(int timeOutSeconds, final String cssSelector) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();

		(new WebDriverWait(driver, timeOutSeconds))
				.until(new ExpectedCondition<Boolean>() {

					public Boolean apply(WebDriver d) {
						WebElement element = null;

						System.out.println("Waiting on " + cssSelector + " : "
								+ element);

						try {
							element = d.findElement(By.cssSelector(cssSelector));
						} catch (Exception e) {
							// Do nothing.
						}

						return element != null;
					}
				});
	}

	public static WebElement findElementWithText(String cssSelector, String text) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		List<WebElement> elements = driver.findElements(By
				.cssSelector(cssSelector));
		for (WebElement element : elements) {
			if (text.equals(element.getText())) {
				return element;
			}
		}

		return null;
	}

	public static void clickOnElement(String divClass) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.cssSelector(divClass));
		element.click();
	}
	
	public static void clickOnElementById(String id) {

		
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.id(id));
		element.click();
	}
	
	public static void clickOnElementByXPath(String Xpath) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.xpath(Xpath));
		element.click();
	}

	public static boolean checkCurrentPage(String pageName, String expectedUrl) {
		boolean isOnExcepetedPage = false;
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		if (expectedUrl.equals(driver.getCurrentUrl())) {
			isOnExcepetedPage = true;
		}
		return isOnExcepetedPage;
	}
	
	
	public static String checkCurrentPage() {
		String CurrentUrl = null;
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		CurrentUrl = driver.getCurrentUrl();
		return CurrentUrl;
	}
	

	public static String getCurrentUrl() {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public static boolean checkContentForDivByCssSelector(String cssSelector,
			String text) {
		boolean isTextEqual = false;
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		String divText = driver.findElement(By.cssSelector(cssSelector)).getText();
		if (text.equals(divText)) {
			isTextEqual = true;
		}
		return isTextEqual;
	}
	
	public static boolean checkContentForDivById(String id,
			String text) {
		boolean isTextEqual = false;
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		String divText = driver.findElement(By.id(id)).getText(); 
		
		if (text.equals(divText)) {
			isTextEqual = true;
		}
		return isTextEqual;
	}

	public static boolean checkElementExists(String selector){
//		boolean isPresent = false;
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		try{
		driver.findElement(By.cssSelector(selector));
		}catch  (NoSuchElementException e){
			return false;
		}
		 return true;
	}
	
	public static boolean checkContentForDivByXpath(String Xpath, String text) {
		boolean isTextEqual = false;
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		String divText = driver.findElement(By.xpath(Xpath)).getText();
		if (text.equals(divText)) {
			isTextEqual = true;
		}
		return isTextEqual;
	}

	public static Map<String, String> verifyTextforPage(
			List<MsgCsvFormatModel> msgList) {
		// boolean status = false;
		String selector = null;
		String text = null;
		String seperator = null;
		Map<String, String> result = new HashMap<String, String>();

		WebDriver driver = ProjectManager.sharedManager().getWebDriver();

		for (MsgCsvFormatModel msgCsvFormatModel : msgList) {
			if (msgCsvFormatModel.getType().equals("id")) {
				seperator = "#";
			} else {
				seperator = ".";
			}
			selector = msgCsvFormatModel.getTag() + seperator
					+ msgCsvFormatModel.getIdentifier();

			WebElement element = driver.findElement(By.cssSelector(selector));
			text = element.getText();

			if (element != null) {
				text = element.getText();
				if (text.equals(msgCsvFormatModel.getText())) {
					result.put(selector, "---equal");
				} else {
					result.put(selector, "----not equal");
				}
			}

			for (Entry<String, String> entry : result.entrySet()) {
				System.out.println(entry.getKey() + "---" + entry.getValue());
			}
		}
		return result;
	}

	public static Map<String, String> verifyTextforPageNew(
			List<MessageModel> msgList) {
		String selector = null;
		String text = null;
		Map<String, String> result = new HashMap<String, String>();

		WebDriver driver = ProjectManager.sharedManager().getWebDriver();

		for (MessageModel messageModel : msgList) {

			selector = messageModel.getSelector();

			WebElement element = driver.findElement(By.cssSelector(selector));
			text = element.getText();

			if (element != null) {
				text = element.getText();
				if (text.equals(messageModel.getText())) {
					result.put(selector, "---equal");
				} else {
					result.put(selector, "---not equal");
				}
			}
			for (Entry<String, String> entry : result.entrySet()) {
				System.out.println(entry.getKey() + "---" + entry.getValue());
			}
		}
		return result;
	}

	public static Map<String, String> verifyTextByXpath(
			List<MessageModel> msgList) {
		String selector = null;
		String text = null;
		Map<String, String> result = new HashMap<String, String>();

		WebDriver driver = ProjectManager.sharedManager().getWebDriver();

		for (MessageModel messageModel : msgList) {
			selector = messageModel.getSelector();

			WebElement element = driver.findElement(By.xpath(selector));
			text = element.getText();

			if (element != null) {
				text = element.getText();
				if (text.equals(messageModel.getText())) {
					result.put(selector, "equal");
				} else {
					result.put(selector, "not equal");
				}
			}
			for (Entry<String, String> entry : result.entrySet()) {
				System.out.println(entry.getKey() + "---" + entry.getValue());
			}
		}
		return result;
	}

	public static void reloadUrl(String url) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		driver.get(url);
	}

	public static boolean ckeckClass(String checkBoxSelector,String checkBoxVisitedClass) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.cssSelector(checkBoxSelector));
		String classOfElement = element.getAttribute("class");
	     if(classOfElement.equals(checkBoxVisitedClass)){
	    	 return true;
	     }else
	     {
	    	 return false;
	     }
	}
	
	public static boolean ckeckAttributeOfElement(String cssSelector,String value,String attribute) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.cssSelector(cssSelector));
		String classOfElement = element.getAttribute(attribute);
	     if(classOfElement.equals(value)){
	    	 return true;
	     }else
	     {
	    	 return false;
	     }
	}
	
	

	public static void closeBrowser() {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		driver.quit();
		
	}
	public static void openBrowser() {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		String basePath = PropertiesManager.sharedManager().getProperty(
				"basePath");
		driver.get(basePath);
	}
	

/*
	public static void checkAttributeforElement(String compImgSelector,
			String string) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element  = driver.findElement(By.cssSelector(compImgSelector));
//		String attribute = element.getAttribute(string);
	}
*/
	public static void setTextFieldValueByXpath(String elementXpath, String value) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement field = driver.findElement(By.xpath(elementXpath));
		field.sendKeys(value);
		
	}

	public static void checkDropBoxContent(String selector, String subElement) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element  = driver.findElement(By.cssSelector(selector));
		
		List<WebElement> options = element.findElements(By.cssSelector("subElement"));
		for (WebElement option : options) {
			   if("Yes".equals(option.getText()))
			       option.click();   
			}
	}

	public static void selectOptionDropdown(String companyDropDownOption) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();	
		driver.findElement(By.cssSelector(companyDropDownOption)).click();;
	}

	public static void getAllId() {
		String id = null;
//		String SplitKey[]= null;
		String key= null;
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		List<WebElement> options = driver.findElements(By.xpath("//*[@id]"));
		
		
		for (WebElement option : options) {
			 id =option.getAttribute("id");
			 String SplitKey[] = id.split("(?<=.)(?=\\p{Lu})");
			 for (String str : SplitKey) {
				 key = "";
						key =key.concat(str);			
						}
			}
	}
	
	
	public static WebElement getWebElement(String cssSelector) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element  = driver.findElement(By.cssSelector(cssSelector));
		return element;
	}
	

	public static List<WebElement> getWebElementList(String cssSelector) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		List<WebElement> elements  = driver.findElements(By.cssSelector(cssSelector));
		return elements;
	}
	
	public static List<WebElement> getWebElementListByXPath(String Xpath) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		List<WebElement> elements  = driver.findElements(By.xpath(Xpath));
		return elements;
	}
	
	public static WebElement getWebElementByXPath(String Xpath) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element  = driver.findElement(By.xpath(Xpath));
		return element;
	}
	
	public static Actions getActionManager() {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		Actions manager  = new Actions(driver);
		return manager;
	}
	
	public static void wait(int miliseconds) {
		try {
			Thread.sleep(miliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void selectDropdownOption(String parentElementSelector , String childElementSelector){
		Actions manager =  SeleniumUtils.getActionManager();
	       
		WebElement ulElement  = SeleniumUtils.getWebElement(parentElementSelector);
		manager.moveToElement(ulElement).moveToElement(SeleniumUtils.getWebElement(childElementSelector)).click().build().perform();
	}
	
	
	public static void selectDropdownOptionByXpath(String parentElementXpath , String childElementXpath){
		Actions manager =  SeleniumUtils.getActionManager();
	       
		WebElement ulElement  = SeleniumUtils.getWebElementByXPath(parentElementXpath);
		manager.moveToElement(ulElement).moveToElement(SeleniumUtils.getWebElementByXPath(childElementXpath)).click().build().perform();
	}

	public static void scrollPage(String cssSelector) {
		
		 WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		 WebElement element =  driver.findElement(By.cssSelector(cssSelector));
		 int elementPosition = element.getLocation().getY();
		 String js = String.format("window.scroll(0, %s)", elementPosition);
		 ((JavascriptExecutor)driver).executeScript(js);
//		 element.click();
		
	}
	
	public static void setDropDownOptionRandom(String btnSelector,String listXpath,String listStartXpath) {
		
		Actions manager =  SeleniumUtils.getActionManager();
		SeleniumUtils.clickOnElement(btnSelector);
		
		SeleniumUtils.wait(2000);
		WebElement ulSiteElement  = SeleniumUtils.getWebElementByXPath(listStartXpath);
		List<WebElement> liSiteElementList  = SeleniumUtils.getWebElementListByXPath(listStartXpath);
		
		Random randomSite = new Random();
		int siteNo = randomSite.nextInt(liSiteElementList.size());
		WebElement selectedSite = liSiteElementList.get(siteNo);
		
		manager.moveToElement(ulSiteElement).moveToElement(selectedSite).click().build().perform();
	}
	
public static void setDropDownOptionByName(String btnSelector,String listXpath,String listStartXpath,String optionName) {
		
		Actions manager =  SeleniumUtils.getActionManager();
		SeleniumUtils.clickOnElement(btnSelector);
		
		List<WebElement> liSiteElementList  = SeleniumUtils.getWebElementListByXPath(listStartXpath);
		SeleniumUtils.wait(2000);
		WebElement ulSiteElement  = SeleniumUtils.getWebElementByXPath(listStartXpath);
		
        for (WebElement webElement : liSiteElementList) {
        	if(webElement.getText().equalsIgnoreCase(optionName)){
        		manager.moveToElement(ulSiteElement).moveToElement(webElement).click().build().perform();
        	}
		}
	}
	
	
	public static void clickOnRowOfTable( String listXpath,String listStartXpath) {
		
		Actions manager =  SeleniumUtils.getActionManager();
		
		SeleniumUtils.wait(2000);
		WebElement tableElement  = SeleniumUtils.getWebElementByXPath(listStartXpath);
		List<WebElement> tableRows  = SeleniumUtils.getWebElementListByXPath(listStartXpath);
		
		Random randomSite = new Random();
		int siteNo = randomSite.nextInt(tableRows.size());
		WebElement selectedSite = tableRows.get(siteNo);
		
		manager.moveToElement(tableElement).moveToElement(selectedSite).click().build().perform();
	}
	
	public static boolean checkTableContains(String content,String inputCssSlector,String listXpath,String listStartXpath,int columnIndex) {

		SeleniumUtils.setTextFieldValueByXpath(inputCssSlector, content);
		SeleniumUtils.wait(2000);
		List<WebElement> tableRows  = SeleniumUtils.getWebElementListByXPath(listStartXpath);
		for (WebElement webElement : tableRows) {
			List<WebElement>  element = webElement.findElements(By.tagName("td"));
			if(content.equalsIgnoreCase(element.get(columnIndex).getText())){
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean clickTableRowByName(String content,String inputXpath,String listXpath,String listStartXpath,int columnIndex) {
		Actions manager =  SeleniumUtils.getActionManager();
		
		SeleniumUtils.setTextFieldValueByXpath(inputXpath, content);
		SeleniumUtils.wait(2000);
		WebElement tableElement  = SeleniumUtils.getWebElementByXPath(listStartXpath);
		List<WebElement> tableRows  = SeleniumUtils.getWebElementListByXPath(listStartXpath);
		for (WebElement webElement : tableRows) {
			List<WebElement>  element = webElement.findElements(By.tagName("td"));
			if(content.equalsIgnoreCase(element.get(columnIndex).getText())){
				SeleniumUtils.wait(2000);
				manager.moveToElement(tableElement).moveToElement(webElement).click().build().perform();
				return true;
			}
		}
		return false;
	}
	
	public static boolean clickOnRowOfTableByName(String content,String listXpath,String listStartXpath,int columnIndex) {
		Actions manager =  SeleniumUtils.getActionManager();
		
		SeleniumUtils.wait(2000);
		WebElement tableElement  = SeleniumUtils.getWebElementByXPath(listStartXpath);
		List<WebElement> tableRows  = SeleniumUtils.getWebElementListByXPath(listStartXpath);
		for (WebElement webElement : tableRows) {
			List<WebElement>  element = webElement.findElements(By.tagName("td"));
			if(content.equalsIgnoreCase(element.get(columnIndex).getText())){
				SeleniumUtils.wait(2000);
				manager.moveToElement(tableElement).moveToElement(webElement).click().build().perform();
				return true;
			}
		}
		return false;
	}
	

	/*public static boolean checkCurrentUrlContains(String urlCompanyView) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		boolean containerContainsContent=false;
		
		 containerContainsContent = StringUtils.containsIgnoreCase(driver.getCurrentUrl(), urlCompanyView);
		
		return containerContainsContent;
	}*/

	public static boolean checkElementDisplayed(String cssSelector) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.cssSelector(cssSelector));
		return element.isDisplayed();
	}

	public static boolean checkFieldIsNonEditable(String elementId) {
		String tag = "label";
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.id(elementId));
		return tag.equals(element.getTagName());
		
	}
	
	public static String getTextByCssSelector(String cssSelector){
		
		String text = null;
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.cssSelector(cssSelector));
		text = element.getAttribute("value");
		SeleniumUtils.wait(1000);
		return text;
	}

	public static String getTextFromXpath(String xPathOfElement){
		String text = null;
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.xpath(xPathOfElement));
		text = element.getText();
		return text;
	}

	public static void clearFieldByCssSelector(String cssSelector) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.cssSelector(cssSelector));
		element.clear();
	}
	
	public static void clearFieldByXPath(String xPath) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.xpath(xPath));
		element.clear();
	}
	
	public static void executeScript(String jsScript) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		((JavascriptExecutor) driver).executeScript(jsScript);
	}
	
	public static void scrollTableVerticallyToEnd(String tbParentDivId){
		 
		SeleniumUtils.wait(1000);
		String script =	  "var divEl = document.getElementById('"+tbParentDivId+"');"+
				 "var trEls = divEl.getElementsByTagName('tr');"+
				  "var itemNumber = trEls.length;"+
				  
 					"var trEl = divEl.getElementsByTagName('tr')[0];"+
				   
				 
				    "var scrollTo = trEl.offsetTop;"+
				    "divEl.scrollTop = scrollTo;";
		
		SeleniumUtils.executeScript(script);
	}
	
	public static void scrollTableVerticallyToRowNo(String tbParentDivId,int rowNo){
		SeleniumUtils.wait(1000);
		rowNo = rowNo -1;
		String script =	  "var divEl = document.getElementById('"+tbParentDivId+"');"+
				 "var trEls = divEl.getElementsByTagName('tr');"+
				  "var itemNumber = trEls.length;"+
				  
 					"var trEl = divEl.getElementsByTagName('tr')["+rowNo+"];"+
				   
				 
				    "var scrollTo = trEl.offsetTop;"+
				    "divEl.scrollTop = scrollTo;";
		
		SeleniumUtils.executeScript(script);
	}
	public static void scrollTableHorizontallyToEnd(String tbParentDivId){
		SeleniumUtils.wait(1000);
		String script =	  "var divEl = document.getElementById('"+tbParentDivId+"');"+
				 "var trEls = divEl.getElementsByTagName('tr');"+
				  "var itemNumber = trEls.length;"+
				  
 					"var trEl = divEl.getElementsByTagName('tr')[0];"+
				   
				    "var parentWidth = divEl.offsetWidth;"+
				    "var childWidth = trEl.offsetWidth;"+
				    "divEl.scrollLeft = childWidth - parentWidth;";
		
		SeleniumUtils.executeScript(script);

	}
	
	public static void scrollTableHorizontallyToColNo(String tbParentDivId,int colNo){
		SeleniumUtils.wait(1000);
		colNo = colNo -1;
		String script =	  "var divEl = document.getElementById('"+tbParentDivId+"');"+
				 "var trEls = divEl.getElementsByTagName('tr');"+
				  "var itemNumber = trEls.length;"+
				  
 					"var trEl = divEl.getElementsByTagName('tr')[0];"+
				   
				 
				    "var selectedTd = trEl.childNodes["+colNo+"];"+
				    "var tdOffset = selectedTd.offsetLeft;"+
				    "var parentWidth = divEl.offsetWidth;"+
				    "var childWidth = trEl.offsetWidth;"+
				    "var maxScroll = childWidth - parentWidth;"+
				    "if(tdOffset > maxScroll){tdOffset = maxScroll};"+
				    "divEl.scrollLeft = tdOffset;";
		
		SeleniumUtils.executeScript(script);

	}
	
	public static boolean hasClass(String cssSelector,String className) {
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element = driver.findElement(By.cssSelector(cssSelector));
	    String classes = element.getAttribute("class");
	    for (String classCotains : classes.split(" ")) {
	        if (classCotains.equals(className)) {
	            return true;
	        }
	    }

	    return false;
	}

	
	public static void selectOptionFromListByIndex(String selectCssSelector,int index){
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element=driver.findElement(By.cssSelector(selectCssSelector));
		Select se=new Select(element);
		se.selectByIndex(index);
	}
	
	public static void selectOptionFromListByValue(String selectCssSelector, String value){
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebElement element=driver.findElement(By.cssSelector(selectCssSelector));
		Select se=new Select(element);
		se.selectByValue(value);
	}
	
	public static void maximizeWindow(){
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		driver.manage().window().maximize();
	}

	public static void clickAtPositionOnElement(String cssSelector, int xOffset, int yOffset){
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		WebDriverWait wait = new WebDriverWait(driver, 300);
		Actions builder = SeleniumUtils.getActionManager();
		WebElement element = wait.until(ExpectedConditions
		            .elementToBeClickable(By.cssSelector(cssSelector)));
		
		Action action = builder.moveToElement(element, xOffset, yOffset).click().build();
		action.perform();
		SeleniumUtils.wait(1000);
	}
	
	public static void selectOptionByText(String selectId, String visibleText){
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		Select dropdown = new Select(driver.findElement(By.id(selectId)));
		dropdown.selectByVisibleText(visibleText);

	}
	public static void selectOptionByIndex(String selectId, int index){
		WebDriver driver = ProjectManager.sharedManager().getWebDriver();
		Select dropdown = new Select(driver.findElement(By.id(selectId)));
		dropdown.selectByIndex(index);

	}
}
