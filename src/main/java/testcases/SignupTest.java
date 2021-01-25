package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethods;
import pages.SignupPage;

public class SignupTest extends ProjectSpecificMethods {
	
	@BeforeTest
	public void provideDetails() {
		testName = "SignupTest";
		testDescription = "SignupTest_AirBnb ";
		testAuthor = "ShrutiManikandan";
		testCategory = "Smoke";
	}
	
	@Test
	public void runSignup() throws InterruptedException, IOException {
		
		new SignupPage(driver,node)
		.clickSignup()
		.clickContinuewithEmail()
		.enterEmail("shruti.manikandan@yopmail.com")
		.FinishSignup("shruti.manikandan@yopmail.com")
		.emailValidation();
	}

}
