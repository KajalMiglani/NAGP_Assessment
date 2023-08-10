package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import utility.Constant;

public class CheckoutPage extends BasePage {

	

	@FindBy(xpath = "//button[@class='action primary checkout']/span")
	WebElement placeOrderBtn;

	@FindBy(xpath = "//button[@class='button action continue primary']")
	WebElement nextBtn;

	@FindBy(id = "checkout-loader")
	WebElement loader;

	@FindBy(xpath = "//td[@class='col col-method' and text() ='Table Rate']/preceding-sibling::td/input[@type='radio']")
	WebElement shippingmethodTableRateRadioBtn;

	@FindBy(xpath = "//td[@class='col col-method' and text() ='Fixed']/preceding-sibling::td/input[@type='radio']")
	WebElement shippingmethodFixedRateRadioBtn;

	@FindBy(xpath = "//span[contains(text(),'Review & Payments')]/parent::li[@class='opc-progress-bar-item _active']")
	WebElement LabelInProgress;

	@FindBy(xpath = "//div[@class='shipping-address-item selected-item']")
	WebElement shippingAddress;

	@FindBy(xpath = "//strong[@class='product-item-name']")
	List<WebElement> orderSummary_itemName;

	@FindBy(xpath = "//div[@class='block items-in-cart']")
	WebElement orderSummary_expandItemList;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	public void verifyShippingDetails(String[] newAddress) throws InterruptedException {
		verifyInProgressLabel(Constant.SHIPPING);
		verifyAddress(newAddress);

	}

	public void verifyOrderSummary(String itemName) {
		try {
			orderSummary_expandItemList.click();
		} catch (NoSuchElementException e) {
			System.out.println(Constant.ORDER_SUMMARY_ITEM_COLLAPSED);
		}
		ArrayList<String> text = new ArrayList<String>();
		for (WebElement webElement : orderSummary_itemName) {
			text.add(webElement.getText());
		}
		Assert.assertTrue(text.contains(itemName), itemName + Constant.IS_DISPLAYED);
		logger.log(Status.PASS, Constant.VERIFIED_ORDER_SUMMARY + itemName);
	}

	public void selectShippingMethodAndClickNext(String method) throws InterruptedException {
		wait.waitForElementInVisible(loader);
		wait.waitForElementVisible(shippingmethodFixedRateRadioBtn);
		if (method.equalsIgnoreCase(Constant.FIXED)) {
			shippingmethodFixedRateRadioBtn.click();
		} else {
			shippingmethodTableRateRadioBtn.click();
		}
		wait.waitForElementVisible(nextBtn);
		wait.waitForElementClickable(nextBtn);
		nextBtn.click();

	}

	public void verifyAddress(String[] newAddress) {
		Assert.assertTrue(shippingAddress.getText().contains(newAddress[1]),
				Constant.COMPANY_NAME_VERIFIED + newAddress[1]);
		logger.log(Status.PASS, Constant.COMPANY_NAME_VERIFIED + newAddress[1]);
		Assert.assertTrue(shippingAddress.getText().contains(newAddress[2]),
				Constant.STREET_NAME_VERIFIED + newAddress[2]);
		logger.log(Status.PASS, Constant.STREET_NAME_VERIFIED + newAddress[2]);
		Assert.assertTrue(shippingAddress.getText().contains(newAddress[3]), Constant.CITY_VERIFIED + newAddress[3]);
		logger.log(Status.PASS, Constant.CITY_VERIFIED + newAddress[3]);
		Assert.assertTrue(shippingAddress.getText().contains(newAddress[4]),
				Constant.TELEPHONE_NUMBER_VERIFIED + newAddress[4]);
		logger.log(Status.PASS, Constant.TELEPHONE_NUMBER_VERIFIED + newAddress[4]);
	}

	public void verifyInProgressLabel(String label) throws InterruptedException {
		wait.waitForElementInVisible(loader);
		if (label.equalsIgnoreCase(Constant.REVIEW_PAYMENTS)) {
			wait.waitForElementClickable(placeOrderBtn);
		} else {
			wait.waitForElementClickable(nextBtn);
		}
		WebElement LabelInProgress = driver.findElement(By
				.xpath("//span[contains(text(),'" + label + "')]/parent::li[@class='opc-progress-bar-item _active']"));
		wait.waitForElementVisible(LabelInProgress);
		Assert.assertTrue(LabelInProgress.isDisplayed(), label + Constant.IN_PROGRESS_LABEL_IS_DISPLAYED);
		logger.log(Status.PASS, Constant.VERIFIED_IN_PROGRESS_LABEL + label);

	}
}
