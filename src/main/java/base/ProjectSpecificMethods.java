package base;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;


public class ProjectSpecificMethods {
	
	public  ChromeDriver driver;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public ExtentTest test, node;
	public String testName, testDescription, testAuthor, testCategory;
	
	@BeforeSuite
	public void startReport() {
				reporter = new ExtentHtmlReporter("./ExtentReports/result.html");
				reporter.setAppendExisting(true);
				extent = new ExtentReports();
				extent.attachReporter(reporter);
	}
	
	//Function to take Screenshot
	public long takeSnap() throws IOException {
		long ranNum = (long) (Math.random() * 9999999999L);	
		File source = driver.getScreenshotAs(OutputType.FILE);
		File target = new File("./snaps/img"+ranNum+".png");
		FileUtils.copyFile(source, target);	
		return ranNum;
	}
		
	@BeforeClass
	public void testDetails() {
		test = extent.createTest(testName, testDescription);
		test.assignAuthor(testAuthor);
		test.assignCategory(testCategory);
	}
	
	//Function to append Screenshots with Report step
	public void reportStep(String msg, String status) throws IOException {
		if(status.equalsIgnoreCase("pass")) {
			node.pass(msg, MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/img"+takeSnap()+".png").build());
		}
		else {
			node.fail(msg, MediaEntityBuilder.createScreenCaptureFromPath(".././snaps/img"+takeSnap()+".png").build());
			throw new RuntimeException();
		}
	}
	
	//Driver Initialization		
	@BeforeMethod
	public void startApp() {
		node = test.createNode(testName);		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("chrome://settings/clearBrowserData");
		driver.findElementByXPath("//settings-ui").sendKeys(Keys.ENTER);
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver.manage().window().maximize();
		driver.get("https://airbnb.co.in/");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}
	
	
	@AfterSuite
	public void endReport() {
		extent.flush();
	}
	
	
	

}
