package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import utility.Constant;
public class AddToCartPage extends BasePage {

	
	WebDriver driver;


	@FindBy(xpath = "//strong[@class ='product-item-name']/a")
	List<WebElement> cartItemList;
	
	@FindBy(xpath = "//a[@class='action showcart']")
	WebElement addCartLink;
	
	@FindBy(xpath = "//a[@class='action viewcart']")
	WebElement viewEditCart;
	
	@FindBy(xpath = "//h1/span")
	WebElement itemFullName;
	
	@FindBy(xpath = "//button[@class='action primary checkout' and @data-role='proceed-to-checkout']")
	WebElement ProceedcheckOutBtn;
	

	

	public AddToCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	public boolean verifyItemDisplayedInCart(String itemName) {
		// TODO Auto-generated method stub
		int num = 0;
		for (WebElement webElement : cartItemList) {
			if (webElement.getText().equals(itemName)) {
				num++;
				break;
			}
		}
		if (num > 0)
			return true;
		else
			return false;

	}



	public void verifyItemAddedIncart(String itemName) throws InterruptedException {
		
		// TODO Auto-generated method stub
		String itemFName = itemFullName.getText();
		verifyAlertDisplayed(Constant.YOU_ADDED+itemFName+Constant.TO_YOUR);
		navigateTocart();
		Assert.assertTrue(verifyItemDisplayedInCart(itemFName), itemFName+Constant.DISPLAYED_IN_CART);
		logger.log(Status.PASS,itemFName+Constant.DISPLAYED_IN_CART);
		
		
	}



	private void navigateTocart() {
		// TODO Auto-generated method stub
		addCartLink.click();
		viewEditCart.click();
	}


	public void editItemQtyInCart(String itemAvailableInStore, String qtyNum) {
		// TODO Auto-generated method stub
		WebElement qty = driver.findElement(By.xpath("//a[contains(@title,'"+itemAvailableInStore+"')]/../following-sibling::td[2]/div//label/input"));
		qty.clear();
		qty.sendKeys(qtyNum);
	}



	public void verifyEditedQty(String itemAvailableInStore, String qtyNum) {
		// TODO Auto-generated method stub
		WebElement qty = driver.findElement(By.xpath("//a[contains(@title,'"+itemAvailableInStore+"')]/../following-sibling::td[2]/div//label/input"));
		String itemQty = qty.getAttribute(Constant.VALUE);
		Assert.assertTrue(itemQty.equals(qtyNum), Constant.ITEM_QUANTITY_EDITED);
	}
	


	
}
