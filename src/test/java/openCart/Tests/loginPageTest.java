package openCart.Tests;

import org.testng.Assert;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import openCart.base.baseTest;

@Epic("EPIC - 100 : design login for opencart app")
@Story("US-LOGIN: 101: design login page feature for opencart")
public class loginPageTest extends baseTest {
	@Severity(SeverityLevel.TRIVIAL)
	@Description("....getting the title of the page.....tester ----SHWETA")
	@Test
	public void LoginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, "Account Login");

	}
	
@Severity(SeverityLevel.NORMAL)
@Description(".....Checking the url of the page ....tester -----SHWETA")
	@Test
	public void LoginPageUrlTest() {
		String actUrl = loginPage.getLoginPageurl();
		Assert.assertTrue(actUrl.contains("?route=account/login"));
	}
	@Test
	@Severity(SeverityLevel.CRITICAL)
	@Description(".....Checking  Forgeot Psw Link....tester -----SHWETA")
	public void LoginPageForgeotPswLinkTest() {
		Assert.assertTrue(loginPage.isForgetPswLinkExist());
	}
	@Severity(SeverityLevel.NORMAL)
	@Description(".....chekcing the register link Exist or not  ....tester -----SHWETA")
	@Test
	public void LoginPageRegisterLinkIsExist() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	@Severity(SeverityLevel.BLOCKER)
	@Description(".....checking user is able to login or not ....tester -----SHWETA")
	@Test

	public void dOLoginTest() {
		accountPage=loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertTrue(accountPage.isLogOutLinkExist());
	}

}
