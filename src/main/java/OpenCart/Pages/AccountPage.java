package OpenCart.Pages;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import OpenCart.constant.Constant;
import OpenCart.eleUtility.ElementUtil;

public class AccountPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// by
	private By search = By.name("search");
	 private By logout = By.linkText("Logout");
	 private By HeaderSection = By.cssSelector("div#content h2");
	 private By SearchIcon=By.cssSelector("div#search button");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
	//

	public String AccPagetitle() {
		String accUrl = eleUtil.waitForTitleAndFeatch(Constant.DEFAULT_SHORT_TIMEOUT, Constant.ACCOUNT_PAGE_TITLE);
		System.out.println("ACCount Page url ::" + accUrl);
		return accUrl;
	}

	public String AccPageUrl() {
		String accTitle = eleUtil.waitForURLContainAndFeatch(Constant.DEFAULT_SHORT_TIMEOUT,
				Constant.ACCOUNT_PAGE_URL_FRACTION);
		System.out.println(" Account Page Title::" + accTitle);
		return accTitle;
	}

	public Boolean isSearchExist() {
		return eleUtil.waitForElementVisible(search, Constant.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
	}

	public Boolean isLogOutLinkExist() {
		return eleUtil.waitForElementVisible(logout, Constant.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
	}

	public List<String> accPageHeader() {
		List<WebElement> headList = eleUtil.waitForElementAllVisible(HeaderSection, Constant.DEFAULT_MEDIUM_TIMEOUT);

		List<String> accHeadList = new ArrayList<String>();
		for (WebElement e : headList) {
			String text = e.getText();
			accHeadList.add(text);

		}
		return accHeadList;

	}

	public searchPage performSearch(String searchKeys) {
		if (isSearchExist()) {
			eleUtil.doSendKeys(search, searchKeys);
			eleUtil.doClick(SearchIcon);
			return new searchPage(driver);
			
		}
		else {
			System.out.println("Search is not present");
			return null;
		}
		
		
	}

}
