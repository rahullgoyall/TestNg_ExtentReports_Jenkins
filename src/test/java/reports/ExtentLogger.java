package reports;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.MediaEntityBuilder;

import utility.LocalDriverManager;

public final class ExtentLogger {
	
	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
		
	}
	
	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
			
		}
	
	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
		
	}
	
	public static void fail(String message,Boolean screenShotEnabled) {
		if(screenShotEnabled)
		ExtentManager.getExtentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Image()).build());
		
	}
	

	private static String getBase64Image() {
		
		return ((TakesScreenshot)LocalDriverManager.getWebDriver()).getScreenshotAs(OutputType.BASE64);
	}

}
