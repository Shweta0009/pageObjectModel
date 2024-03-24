package openCart.Tests;

import java.util.List;




import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import OpenCart.constant.Constant;
import openCart.base.baseTest;

public class accountPageTest extends baseTest {

	@BeforeClass
	public void accPageSetUp() {
		accountPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void accpageTitleTest() {
		String accTitle = accountPage.AccPagetitle();
		Assert.assertEquals(accTitle, Constant.ACCOUNT_PAGE_TITLE);
	}

	@Test
	public void accpageurleTest() {
		String accUrl = accountPage.AccPageUrl();
		Assert.assertTrue(accUrl.contains(Constant.ACCOUNT_PAGE_URL_FRACTION));
	}

	@Test
	public void isSearchExistTest() {
		Assert.assertTrue(accountPage.isSearchExist());
	}

	@Test
	public void isLogountLinkExistTest() {
		Assert.assertTrue(accountPage.isLogOutLinkExist());
	}

	@Test
	public void accPageHeadTest() {
		List<String> accHead = accountPage.accPageHeader();
		Assert.assertEquals(accHead.size(), Constant.ACCOUNT_PAGE_HEADR_COUNT);
	}

	@Test
	public void accPageHeadListTest() {
		List<String> accHeadList = accountPage.accPageHeader();
		System.out.println("actual account page haeder List ::::" + accHeadList);
		System.out.println("actual account page haeder List ::::" + Constant.ACCOUNT_PAGE_EXPECTED_HEADERS_LIST);
		Assert.assertEquals(accHeadList, Constant.ACCOUNT_PAGE_EXPECTED_HEADERS_LIST);
	}

	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] { { "iPhone" }, { "samsung" }, { "apple" } };
	}

	@Test(dataProvider = "getProductData")
	public void searchProductCountTest(String searchKey) {
		sp = accountPage.performSearch(searchKey);
		Assert.assertTrue(sp.getSearchproductCount() > 0);

	}

	@DataProvider 
	public Object[][]getProductTestData(){
		return new  Object[][] {
			{"iPhone" ,"iPhone"},
			{"samsung" ,"Samsung SyncMaster 941BW"},
			{"samsung" ,"Samsung Galaxy Tab 10.1"}
           ,{"apple","Apple Cinema 30\""}
		};
	}
	@Test(dataProvider = "getProductTestData")

	public void searchProductTest(String searchKey , String productName) {
		sp = accountPage.performSearch(searchKey);
		if (sp.getSearchproductCount() > 0) {
			ProductInfoPage = sp.selectProduct(productName);
			String pHeadr = ProductInfoPage.getProductHeaderValue();
			Assert.assertEquals(pHeadr, productName);
		}

	}

}
