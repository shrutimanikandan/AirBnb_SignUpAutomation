package pages;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import base.ProjectSpecificMethods;

public class SignupForm extends ProjectSpecificMethods{
	
	
	public SignupForm(ChromeDriver driver, ExtentTest node) {
		this.driver = driver;
		this.node = node;
	}
	
	//Function to Click Continue With Email Button
	public SignupForm clickContinuewithEmail() throws IOException {
		try {
		WebDriverWait wait = new WebDriverWait(driver,50);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button/div/div[2][text()='Continue with email']"))).click();
		reportStep("Continue with email clicked successsfully", "pass");
		}
		catch (Exception e) {
			reportStep("Unable to click Continue with email", "fail");
		}
		return this;
	}
	
	//Function to Navigate to FinishSignup Form
	public SignupForm enterEmail(String email) throws IOException {
		try {
		    boolean val = driver.findElementByXPath("//input[@id='email-login-email']").isEnabled();
		    if(val==true) {
				driver.findElementByXPath("//input[@id='email-login-email']").click();
				driver.findElementByXPath("//input[@id='email-login-email']").sendKeys(email);
				driver.findElementByXPath("//button[@type='submit']").click();
				driver.findElementByXPath("//input[@id='email-signup-email']").clear();
				}
		    else {
		    	driver.findElementByXPath("//input[@id='email-signup-email']").clear();
		}
		}catch(Exception e) {
			reportStep("Unable to send email ", "fail");
		}
			return this;
	}
	
	//Function to Navigate to FinishSignup Form
	public SignupForm FinishSignup(String email) throws IOException{
		try {
			WebDriverWait wait = new WebDriverWait(driver,50);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1/div[text()='Finish signing up']")));
			reportStep("Finish SignUp page landed Successfully","pass");
			driver.findElementByXPath("//input[@id='email-signup-email']").sendKeys(email);
			driver.findElementByXPath("//button[@type='submit']/span").click();
			reportStep("Agree and Continue Button is clicked","pass");	
		}
		catch(Exception e) {
			reportStep("Unable to click Agree and continue Button", "fail");
		}
		return this;
	}
	
	//Function to Validate errors when only Email is entered as input.
	public SignupForm emailValidation() throws IOException {
		try {
			String firstNameAssertion = driver.findElementByXPath("//div[text()='First name is required.']").getText();
			String dobAssertion = driver.findElementByXPath("//div[@id='error-text'][text()='Select your date of birth to continue.']").getText();
            if(firstNameAssertion.equalsIgnoreCase("First name is required.")) {
            	reportStep("FirstName Error validation is successful", "pass");
            }
            if(dobAssertion.equalsIgnoreCase("Select your date of birth to continue.")) {
            	reportStep("Date of Birth Error Validation is successful", "pass");
            }    
		}
		catch (Exception e) {
			reportStep("Error Validation is not done", "fail");
		}
		return this;
	}
	
	//Function to Validate error when password is missing,with all other required data filled.
	public SignupForm passwordValidation(String firstName,String lastName,String date,String email) throws IOException {
		try {
			driver.findElementByXPath("(//input[contains(@id,'email-signup-user')])[1]").sendKeys(firstName);
			driver.findElementByXPath("(//input[contains(@id,'email-signup-user')])[2]").sendKeys(lastName);
			driver.findElementByXPath("//input[@id='date']").sendKeys(date);
			//driver.findElementByXPath("//input[@id='email-signup-email']").sendKeys(email);
			reportStep("FirstName,LastName,DOB & email entered successfully", "pass");
			driver.findElementByXPath("//button[@type='submit']/span").click();
			Boolean pwd = driver.findElementByXPath("//div[5]/div[2]['password-rules-indicator'])[2]").isDisplayed();
	        if(pwd == true) {
	        	reportStep("'Password is required' error messages are displayed,", "pass");
	        }
		}
		catch (Exception e) {
			reportStep("Password Validation Failure", "fail");
		}
		return this;
	}
	
	//Function to Enter Valid data and successfull Login
	public SignupForm entervalidData(String firstName,String lastName,String date,String email,String pwd) throws IOException {
		try {
			driver.findElementByXPath("(//input[contains(@id,'email-signup-user')])[1]").sendKeys(firstName);
			driver.findElementByXPath("(//input[contains(@id,'email-signup-user')])[2]").sendKeys(lastName);
			driver.findElementByXPath("//input[@id='date']").sendKeys(date);
			driver.findElementByXPath("//input[@id='email-signup-email']").clear();
			driver.findElementByXPath("//input[@id='email-signup-email']").sendKeys(email);
			driver.findElementByXPath("//*[@id='email-signup-password']").sendKeys(pwd);
			reportStep("Login Successfull", "pass");
			
		}
		catch (Exception e) {
			reportStep("Password Validation Failure", "fail");
		}
		return this;
	}
	
}
