package tests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.AddToCartPage;
import Pages.HomePage;
import Pages.ItemDetailsPage;
import Pages.LoginPage;
import utility.Constant;
import utility.PropertyFileReader;

public class AddToCartTest extends BaseTest{
	
	LoginPage login;
	HomePage home ;
	ItemDetailsPage itemDetail;
	AddToCartPage cart;
	
	
	
	
	@BeforeMethod
	public void setup() {
		login = new LoginPage(driver);
		 home = new HomePage(driver);
		 itemDetail = new ItemDetailsPage(driver);
		 cart = new AddToCartPage(driver);
		 
	}
	
	/*
	 * Verify Add to cart and Edit Cart Functionality
	 * Input - Login Username, password, email 
	 * 		-	Item name , Item Quantity, Item Edit Quantity
	 * 
	 * verify user is able to create cart
	 * verify qty can be edity once cart is created 
	 */
	@Test
	public void verifyUserCanAddItemToCartAndEditCart() throws IOException, InterruptedException {
		
		String username = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.DUMMY_USERNAME);
		String email = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.DUMMY_EMAIL);
		String password = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.DUMMY_PASSWORD);
		login.login(email,password);
		home.verifyLoggedInUserOnHomeScreen(username);
		String itemAvailableInStore = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.ADD_CART_ITEM);
		String itemQTY = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.CART_QTY);
		home.searchItem(itemAvailableInStore);
		itemDetail.addItemToCart(itemAvailableInStore,itemQTY );
		cart.verifyItemAddedIncart(itemAvailableInStore);
		String itemQty = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.CART_EDIT_QTY);
		cart.editItemQtyInCart(itemAvailableInStore, itemQty);
		cart.verifyEditedQty(itemAvailableInStore, itemQty);
		
	}
	
}

