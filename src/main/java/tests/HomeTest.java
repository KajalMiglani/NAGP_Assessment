package tests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.SearchResultPage;
import utility.Constant;
import utility.PropertyFileReader;

public class HomeTest extends BaseTest{
	
	HomePage home ;
	SearchResultPage searchScreen;

	@BeforeMethod
	public void setup() {
		 home = new HomePage(driver);
		 searchScreen = new SearchResultPage(driver);  
	}
	
	/*
	 * Verify Search Functionality 
	 * Positive Search - List of Items matching Searched Item is returned
	 * Negative Search - a. Searched Item is not present in the inventory - Proper error message is displayed
	 * 					 b.	Less than three characters are passed in Searchbox - Proper error message is displayed 
	 */
	@Test
	public void verifySearchFunctionalityOnHomeScreen() throws IOException {
		
		String itemAvailableInStore = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.SEARCH_POSITIVE);
		String itemUnavailableInStore = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.SEARCH_NEGATIVE);
		String invalidSearchItem = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.SEARCH_INVALID);		
		home.searchItem(itemAvailableInStore);
		searchScreen.verifySearch(itemAvailableInStore);
		home.searchItem(itemUnavailableInStore);
		searchScreen.verifySearch(itemUnavailableInStore);
		home.searchItem(invalidSearchItem);
		searchScreen.verifySearch(invalidSearchItem);
		
	
	}
}
