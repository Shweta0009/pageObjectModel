package openCart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import OpenCart.Pages.AccountPage;
import OpenCart.Pages.LoginPage;
import OpenCart.Pages.RegistrationPage;
import OpenCart.Pages.productInfoPage;
import OpenCart.Pages.searchPage;
import OpenCart.factory.driverFactory;

public class baseTest {
	driverFactory df;
	WebDriver  driver;
	 protected Properties prop;
	protected LoginPage loginPage;
	protected  AccountPage accountPage ;
	protected searchPage sp;
	protected productInfoPage ProductInfoPage;
	protected SoftAssert softAssert;
	protected RegistrationPage register;
	
	@BeforeTest
	public void setUp() {
		df= new driverFactory();
		prop=df.initProp();
		driver=df.initDriver(prop);
		loginPage=new LoginPage(driver);
		softAssert=new SoftAssert();
		
		
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
