package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemDetailsPage extends BasePage {

	
	

	@FindBy(xpath = "//a[@class='action towishlist']")
	WebElement addToWishListlink;

	@FindBy(id = "product-addtocart-button")
	WebElement addToCartButton;

	@FindBy(id = "qty")
	WebElement quantityInputField;

	@FindBy(xpath = "//h1/span")
	WebElement itemFullName;
	
	@FindBy(xpath = "(//span[text()='Add to Compare'])[1]")
	WebElement addToCompareButton;
	
	
	
	
	public ItemDetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public void addItemToCart(String item, String cartQty) throws InterruptedException {
		SearchResultPage search = new SearchResultPage(driver);
		search.verifySearch(item);
		search.searchResultList_ItemName.get(0).click();
		quantityInputField.clear();
		quantityInputField.sendKeys(cartQty);
		wait.waitForElementClickable(addToCartButton);
		addToCartButton.click();

	}


	public String getItemName() {
		return itemFullName.getText();
	}

	public void clickAddCompareButton()
	{wait.waitForElementClickable(addToCompareButton);
		addToCompareButton.click();
	}
	
	
}
