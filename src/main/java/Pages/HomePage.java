package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import utility.Constant;
public class HomePage extends BasePage{
	

	WebDriver driver;
	
	@FindBy(id ="search")
	WebElement searchField;
	
	@FindBy(xpath ="//a[text()='Create an Account']")
	WebElement createNewAccount;

	@FindBy(xpath = "//ul/li/span[@class='logged-in']")
	WebElement loggedInUser;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void verifyLoggedInUserOnHomeScreen(String loggedUsername) throws InterruptedException {
		verifyPageHeadingText(Constant.HOME);
		wait.waitForElementVisible(loggedInUser);
		String loggedInUsername = loggedInUser.getText();
		Assert.assertTrue(loggedInUsername.contains(loggedUsername) ,Constant.LOGGED_IN_USER_NAME+loggedUsername);
		logger.log(Status.PASS,Constant.VERIFIED_LOGGED_IN_USER+ loggedInUsername);

}


	public void searchItem(String item) {
		
		searchField.click();
		searchField.clear();
		searchField.sendKeys(item);
		searchField.sendKeys(Keys.RETURN);
	}
}

