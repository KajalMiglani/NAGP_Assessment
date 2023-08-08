package Pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


import com.aventstack.extentreports.Status;

import utility.Constant;
public class SearchResultPage extends BasePage {



	WebDriver driver;
	

	@FindBy(xpath = "//div[@class='message notice']/div")
	WebElement errorMessage;

	@FindBy(xpath = "//span[@class='toolbar-number']")
	WebElement searchResultNum;

	@FindBy(xpath = "//a[@class='product-item-link']")
	List<WebElement> searchResultList_ItemName;
	

	public SearchResultPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifySearch(String itemName) {
		// TODO Auto-generated method stub
		verifyPageHeadingText(Constant.SEARCH_RESULTS_FOR);

		try {
			Assert.assertTrue(searchResultNum.isDisplayed());
			Assert.assertTrue(searchResultList_ItemName.get(0).getText().contains(itemName));
			logger.log(Status.PASS, Constant.SEARCH_RESULTS_DISPLAYED_ON_SCREEN_FOR_ITEM + itemName);

		} catch (NoSuchElementException e) {
			// TODO: handle exception
			Assert.assertTrue(errorMessage.isDisplayed());
			if (itemName.length() < 3) {
				Assert.assertTrue(errorMessage.getText().contains(Constant.MINIMUM_SEARCH_QUERY_LENGTH_IS_3),
						Constant.MESSAGE_DISPLAYED_MINIMUM_SEARCH_QUERY_LENGTH_IS_3);
				logger.log(Status.PASS,
						Constant.SEARCH_MESSAGE_INVALID_ITEM);
			} else {
				Assert.assertTrue(errorMessage.getText().contains(Constant.YOUR_SEARCH_RETURNED_NO_RESULTS),
						Constant.MESSAGE_DISPLAYED_YOUR_SEARCH_RETURNED_NO_RESULTS);
				logger.log(Status.PASS,
						Constant.SEARCH_MESSAGE_UNAVAILABLE_ITEM);
			}
		}
	}

	public void clickFirstSearchItem() throws InterruptedException {
	wait.waitForElementVisible(searchResultList_ItemName.get(0));
		searchResultList_ItemName.get(0).click();
	}


						

		
	
	}
