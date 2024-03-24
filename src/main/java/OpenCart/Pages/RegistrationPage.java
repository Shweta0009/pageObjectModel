package OpenCart.Pages;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import OpenCart.constant.Constant;
import OpenCart.eleUtility.ElementUtil;

public class RegistrationPage {
	private WebDriver driver;
	private ElementUtil eleUtil;
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By teliphone = By.id("input-telephone");
	private By confirmPsw = By.id("input-confirm");

	private By psw = By.id("input-password");
	private By subYes = By.xpath("//label[normalize-space()='Yes']/input[@type='radio']");

	private By subNo = By.xpath("//label[normalize-space()='No']/input[@type='radio']");
	private By agreeBox = By.xpath("//input[@type='checkbox']");
	private By continuBtn = By.xpath("//input[@type='submit']");

	private By RegsuccMsg = By.xpath("//h1[text()='Your Account Has Been Created!']");
	private By logout = By.linkText("Logout");
	private By register = By.linkText("Register");

	

	public  RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}

	public boolean registerUser(String firstName, String lastName, String email, String teliphone, String psw,
			String subscribe) {
		eleUtil.waitForElementPresence(this.firstName, Constant.DEFAULT_MEDIUM_TIMEOUT).sendKeys(firstName);
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.teliphone, teliphone);
		eleUtil.doSendKeys(this.psw, psw);
		eleUtil.doSendKeys(this.confirmPsw, psw);
		if (subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(subYes);
		} else {
			eleUtil.doClick(subNo);

		}
		eleUtil.doActionClick(agreeBox);
		eleUtil.doClick(continuBtn);
		
		String succMsg=eleUtil.waitForElementVisible(RegsuccMsg, Constant.DEFAULT_MEDIUM_TIMEOUT).getText();
		  System.out.println("Success msg:::" + succMsg);
		if (succMsg.contains(Constant.USER_REG_SUCC_MSG)) {
			
			eleUtil.doClick(logout);
			eleUtil.doClick(register);
		return true;
		
		}else {
			return false;
		}
		
	
	}
}
	
	


