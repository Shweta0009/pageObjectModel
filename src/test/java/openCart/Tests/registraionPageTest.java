package openCart.Tests;

import java.util.Random;



import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import OpenCart.constant.Constant;
import OpenCart.eleUtility.excelUtil;
import openCart.base.baseTest;

public class registraionPageTest extends baseTest {
	
  @BeforeClass
  public void regPageSetUp() {
	  register=loginPage.navigateToRegister();
  }
  
  public String getRandamEmail() {
	  Random random=new Random();
	String email= "auto"+ System.currentTimeMillis()+"@gmail.com";
	 // String email="auto"+random.nextInt(1000) + "@gmail.com";
	  return email;
  }
  @DataProvider
  public Object[][] getRegTestData() {
	 Object[] [] testData=excelUtil.getTestData(Constant.REGISTER_SHEET_NAME);
  return testData;
  }
  
  @Test(dataProvider="getRegTestData")
  public void userRegTest(String firstName, String lastName,String teliphone, String psw , String subscribe) {
	  Assert.assertTrue(register.registerUser(firstName,lastName,  getRandamEmail(), teliphone, psw, subscribe));
  }

}
