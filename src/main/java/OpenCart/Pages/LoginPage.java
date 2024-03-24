package OpenCart.Pages;

import org.openqa.selenium.By;


import org.openqa.selenium.WebDriver;

import OpenCart.constant.Constant;
import OpenCart.eleUtility.ElementUtil;
import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	// by locater
	private By emailId = By.id("input-email");
	private By psw = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value=\"Login\"]");
	private By forgetPswLink = By.linkText("Forgotten Password");
	private By register=By.linkText("Register");

	// page couns..

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3 page action method
	@Step("getting login page title..")
	public String getLoginPageTitle() {
		String pagetitle = eleUtil.waitForTitleAndFeatch(Constant.DEFAULT_SHORT_TIMEOUT,Constant.LOGIN_PAGE_TITLE);
		System.out.println(pagetitle);
		return pagetitle;
	}
	@Step("getting login page url..")
	public String getLoginPageurl() {
		String pageurl = eleUtil.waitForURLContainAndFeatch(Constant.DEFAULT_SHORT_TIMEOUT,Constant.LOGIN_PAGE_URL_FRACTION);
		System.out.println(pageurl);
		return pageurl;
	}
	@Step("checking Forget Psw Link Exist..")

	public boolean isForgetPswLinkExist() {
		return eleUtil.waitForElementVisible(forgetPswLink, Constant.DEFAULT_MEDIUM_TIMEOUT).isDisplayed();
	}
	@Step("checking Register Link Exist..")
   public boolean isRegisterLinkExist() {
	   return eleUtil.waitForElementVisible(register, Constant.DEFAULT_SHORT_TIMEOUT).isDisplayed();
   }
	@Step("Login with user: {0} and password: {1}")
	public AccountPage doLogin(String un, String pasw) {
		System.out.println(" Login credential is---> " + un + ":" + pasw);
		eleUtil.waitForElementVisible(emailId, Constant.DEFAULT_MEDIUM_TIMEOUT).sendKeys(un);
		;
		eleUtil.doSendKeys(psw, pasw);
		eleUtil.doClick(loginBtn);
         return new AccountPage(driver);

	}
	public RegistrationPage navigateToRegister() {
		eleUtil.doClick(register);
		return new RegistrationPage(driver);
	}

}
