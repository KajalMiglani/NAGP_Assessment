package tests;


import java.io.IOException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.LoginPage;
import utility.PropertyFileReader;
import utility.Constant;

public class LoginTest extends BaseTest{
	
	LoginPage login;
	HomePage home ;
	
	
	
	@BeforeMethod
	public void setup() {
		login = new LoginPage(driver);
		 home = new HomePage(driver);
	}
	
/*
 * Verify Login for an existing account 
 * Input - Username, Email, Password
 */
	@Test
	public void verifyLoginForExistingUser() throws IOException, InterruptedException {
		
		String username = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.DUMMY_USERNAME);
		String email = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.DUMMY_EMAIL);
		String password = PropertyFileReader.readProperty(testDataPath).getProperty(Constant.DUMMY_PASSWORD);
		login.login(email,password);	
		home.verifyLoggedInUserOnHomeScreen(username);
	}
	

}

