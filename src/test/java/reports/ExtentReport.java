package reports;

import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public final class ExtentReport {
	
	private static ExtentReports extend;
	
	public static void initReport() {
		if(Objects.isNull(extend)) {
			extend = new ExtentReports();
			ExtentSparkReporter spark =  new ExtentSparkReporter(System.getProperty("user.dir")+"/extent-test-output/index.html");
			extend.attachReporter(spark);
			spark.config().setReportName("Automation");
			spark.config().setTheme(Theme.STANDARD);
		
		}
		
	}
	
	
	public static void flushReports() {
		if(Objects.nonNull(extend)) {
	     extend.flush();	
		}
	}
	
	public static void createTest(String message) {
	     ExtentManager.setExtentTest(extend.createTest(message));
	}


	
}
