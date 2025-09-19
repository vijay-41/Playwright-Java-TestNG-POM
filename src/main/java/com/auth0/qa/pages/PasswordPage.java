package com.auth0.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class PasswordPage {
public String passwordPageTitle = "Create a password to sign up | Auth0";
public String passwordPageTitleLogin = "Enter your password to log in | Auth0";
public String duplicateUserErrorMessage = "The user already exists.";
public String invalidUserErrorMessage = "Wrong email or password";
	private Page page;
	
	//Define Locator
	private Locator passwordFieldLocator;
	private Locator continueButtonLocator;
	private Locator duplicateUserErrorLocator;
	private Locator invalidUserErrorLocator;
	
	//Assign Element
	public PasswordPage(Page page) {
		this.page = page;
		passwordFieldLocator = page.locator("#password");
		continueButtonLocator = page.locator("xpath=//button[text()='Continue']");
		duplicateUserErrorLocator = page.locator("#error-element-email");
		invalidUserErrorLocator = page.locator("#error-element-password");
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

	public void assertLoginPasswordPageLoad() {
		PlaywrightAssertions.assertThat(page).hasTitle(passwordPageTitleLogin);
	}

	public void assertDuplicateUserErrorMessage(){
		PlaywrightAssertions.assertThat(duplicateUserErrorLocator).isVisible();
		PlaywrightAssertions.assertThat(duplicateUserErrorLocator).containsText(duplicateUserErrorMessage);
	}

	public void assertInvalidUserUserErrorMessage(){
		PlaywrightAssertions.assertThat(invalidUserErrorLocator).isVisible();
		PlaywrightAssertions.assertThat(invalidUserErrorLocator).containsText(invalidUserErrorMessage);
	}
}
