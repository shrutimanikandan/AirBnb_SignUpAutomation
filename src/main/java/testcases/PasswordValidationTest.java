package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethods;
import pages.SignupPage;


public class PasswordValidationTest extends ProjectSpecificMethods {
	
	@BeforeTest
	public void provideDetails() {
		testName = "PasswordValidationTest";
		testDescription = "PasswordValidationTest_AirBnb";
		testAuthor = "ShrutiManikandan";
		testCategory = "Smoke";
	}
	
	@Test
	public void runPwd() throws InterruptedException, IOException {
		
		new SignupPage(driver,node)
		.clickSignup()
		.clickContinuewithEmail()
		.enterEmail("shruti.manikandan@yopmail.com")
		.FinishSignup("shruti.manikandan@yopmail.com")
		.passwordValidation("shruti","manikandan","18/12/1993","shruti.manikandan@yopmail.com");
	}

}
