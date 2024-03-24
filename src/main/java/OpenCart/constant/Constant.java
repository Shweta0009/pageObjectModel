package OpenCart.constant;

import java.util.Arrays;
import java.util.List;

public class Constant {
	
	public static final int DEFAULT_MEDIUM_TIMEOUT=10;
	public static final int DEFAULT_SHORT_TIMEOUT=5;
	public static final int DEFAULT_LONG_TIMEOUT=20;
 
	
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION ="?route=account/login";
	
	public static final String ACCOUNT_PAGE_TITLE="My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION ="/index.php?route=account/account";
	public static final int ACCOUNT_PAGE_HEADR_COUNT = 4;
	public static final List<String> ACCOUNT_PAGE_EXPECTED_HEADERS_LIST=Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final String USER_REG_SUCC_MSG = "Your Account Has Been Created!";
	
	//**********cheetSheet***********
	
	public static final String REGISTER_SHEET_NAME = "register";

}
