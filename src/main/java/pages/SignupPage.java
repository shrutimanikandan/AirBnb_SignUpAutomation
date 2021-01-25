package pages;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentTest;

import base.ProjectSpecificMethods;

public class SignupPage extends ProjectSpecificMethods {
	
	public SignupPage(ChromeDriver driver,ExtentTest node) {
		this.driver = driver;
		this.node = node;
	}

	//Click SignUp Button on Homepage
	public SignupForm clickSignup() throws IOException{
		try {
			driver.findElementByXPath("//button[@id='field-guide-toggle']").click();
			System.out.print(driver.findElementByXPath("//div[@id='simple-header-profile-menu']").getText());
			driver.findElementByXPath("//div[@id='simple-header-profile-menu']/div/a[1]/div[text()='Sign up']").click();
			reportStep("Sign up Button clicked", "pass");
		} catch (Exception e) {
			reportStep("Sign up Button not clicked", "fail");
		}
		return new SignupForm(driver,node);
	}
	
	

}
