package tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.BasePage;
import utility.Constant;
import utility.PropertyFileReader;
import utility.ScreenshotUtility;

public class BaseTest {

	
	public static WebDriver driver;
	public static String testDataPath = System.getProperty(Constant.USER_DIR)+File.separator+Constant.TEST_DATA_PATH;

	
	@BeforeTest
	public void beforeTestFunction() {
		BasePage.createExtentReports();

	}

	@BeforeMethod
	public void beforeMethodFunction(Method testMethod) {
		
		BasePage.createExtentTest(testMethod);


		Properties prop = null;
		try {
			prop = PropertyFileReader.readProperty(System.getProperty(Constant.USER_DIR)+File.separator+Constant.GLOBAL_CONFIG_PROPERTIES_PATH);
			initializeDriver();
			driver.manage().window().maximize();
			String url = prop.getProperty("URL");
			driver.get(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTestFunction() {
		BasePage.flushExtentReport();
	}

	@AfterMethod
	public void afterMethodFunction(ITestResult result) {
		BasePage.publishResult(result);
		ScreenshotUtility screen= new ScreenshotUtility();
		screen.takeScreenshotOnFailure(result, driver);
		driver.quit();
		
	}

	
	public void initializeDriver() {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
	
	}

		
	
	 
	

	public void quit() {
		driver.quit();
	}

}
