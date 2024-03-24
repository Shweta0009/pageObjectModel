package openCart.Tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import openCart.base.baseTest;

public class ProductInfoTest extends baseTest{
	@BeforeClass
	public void accPageSetUp() {
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}
	@DataProvider 
	public Object[][]getProductImageTestData(){
		return new  Object[][] {
			{"iPhone" ,"iPhone" ,5},
			{"samsung" ,"Samsung SyncMaster 941BW",-1},
			{"samsung" ,"Samsung Galaxy Tab 10.1",6}
           ,{"apple","Apple Cinema 30\"",5}
		};
	}

	@Test(dataProvider="getProductImageTestData")
	public void productImageCountTest(String serachKeys , String productName , int impageCOUNT) {
		sp=accountPage.performSearch(serachKeys);
		ProductInfoPage=sp.selectProduct(productName);
		int actImgCount=ProductInfoPage.getProductInfoCount();
		Assert.assertEquals(actImgCount, impageCOUNT);
		
	}
	
	@Test
	
	public void productInfoTest() {
		sp=accountPage.performSearch("samsung");
		ProductInfoPage=sp.selectProduct("Samsung SyncMaster 941BW");
		Map<String, String> actProductInfo=ProductInfoPage.getProductInfo();
		System.out.println(actProductInfo);
		softAssert.assertEquals(actProductInfo.get("Availability"),"2-3 Days");	
		softAssert.assertEquals(actProductInfo.get("Product Code"),"Product 6");
		softAssert.assertEquals(actProductInfo.get("extText"),"$200.00");
          softAssert.assertEquals(actProductInfo.get("productName"),"Samsung SyncMaster 941BW");
          softAssert.assertAll();
	}
	@DataProvider
	public Object[][]addToCart(){
		return new Object[][] {
			{"samsung","Samsung SyncMaster 941BW"},
			{"iPhone" ,"iPhone"}
			
		};
	}
		
		
	
	@Test(dataProvider="addToCart")
	
	public void addToCartTest(String searchKey , String productName) {
		
		sp=accountPage.performSearch(searchKey);
		ProductInfoPage=sp.selectProduct(productName);
		ProductInfoPage.enterQuantity(2);
	    String actCartMsg=	ProductInfoPage.addToCart();
	    softAssert.assertTrue(actCartMsg.indexOf("shopping cart")>=0);
	    softAssert.assertTrue(actCartMsg.indexOf(productName)>=0);
       //  softAssert.assertEquals(actCartMsg, " Success: You have added Samsung SyncMaster 941BW to your shopping cart!");
	    softAssert.assertAll();
	    
	    

	}
	

}
