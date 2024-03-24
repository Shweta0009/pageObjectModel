package OpenCart.Pages;

import java.util.HashMap;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import OpenCart.constant.Constant;
import OpenCart.eleUtility.ElementUtil;

public class productInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By headValue = By.tagName("h1");
	private By productImg = By.cssSelector("li.image-additional");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private Map<String, String> productInfoMap;
	private By quantity = By.id("input-quantity");
	private By addToCrtBtn = By.id("button-cart");
	private By succMsg = By.cssSelector("div.alert.alert-success");

	public productInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderValue() {
		String headrText = eleUtil.doElementsGetText(headValue);
		System.out.println("headrText" + headrText);
		return headrText;
	}

	public int getProductInfoCount() {
		int imgCount = eleUtil.waitForElementAllVisible(productImg, Constant.DEFAULT_MEDIUM_TIMEOUT).size();
		System.out.println("product image  count::" + imgCount);
		return imgCount;

	}

	public void enterQuantity(int qty) {
		System.out.println("quantity" + qty);
		eleUtil.doActionSendKeys(quantity, String.valueOf(qty));
	}

	public String addToCart() {
		eleUtil.doActionClick(addToCrtBtn);
		String msgSuccess = eleUtil.waitForElementPresence(succMsg, Constant.DEFAULT_MEDIUM_TIMEOUT).getText();
		StringBuilder sb = new StringBuilder(msgSuccess);
		String msg = sb.substring(0, msgSuccess.length() - 1).replace("\n", "");

		System.out.println("Success massage ::" + msg);
		return msg;
	}

	public Map<String, String> getProductInfo() {

//		Product Code: Product 6
//		Availability: 2-3 Days
		// productInfoMap = new HashMap<String, String>(); it does not maintain the
		// order
		productInfoMap = new LinkedHashMap<String, String>(); // it does maintain the order
		// productInfoMap = new TreeMap<String, String>();//short

		// Header
		productInfoMap.put("productName", getProductHeaderValue());
		getProductMetaData();
		getProductPricing();
		return productInfoMap;

	}

	// produtct meta deta
	private void getProductMetaData() {
		List<WebElement> mataList = eleUtil.getElements(productMetaData);
		for (WebElement e : mataList) {
			String meta = e.getText();
			String[] mataInfo = meta.split(":");
			String key = mataInfo[0].trim();
			String value = mataInfo[1].trim();
			productInfoMap.put(key, value);

		}
	}

	// productPricing
	private void getProductPricing() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exText = priceList.get(1).getText();
		String extValue = exText.split(":")[1].trim();
		productInfoMap.put("productPrice", price);
		productInfoMap.put("extText", extValue);
	}

}
