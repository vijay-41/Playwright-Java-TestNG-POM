package com.auth0.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class PasswordPage {
public String passwordPageTitle = "Create a password to sign up | Auth0";
	private Page page;
	
	//Define Locator
	private Locator passwordFieldLocator;
	private Locator continueButtonLocator;
	
	//Assign Element
	public PasswordPage(Page page) {
		this.page = page;
		passwordFieldLocator = page.locator("#password");
		continueButtonLocator = page.locator("xpath=//button[text()='Continue']");
	}
	
	//Action
	
	public void enterPassword(String password) {
		passwordFieldLocator.fill(password);
	}
	
	public void clickOnContinue() {
		continueButtonLocator.click();
	}
	
	public void assertPageLoad() {
		PlaywrightAssertions.assertThat(page).hasTitle(passwordPageTitle);
	}
}
