package com.auth0.qa.testcases;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.auth0.qa.base.Base;
import com.auth0.qa.pages.DashboardPage;
import com.auth0.qa.pages.HomePage;
import com.auth0.qa.pages.PasswordPage;
import com.auth0.qa.pages.ProfilePage;
import com.auth0.qa.pages.SignUpPage;
import com.auth0.qa.utlis.Utilities;

public class SignupTest extends Base {
@Parameters("BrowserName")
	@BeforeMethod
	public void setup(@Optional("") String browserName) {
		initializeBrowserAndOpenHomepage(browserName);
	}
	
	@AfterMethod
	public void tearDown(Method method) {
		close(method);
	}
	
	@Test
	public void verifyNewUserSignup() {
		// Home page
			HomePage homePage = new HomePage(page);
			homePage.assertPageLoad();
			homePage.clickOnSignup();
			
		// Sign-up Page	
			SignUpPage signUpPage = new SignUpPage(page);
			signUpPage.assertPageLoad();
			signUpPage.enterEmailId(Utilities.emailgenerator.generateEmail());
			signUpPage.clickOnContinue();
			
		// Password Page	
			PasswordPage passwordPage = new PasswordPage(page);
			passwordPage.assertPageLoad();
			passwordPage.enterPassword(defaultPassword());
			passwordPage.clickOnContinue();
			
		//Profile Page	
			ProfilePage profilePage = new ProfilePage(page);
			profilePage.assertPageLoad();
			profilePage.clickOnNotCodingLabel();
			profilePage.clickOnNextButton();
			
		//Dash-board Page	
			DashboardPage dashboardPage = new DashboardPage(page);
			dashboardPage.assertGettingStartedHeaderIsVisible(timeout());;
	}
}
