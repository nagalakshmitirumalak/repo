package listenerspack;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import extentreportspack.ExtentReporterTest;
import use.Base;

public class Listeners extends Base implements ITestListener {

	WebDriver driver;
	ExtentReports extentReport = ExtentReporterTest.getExtentReporter();
	ExtentTest extentTest;
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) {
		
		String testMethodName = result.getName();
		extentTest = extentReport.createTest(testMethodName);
		extentTestThread.set(extentTest);
		extentTestThread.get().info(testMethodName+" started executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		String testMethodName = result.getName();
		extentTestThread.get().log(Status.PASS, testMethodName+" execution success");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getName();
		
		extentTestThread.get().fail(result.getThrowable());
		
		try {
			
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e1) {
			
			e1.printStackTrace();
		}
		
		try {
			
			String screenshotpath = takeScreenshot(testName,driver);
			extentTestThread.get().addScreenCaptureFromPath(screenshotpath,testName);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
	}

	
	
	
	
}
