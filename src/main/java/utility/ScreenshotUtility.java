package utility;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class ScreenshotUtility {
	
	 public void takeScreenshotOnFailure(ITestResult result, WebDriver driver) {
		 if(result.getStatus() == ITestResult.FAILURE)
		 {
			 String testCaseName = result.getMethod().getMethodName();
			 String errorDescription = result.getThrowable().getMessage().substring(0,50);
	        try {
	            String screenshotName = String.format("%s_%s.png", testCaseName, errorDescription.replaceAll("[^a-zA-Z0-9.-]", "_"));
	            System.out.println(screenshotName);
	            File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	            File destFile = new File("screenshots", screenshotName);
	            FileUtils.copyFile(screenshotFile,destFile);
	            System.out.printf("Screenshot saved: %s%n", destFile.getAbsolutePath());            
	            System.out.println("Screenshot saved: "+ screenshotName);
	        } catch (Exception e) {
	            System.err.println("Failed to save screenshot:"+ e.getMessage());
	            e.printStackTrace();
	        }
		 }
	    }
	 

}
