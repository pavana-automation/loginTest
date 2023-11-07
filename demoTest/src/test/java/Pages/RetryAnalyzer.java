package Pages;

import org.testng.IRetryAnalyzer;
import test.login;
import org.testng.ITestResult;


public class RetryAnalyzer implements IRetryAnalyzer{
	
	int counter = 0;
	int retryLimit = 3;
	login logintest = new login();
	
	@Override
	public boolean retry(ITestResult result) {
		
		
		if(counter < retryLimit)
		{
			System.out.println("Retry the failed case");
			logintest.browser();
			counter++;
			return true;
		}
		return false;
	}
	
	
	
	

	}


