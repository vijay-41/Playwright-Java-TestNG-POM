package com.auth0.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class SignUpPage {
public String signupPageTitle = "Sign Up - Auth0";
	private Page page;
	
	//Define Locator
	private Locator emailFieldLocator;
	private Locator continueButtonLocator;
	
	//Assign Element
	public SignUpPage(Page page) {
		this.page = page;
		emailFieldLocator = page.getByPlaceholder("Email");
		continueButtonLocator = page.locator("#database");
	}
	//Action
	public void enterEmailId(String emailId) {
		emailFieldLocator.fill(emailId);
	}
	
	public void clickOnContinue() {
		continueButtonLocator.click();
	}
	
	public void assertPageLoad() {
		PlaywrightAssertions.assertThat(page).hasTitle(signupPageTitle);
	}
}
