 package ExtentReports;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	public static ExtentReports extentReports;
	static ExtentTest extentTest;
	public static String reports;
	public static String filelocation;
	public static WebDriver driver;
	
	
	public static ExtentReports generateReports() {
		if(extentReports==null) {
			extentReports = new ExtentReports();
			Date d = new Date();
			String reportsFolder = d.toString().replaceAll(":", "-")+"//Screenshots";
			
			reports =System.getProperty("user.dir") +"\\Results\\"+reportsFolder;	//Screenshots Saving Folder
			String path = 	System.getProperty("user.dir")+"\\Results\\"+d.toString().replaceAll(":", "-");	//extentReports Saving Folder
			File file = new File(reports);
			file.mkdirs();
			ExtentSparkReporter spark = new ExtentSparkReporter(path);
			
			spark.config().setDocumentTitle("Extent Reports");
			spark.config().setReportName("Automation");
			spark.config().setTheme(Theme.STANDARD);
			extentReports.attachReporter(spark);
		}
		
		return extentReports;
		
	}
	
	
	
	public static String  generateScreenshots( String name )  {

		Date d = new Date();
		String filelocation = d.toString().replaceAll(":", "-")+"//"+name+".jpg";
		TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String path = ExtentManager.reports+"//"+filelocation;
		File des = new File(path);
		
		try {
			FileUtils.copyFile(src, des);
			extentTest.log(Status.INFO, "Screenshot--> "+extentTest.addScreenCaptureFromPath(path));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	return path;

	}



//
//@BeforeMethod
//	public void beforeTest(ITestResult result) {
//		ex = ExtentManager.generateReports(result.getMethod().getMethodName());
//		test=ex.createTest(result.getMethod().getMethodName());
//		
//	}
//	
//	@AfterMethod
//	public void afterMethod(ITestResult result) {
//		
//		if(result.getStatus()==ITestResult.SUCCESS) {
//			test.log(Status.PASS, "The success method name is"+result.getName());
//			try {
//				String attach = ExtentManager.generateScreenshots(result.getMethod().getMethodName());
//				test.addScreenCaptureFromPath(attach);
//			} catch (IOException e) {
//				
//				e.printStackTrace();
//			}
//		}else if(result.getStatus()==ITestResult.FAILURE) {
//			test.fail("My Test Case Failed");
//		}
//	}
	
}
