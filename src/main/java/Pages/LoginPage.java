package pages;

import java.io.File;
import utility.Constant;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import utility.PropertyFileReader;

public class LoginPage extends BasePage {


	WebDriver driver;
	
	@FindBy(xpath = "(//a[contains(text() ,'Sign In')])[1]")
	WebElement SignInLink;
	
	@FindBy(id = "email")
	WebElement emailField;
	
	@FindBy(id = "pass")
	WebElement passwordField;
	
	@FindBy(id = "send2")
	WebElement signInBtn;

	
	@FindBy(className = "post-title")
	WebElement loggedInMessage;
	
	@FindBy(xpath = "//div[@class = 'post-content']//strong")
	WebElement loggedInSubMessage;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Login into application
	public void login(String username, String password) {
		SignInLink.click();
		emailField.sendKeys(username);
		passwordField.sendKeys(password);
		logger.log(Status.INFO,Constant.ENTERED_LOG_IN_DETAILS_EMAIL+username+Constant.PASSWORD3+password);
		signInBtn.click();
	}
	


	public void validateLoginScreen() throws IOException {
		//using explicitWait for the visibility of LoggedIn post title element
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("post-title")));
		//Taking a screenshot of LoggedIn post title element
		File screenshot = loggedInMessage.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("./screenshot.jpg"));
		//Taking a screenshot of entire LoggedIn page 
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("./screenshotTwo.jpg"));
		//TestNG Assertions
		Assert.assertEquals(loggedInMessage.getText(), "Logged In Successfully");
		Properties prop = PropertyFileReader.readProperty("testData.properties");
		Assert.assertEquals(loggedInSubMessage.getText(), 
				String.format("Congratulations %s. You successfully logged in!", prop.getProperty("username")));
		logger.log(Status.PASS,"Verified logged in message "+loggedInMessage);
	}


}
