package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemDetailsPage extends BasePage {

	
	WebDriver driver;

	@FindBy(xpath = "//a[@class='action towishlist']")
	WebElement AddToWishListlink;

	@FindBy(id = "product-addtocart-button")
	WebElement AddToCartButton;

	@FindBy(id = "qty")
	WebElement QuantityInputField;

	@FindBy(xpath = "//h1/span")
	WebElement itemFullName;
	
	@FindBy(xpath = "(//span[text()='Add to Compare'])[1]")
	WebElement AddToCompareButton;
	
	
	
	
	public ItemDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public void addItemToCart(String item, String cartQty) throws InterruptedException {
		// TODO Auto-generated method stub
		SearchResultPage search = new SearchResultPage(driver);
		search.verifySearch(item);
		search.searchResultList_ItemName.get(0).click();
		QuantityInputField.clear();
		QuantityInputField.sendKeys(cartQty);
		wait.waitForElementClickable(AddToCartButton);
		AddToCartButton.click();

	}


	public String getItemName() {
		// TODO Auto-generated method stub
		return itemFullName.getText();
	}

	public void clickAddCompareButton()
	{wait.waitForElementClickable(AddToCompareButton);
		AddToCompareButton.click();
	}
	
	
}
