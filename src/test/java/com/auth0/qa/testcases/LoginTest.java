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
import com.auth0.qa.pages.LoginPage;
import com.auth0.qa.pages.PasswordPage;
import com.auth0.qa.utlis.Utilities;

public class LoginTest extends Base{
@Parameters("BrowserName")
	@BeforeMethod
	public void setup(@Optional("") String browserName) {
		initializeBrowserAndOpenHomepage(browserName);
	}
	
	@AfterMethod
	public void tearDown(Method method) {
		close(method);
	}
	@Test(priority = 1,dependsOnMethods = "verifyNewUserSignup")
    public void verifyNewUserLogin() {
        //Home page
        HomePage homePage = new HomePage(page);
        homePage.assertPageLoad();
        homePage.clickOnLogin();

		//Login page
		LoginPage loginPage = new LoginPage(page);
		loginPage.assertPageLoad();
		loginPage.enterEmailId(Utilities.emailgenerator.getEmail());
		loginPage.clickOnContinue();

		//Password page
		PasswordPage passwordPage = new PasswordPage(page);
		passwordPage.assertLoginPasswordPageLoad();
		passwordPage.enterPassword(defaultPassword());
		passwordPage.clickOnContinue();

		//Dash-board Page
		DashboardPage dashboardPage = new DashboardPage(page);
		dashboardPage.assertGettingStartedHeaderIsVisible(timeout());
    }
	@Test(priority = 2)
	public void verifyInvalidUserUserLoginAttempt(){
		//Home page
        HomePage homePage = new HomePage(page);
        homePage.assertPageLoad();
        homePage.clickOnLogin();

		//Login page
		LoginPage loginPage = new LoginPage(page);
		loginPage.assertPageLoad();
		loginPage.enterEmailId(Utilities.emailgenerator.generateEmail());
		loginPage.clickOnContinue();

		//Password page
		PasswordPage passwordPage = new PasswordPage(page);
		passwordPage.assertLoginPasswordPageLoad();
		passwordPage.enterPassword(defaultPassword());
		passwordPage.clickOnContinue();
		passwordPage.assertInvalidUserUserErrorMessage();
	}
}
