package OpenCart.eleUtility;


import java.time.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import OpenCart.factory.driverFactory;

public class ElementUtil {
	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil= new JavaScriptUtil(driver);
	}

	public WebElement getElement(By locater) {
		WebElement element= driver.findElement(locater);
		if (Boolean.parseBoolean(driverFactory.higlight)) {
		jsUtil.flash(element);
		}
		return element;

	}

	public WebElement getElement(By locater, int timeout) {
		return waitForElementVisible(locater, timeout);
		// return driver.findElement(locater);

	}

	public List<WebElement> getElements(By locater) {
		return driver.findElements(locater);
	}

	public void doSendKeys(By locater, String value) {
		WebElement element=getElement(locater);
		element.clear();
		element.sendKeys(value);
	}

	public boolean doElementIsDisplayed(By locater) {
		return getElement(locater).isDisplayed();

	}

	public void doClick(By locater) {
		getElement(locater).click();
	}

	public void doActionSendKeys(By locater, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locater), value).build().perform();
	}

	public void doActionClick(By locater) {
		Actions act = new Actions(driver);
		act.click(getElement(locater)).build().perform();

	}

	public String doElementsGetText(By locater) {
		return getElement(locater).getText();
	}

	public void getElementsAttributes(By locater, String AtrName) {
		List<WebElement> eleList = getElements(locater);
		for (WebElement e : eleList) {
			String AtrValue = e.getAttribute(AtrName);
			System.out.println(AtrValue);
		}

	}

	// *****************select based drop drown *******************
	public void doSelectDropDownBytext(By locater, String text) {
		Select select = new Select(getElement(locater));
		select.selectByVisibleText(text);
		;

	}

	public void doSelectDropDownByValue(By locater, String value) {
		Select select = new Select(getElement(locater));
		select.selectByValue(value);
	}

	public void doSelectDropDownByIndex(By locater, int index) {
		Select select = new Select(getElement(locater));
		select.selectByIndex(index);
	}

	public List<WebElement> getDropDownOptionList(By locater) {
		Select select = new Select(getElement(locater));
		return select.getOptions();

	}

	public int getTotalDropDownOptions(By locater) {
		int optionCount = getDropDownOptionList(locater).size();
		System.out.println("total count ===>" + optionCount);
		return optionCount;

	}

	public void getDropDownValue(By locater, String ExpValue) {
		List<WebElement> optionList = getDropDownOptionList(locater);
		for (WebElement e : optionList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(ExpValue)) {
				e.click();
				break;
			}
		}

	}

	public List<String> getDropDownOptionTextList(By locater) {
		List<WebElement> optionList = getDropDownOptionList(locater);
		List<String> optionTextList = new ArrayList<String>();
		for (WebElement e : optionList) {
			String text = e.getText();
			optionTextList.add(text);

		}
		return optionTextList;

	}

	public void doSearch(By sugListLocater, String suggName) {

		List<WebElement> sugList = getElements(sugListLocater);
		System.out.println(sugList.size());
		for (WebElement e : sugList) {

			String text = e.getText();

			System.out.println(text);

			if (text.contains(suggName)) {
				e.click();
				break;
			}

		}

	}

	// **************wait*********************
	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does notnecessarily mean that the element is visible.
	 * 
	 * @param locater
	 * @param timeout
	 */

	public WebElement waitForElementPresence(By locater, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfElementLocated(locater));

	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible.Visibility means that the element is not only displayed but, also
	 * has a height and width that isgreater than 0.
	 * 
	 * @param locater
	 * @param timeout
	 */
	public WebElement waitForElementVisible(By locater, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locater));

	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locater
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForAllElementPresence(By locater, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locater));

	}

	/**
	 * An expectation for checking that all elements present on the web page that
	 * match the locatorare visible. Visibility means that the elements are not only
	 * displayed but also have a heightand width that is greater than 0.
	 * 
	 * @param locater
	 * @param timeout
	 * @return
	 */
	public List<WebElement> waitForElementAllVisible(By locater, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locater));
	}

	// *************waitforAlert**********
	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public String getAlertText(int timeout) {
		String text = waitForAlert(timeout).getText();
		System.out.println(text);
		return text;
	}

	public void alertAccept(int timeout) {
		waitForAlert(timeout).accept();

	}

	public void alertDismiss(int timeout) {
		waitForAlert(timeout).dismiss();

	}

	public void alertSenDkeys(int timeout, String v) {
		waitForAlert(timeout).sendKeys(v);

	}

	public String waitForTitleContainsAndFeatch(int timeout, String titleFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleContains(titleFractionValue));
		return driver.getTitle();
	}

	public String waitForTitleAndFeatch(int timeout, String titleValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.titleIs(titleValue));// for whole title
		return driver.getTitle();
	}

	public String waitForURLContainAndFeatch(int timeout, String titleFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(titleFractionValue));
		return driver.getCurrentUrl();
	}

	public boolean waitForURLContain(int timeout, String titleFractionValue) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlContains(titleFractionValue));

	}

	public boolean waitForURLis(int timeout, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.urlToBe(url));// for whole url

	}

	// ***********WaitForframeHandel**************
	public  void  frameToBeAvailableAndSwitchToItByIdOrName(int timeout, String idOrName) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout)) ;
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(idOrName));
		
	}
	public  void  frameToBeAvailableAndSwitchToItByIndex(int timeout, int indexNum) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout)) ;
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(indexNum));
		
	}
	public  void  frameToBeAvailableAndSwitchToItByLocater(int timeout, By locater) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout)) ;
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locater));
		
	}
	public  void  frameToBeAvailableAndSwitchToItByFrameElement(int timeout, WebElement FrameElement) {
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(timeout)) ;
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameElement));
		
	}
	
	               //******click**********************8
	/**
	 * An expectation for checking an element is visible and enabled such that you can click it.
	 * @param timeout
	 * @param locater
	 */
	
	public  void waitForClick(int timeout, By locater) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.elementToBeClickable(locater)).click();

	}

	public  WebElement waitForEleToBClick(int timeout, By locater) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.elementToBeClickable(locater));

	}

	public  void doClickActionWithWait(int timeout, By locater) {
		WebElement ele = waitForEleToBClick(timeout, locater);
		Actions act = new Actions(driver);
		act.click(ele).build().perform();

	}
	
	///######fluentWait#############
	
	public  WebElement waitForElementPresentWithFluentWait(int timout , int interval , By locater) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
				withTimeout(Duration.ofSeconds(timout))
				.ignoring(NoSuchElementException.class).
				ignoring(StaleElementReferenceException.class)
				.pollingEvery(Duration.ofSeconds(interval)).
				withMessage("element is not found...........");
		return wait.until(ExpectedConditions.elementToBeClickable(locater));
		
	}
	public  void waitForAlertPresentWithFluentWait(int timout , int interval ) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
				withTimeout(Duration.ofSeconds(timout))
				.ignoring(NoAlertPresentException.class)
				.pollingEvery(Duration.ofSeconds(interval)).
				withMessage("Alert is not found...........");
		 wait.until(ExpectedConditions.alertIsPresent());
		
	}

	

}
