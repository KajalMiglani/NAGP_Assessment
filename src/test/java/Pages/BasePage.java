package Pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import utility.Constant;
import utility.PropertyFileReader;
import utility.WaitHelper;

public class BasePage {
	public static XSSFWorkbook workbook;
	public static XSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();
	static String sheetName = "Sheet1";
	public String res;

	protected WaitHelper wait;

	public ExtentReporter reporter;
	public static ExtentTest logger;
	public static final ExtentReports extentReports = new ExtentReports();

	
	WebDriver driver;

	@FindBy(xpath = "//h1/span")
	WebElement pageHeading;

	@FindBy(xpath = "//div[@role ='alert']/div/div")
	WebElement Alert;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WaitHelper(driver);
	}

	public void verifyPageHeadingText(String heading) {
		wait.waitForElementVisible(pageHeading);
		String pageLabel = pageHeading.getText();
		Assert.assertTrue(pageLabel.contains(heading), Constant.HEADING_DISPLAYED + pageLabel);
		logger.log(Status.PASS, Constant.VERIFIED_PAGE_HEADING + pageLabel);
	}

	public synchronized static ExtentReports createExtentReports() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty(Constant.USER_DIR) + File.separator + "reports"
				+ File.separator + "ExtentReportResults.html");
		reporter.config().setReportName("TestResult Extent Report");
		extentReports.attachReporter(reporter);
		extentReports.setSystemInfo("Tester Name", "Kajal");
		return extentReports;
	}

	public static void createExtentTest(Method testMethod) {
		// TODO Auto-generated method stub
		logger = extentReports.createTest(testMethod.getName());

	}

	public static void flushExtentReport() {
		// TODO Auto-generated method stub
		extentReports.flush();
	}

	public static void publishResult(ITestResult result) {
		// TODO Auto-generated method stub
		if (result.getStatus() == ITestResult.SUCCESS) {
			String methodName = result.getMethod().getMethodName();
			String logText = Constant.TEST_CASE + methodName + Constant.PASSED;
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
			logger.log(Status.PASS, m);

		} else if (result.getStatus() == ITestResult.FAILURE) {
			String methodName = result.getMethod().getMethodName();
			String logText = Constant.TEST_CASE + methodName + Constant.FAILED;
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
			logger.log(Status.FAIL, m);
			
			
	        }
	}

	public void navigateToHomePage() {
		try {
			driver.get(PropertyFileReader
					.readProperty(System.getProperty(Constant.USER_DIR) + File.separator + Constant.GLOBAL_CONFIG_PROPERTIES_PATH)
					.getProperty(Constant.URL));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyAlertDisplayed(String expectedAlert) throws InterruptedException {
		// TODO Auto-generated method stub
		wait.waitForElementVisible(Alert);
		Assert.assertTrue(Alert.getText().contains(expectedAlert), Constant.ALERT_DISPLAYED + expectedAlert);
		logger.log(Status.PASS, Constant.ALERT_DISPLAYED + expectedAlert);
	}

	@DataProvider(name = "ReadDataFromExcel")
	public static Object[][] ReadDataFromExcel() throws IOException {

		FileInputStream fis = new FileInputStream(Constant.PATH_ADDRESS_XLSX);
		workbook = new XSSFWorkbook(fis);
		worksheet = workbook.getSheet(sheetName);
		XSSFRow Row = worksheet.getRow(0);
		int RowNum = worksheet.getPhysicalNumberOfRows();
		int ColNum = Row.getLastCellNum();

		Object Data[][] = new Object[RowNum - 1][ColNum];

		for (int i = 0; i < RowNum - 1; i++) {
			XSSFRow row = worksheet.getRow(i + 1);
			for (int j = 0; j < ColNum; j++) {
				if (row == null) {
					Data[i][j] = "";
				} else {
					XSSFCell cell = row.getCell(j);
					if (cell == null)
						Data[i][j] = "";
					else {
						String value = formatter.formatCellValue(cell);
						Data[i][j] = value;
					}
				}
			}
		}
		return Data;
	}
	


}
