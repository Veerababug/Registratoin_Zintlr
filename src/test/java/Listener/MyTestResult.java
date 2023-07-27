package Listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utility.Generic_Keyword;

public class MyTestResult implements ITestListener{
	

	public void onTestSuccess(ITestResult result) {
		ExtentTest test = (ExtentTest) result.getTestContext().getAttribute("tests");
		test.log(Status.PASS, "Success Test Case name is  "+result.getName());
		//test.log(Status.PASS, result.getMethod().getMethodName());
		//GenericKeyword.generateScreenshots(result.getMethod().getMethodName());

	}

	public void onTestFailure(ITestResult result) {
		ExtentTest test = (ExtentTest) result.getTestContext().getAttribute("tests");
		test.log(Status.FAIL, result.getThrowable().getMessage());
		Generic_Keyword.generateScreenshots();
	}

	public void onTestSkipped(ITestResult result) {
		ExtentTest test = (ExtentTest) result.getTestContext().getAttribute("tests");
		test.log(Status.SKIP, result.getName());
		test.log(Status.SKIP, result.getMethod().getMethodName());
		//GenericKeyword.generateScreenshots();
	}

	public void onFinish(ITestContext context) {
		
	}

}
