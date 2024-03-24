package OpenCart.Pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import OpenCart.constant.Constant;

import OpenCart.eleUtility.ElementUtil;

public class searchPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productCount=By.cssSelector("div#content div.product-layout");

	
	 
	public searchPage(WebDriver driver) {
		this.driver=driver;
		eleUtil= new ElementUtil(driver);
	}
	
	public int  getSearchproductCount() {
		int PrdouctCount= eleUtil.waitForElementAllVisible(productCount,Constant.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("PrdouctCount ::"  +  PrdouctCount);
		return PrdouctCount; 
	
		
	}
	
	
	public productInfoPage selectProduct( String ProducName) {
		By productLink=By.linkText(ProducName);
		eleUtil.waitForElementVisible(productLink, Constant.DEFAULT_MEDIUM_TIMEOUT).click();
		return new productInfoPage(driver);
	}

}
