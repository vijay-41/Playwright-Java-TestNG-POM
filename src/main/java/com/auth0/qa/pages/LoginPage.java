package com.auth0.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class LoginPage {
public String loginPageTitle = "Log in | Auth0";
    private Page page;

    //Define Locator
    private Locator emailFieldLocator;
	private Locator continueButtonLocator;

    //Assign Elements
    public LoginPage(Page page) {
        this.page = page;
        emailFieldLocator = page.locator("xpath=//input[@id='username']");
        continueButtonLocator = page.locator("xpath=//button[text()='Continue']");
    }
    //Action
    public void enterEmailId(String emailId) {
        emailFieldLocator.fill(emailId);
    }

    public void clickOnContinue() {
        continueButtonLocator.click();
    }

    public void assertPageLoad() {
        PlaywrightAssertions.assertThat(page).hasTitle(loginPageTitle);
    }
}
