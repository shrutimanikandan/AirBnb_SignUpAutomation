package testcases;

import java.io.IOException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificMethods;
import pages.SignupPage;


public class successfulLoginTest extends ProjectSpecificMethods {
	
	@BeforeTest
	public void provideDetails() {
		testName = "succesfulLoginTest";
		testDescription = "succesfulLoginTest_AirBnb ";
		testAuthor = "ShrutiManikandan";
		testCategory = "Smoke";
	}
	
	@Test
	public void runLogin() throws InterruptedException, IOException {
		
		new SignupPage(driver,node)
		.clickSignup()
		.clickContinuewithEmail()
		.enterEmail("shruti.manikandan@yopmail.com")
		.FinishSignup("shruti.manikandan@yopmail.com")
		.entervalidData("shruti","manikandan","18/12/1993","shruti.manikandan@yopmail.com","dummy@1234");
	}

}
