package utility;

import java.io.File;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ExtentReports.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Generic_Keyword {
	
	public  static WebDriver driver;
	public Properties prop;
	public Properties envprop;
	public  ExtentReports report;
	public  static ExtentTest test;
	public Select select;
	public String date;
	public Actions action;

public void launchBrowser(String browser) {
		
	if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
//			ChromeOptions cp = new ChromeOptions();
//			cp.addArguments("--incognito");
//			cp.setAcceptInsecureCerts(true);
//			DesiredCapabilities dsp = new DesiredCapabilities();
//			dsp.setCapability(ChromeOptions.CAPABILITY, cp);
//			cp.merge(dsp);
			driver = new ChromeDriver();
		}else if(browser.equals("mozilla")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}else if(browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void navigate(String url) {
		log("Navigating to the browser");
		driver.get(prop.getProperty(url));
	}
	
	public void click(String Locator) {
		log("Clicking an element");
		getElement(Locator).click();
	}
	
	public void clickEnter(String locator) {
		getElement(locator).sendKeys(Keys.ENTER);	
	}
	
	public void type(String locator,String data) {
		log("Locator  "+locator+"    "+"Data     "+data);
		getElement(locator).sendKeys(prop.getProperty(data));		
	}
	
	public void log(String message) {
		test.log(Status.INFO, message);
	}
	
	public WebElement getElement(String locator) {
		log("Getting WebElement  "+locator);
		//Check the presence
		if(!presenceOfElement(locator)) {
			log("Element not present  "+locator);
		}
		
		//Check the visibility
		if(!visibilityOfElement(locator)) {
			log("Element not visible  "+locator);
		}
		
		
		WebElement e = driver.findElement(getLocator(locator));
		
		return e;
	}
	
	public boolean visibilityOfElement(String locator) {
		WebDriverWait  wait = new  WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
		
		return true;
	}
	
	public boolean presenceOfElement(String locator) {
		WebDriverWait  wait = new  WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locator)));
		
		return true;
	}
	
	
	public By getLocator(String locator) {
		
		By by = null;
		if(locator.endsWith("_id")) {
			by = By.id(prop.getProperty(locator));
		}else if(locator.endsWith("_name")) {
			by = By.name(prop.getProperty(locator));
		}else if(locator.endsWith("_css")) {
			by = By.cssSelector(prop.getProperty(locator));
		}else if(locator.endsWith("_xpath")) {
			by = By.xpath(prop.getProperty(locator));
		}else 
			by = By.className(prop.getProperty(locator));
		return by;
	}
	
public void selectFromDropDown(String locator, String option) {
		
		select = new Select(getElement(locator));
		select.selectByValue(prop.getProperty(option));
		
	}
	
	
	public  static String  generateScreenshots()  {

		Date d = new Date();
		String filelocation = d.toString().replaceAll(":", "-").replaceAll(" ","_")+".jpg";
		TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String path = ExtentManager.reports+"//"+filelocation;
		File des = new File(path);
		
		try {
			FileUtils.copyFile(src, des);
			test.log(Status.INFO, "Screenshot--> "+test.addScreenCaptureFromPath(path));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	return path;

	}

	
	public void setReport(ExtentTest test) {
		this.test = test;
	}
	
	public  void quit() {
		driver.quit();
	}
}
