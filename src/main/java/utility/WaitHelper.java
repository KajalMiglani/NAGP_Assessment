package utility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper {
	
	 private WebDriver driver;
	    private long timeout = 20000;


	   public WaitHelper(WebDriver driver) {
	        this.driver = driver;
	    }
	 
	    public void setImplicitWait(int seconds) {
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));

	    }

	    public void waitForElementVisible(WebElement ele) {
	    		WebDriverWait waitExplicit= new WebDriverWait(driver, Duration.ofSeconds(timeout));
	    		waitExplicit.until(ExpectedConditions.visibilityOf(ele));
	    	}
	    
	    public void waitForElementInVisible(WebElement ele) {
			WebDriverWait waitExplicit= new WebDriverWait(driver, Duration.ofSeconds(timeout));
			waitExplicit.until(ExpectedConditions.invisibilityOf(ele));
		}
	    
	    
	    public void waitForElementClickable(WebElement ele) {
			WebDriverWait waitExplicit= new WebDriverWait(driver, Duration.ofSeconds(20000));
			waitExplicit.until(ExpectedConditions.elementToBeClickable(ele));
		}

}
