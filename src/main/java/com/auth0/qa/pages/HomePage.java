package com.auth0.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class HomePage {
    
    public String homepageTitle = "Secure AI Agent & User Authentication | Auth0";
	private Page page;
	//Define Locator
	private Locator signupButtonLocator;
	private Locator loginButtonLocator;
	
	//Assign Element
	public HomePage(Page page) {
		this.page = page;
		signupButtonLocator = page.locator("xpath=//nav[@aria-label='Desktop nav']//span[text()='Sign up']");
		loginButtonLocator = page.locator("xpath=//a[text()='Login']");
	}
	
	//Action
	
	public void clickOnSignup() {
		signupButtonLocator.click();
	}
	
	public void assertPageLoad() {
		PlaywrightAssertions.assertThat(page).hasTitle(homepageTitle);
	}

	public void clickOnLogin() {
		loginButtonLocator.click();
	}
}
