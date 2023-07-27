package Base;

import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import ExtentReports.ExtentManager;
import utility.Application_Keyword;

public class Base_Class  extends Application_Keyword{
	
	public Application_Keyword application;
	public ExtentReports report;
	public ExtentTest test;
	
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext context ) throws Exception {
		 report = ExtentManager.generateReports();
		 test = report.createTest(context.getCurrentXmlTest().getName());
		 test.log(Status.INFO, "Starting the  "+context.getCurrentXmlTest().getName());
	
		 	
		 context.setAttribute("rep", report);
		 context.setAttribute("tests", test);
		
		 application = new Application_Keyword();
		 application.setReport(test);
		 context.setAttribute("app", application);
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {
		test = (ExtentTest) context.getAttribute("tests");
		
		application = (Application_Keyword) context.getAttribute("app");	
		report = (ExtentReports) context.getAttribute("rep");
		

	}
	
	@AfterTest
	public void afterTest(ITestContext context) {
		application = (Application_Keyword) context.getAttribute("app");	
		if(application!=null)
		application.quit();
		
		report=(ExtentReports) context.getAttribute("rep");
		if(report!=null)
			report.flush();
	} 
	
	
	@AfterSuite
	public void afterSuite(ITestContext context) {
		
	}
	

}
