package OpenCart.factory;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.aspectj.util.FileUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class driverFactory {
	public WebDriver driver;
	public Properties prop;
	public OptionManeger optionManeger;
	public static String higlight;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is initialized the driver on the based of given browserName
	 * 
	 * @param BrowserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {
		String BrowserName = prop.getProperty("browser").toLowerCase().trim();
		optionManeger = new OptionManeger(prop);
		higlight = prop.getProperty("highlight").trim();

		System.out.println("browser Name ::" + BrowserName);
		if (BrowserName.equalsIgnoreCase("chrome")) {
			// driver = new ChromeDriver(optionManeger.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManeger.getChromeOptions()));


		} else if (BrowserName.equalsIgnoreCase("edge")) {
			// driver = new EdgeDriver(optionManeger.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionManeger.getEdgeOptions()));
			


		} else if (BrowserName.equalsIgnoreCase("firefox")) {

			// driver = new FirefoxDriver(optionManeger.getfireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionManeger.getfireFoxOptions()));

		} else {
			System.out.println("plz pass the right browser name ..." + BrowserName);
		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();

	}

	/*
	 * get the local Thread copy of the driver
	 * 
	 * synchronized--- every thered will give their own individual copy
	 */

	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}
	// fileInputStream is used to interect he file

	/**
	 * This method is reading file from the .properties file
	 * 
	 * @return
	 */

	public Properties initProp() {

		// maven clean install -denv="Stage"
		prop = new Properties();
		FileInputStream ip = null;
		String EnvName = System.getProperty("env");
		System.out.println("Running test cases on Env :" + EnvName);
		try {
			if (EnvName == null) {
				System.out.println("Env is not found....Running test cases on Qa env");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");

			} else {
				switch (EnvName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");

					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");

					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");

					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/prod.config.properties");

					break;

				default:
					System.out.println("Wrong env passes ... no need to run the test cases...");
					break;
				}

			}
		} catch (FileNotFoundException e) {

		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;

	}

	/*
	 * Take screen short
	 */
	public static String getScreenShot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtil.copyFile(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

}
