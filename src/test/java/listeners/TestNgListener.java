package listeners;

import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utility.EmailUtils;
import reports.*;
import utility.Init;
import utility.Utils;

public class TestNgListener implements ITestListener, ISuiteListener {

	public static int totalCount = 0;
	public static int totalFailed = 0;
	public static int totalPassed = 0;
	public static int totalSkipped = 0;
	public static String startDateTime;
	public static String endDateTime;

	@Override
	public void onStart(ISuite suite) {
		startDateTime = Utils.getFormattedDateTime();	
		ExtentReport.initReport();
	}

	@Override
	public void onFinish(ISuite suite) {
		totalCount = totalFailed+totalPassed+totalSkipped;
		endDateTime = Utils.getFormattedDateTime();
		ExtentReport.flushReports();

		//	EmailUtils.sendReport("goel199320@gmail.com", "bharatpur", "goel1993420@gmail.com", "Automation Report");


	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName()+result.getMethod().getDescription());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentLogger.pass("test pass"+result.getMethod().getMethodName());
		totalPassed++;

	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentLogger.fail("Test Fail "+result.getThrowable());
		ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
		ExtentLogger.fail("Screenshot",true);
		totalFailed++;

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentLogger.skip("test skip"+result.getMethod().getMethodName());
		totalSkipped++;

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}




}
