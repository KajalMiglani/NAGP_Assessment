package tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
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
			String browser = prop.getProperty("BROWSER");
			initializeDriver(browser);
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

	
	public void initializeDriver(String browser) {
//		switch (browser) {
//		case "chrome":
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		break;
//		case "firefox":
//		WebDriverManager.firefoxdriver().setup();
//		driver = new FirefoxDriver();
//		break;
//		case "edge":
//		WebDriverManager.edgedriver().setup();
//		driver = new EdgeDriver();
//		break;
//		case "safari":
//		driver = new SafariDriver();
//		break;
//		case "ie":
//		WebDriverManager.iedriver().setup();
//		InternetExplorerOptions capabilities = new InternetExplorerOptions()
//		.requireWindowFocus();
//		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
//		driver= new InternetExplorerDriver(capabilities);
//		
//		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		System.out.println("Incorrect value for Browser passed so started chrome driver");
//		}
	}

		
	
	 
	

	public void quit() {
		driver.quit();
	}

}
