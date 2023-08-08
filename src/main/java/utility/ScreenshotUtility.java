package utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;

public class ScreenshotUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtility.class);
	 public void takeScreenshotOnFailure(ITestResult result, WebDriver driver) {
		 if(result.getStatus() == ITestResult.FAILURE)
		 {
			 String testCaseName = result.getMethod().getMethodName();
			 String errorDescription = result.getThrowable().getMessage().substring(0,50);
	        try {
	            String screenshotName = String.format("%s_%s.png", testCaseName, errorDescription.replaceAll("[^a-zA-Z0-9.-]", "_"));
	            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	            File destFile = new File("screenshots", screenshotName);
	            FileUtils.copyFile(screenshotFile,destFile);
	            
	        } catch (Exception e) {
	         
	          logger.error("Error Occured", e);
	        }
		 }
	    }
	 

}
