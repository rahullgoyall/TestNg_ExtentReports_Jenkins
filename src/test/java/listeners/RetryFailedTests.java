package listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {
	
	int count = 0;
	int retry = 1;
	@Override
	public boolean retry(ITestResult result) {
		boolean value = count<retry;
			count++;	
		return value;
	}

}
