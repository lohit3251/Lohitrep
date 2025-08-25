package UOB;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener{
	
	@Override
	public void onTestStart(ITestResult result) {
	    // This method is invoked each time before a test starts
	    System.out.println("Test Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	    // This method is invoked when a test passes
	    System.out.println("Test Passed: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
	    // This method is invoked when a test fails
	    System.out.println("Test Failed: " + result.getName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	    // This method is invoked when a test is skipped
	    System.out.println("Test Skipped: " + result.getName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // This method is invoked if a test fails but is within the success percentage set in TestNG
	    System.out.println("Test Failed but within success percentage: " + result.getName());
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	    // This method is invoked if a test fails due to a timeout
	    System.out.println("Test Failed due to timeout: " + result.getName());
	}

	@Override
	public void onStart(ITestContext context) {
	    // This method is invoked before any test methods in the current test context are run
	    System.out.println("Test Execution Started: " + context.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
	    // This method is invoked after all the test methods in the current test context have run
	    System.out.println("Test Execution Finished: " + context.getName());
	}

	
	

	

}
