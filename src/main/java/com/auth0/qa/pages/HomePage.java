package com.auth0.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class HomePage {
    
    public String homepageTitle = "Auth0: Secure access for everyone. But not just anyone.";
	private Page page;
	//Define Locator
	private Locator signupButtonLocator;
	
	//Assign Element
	public HomePage(Page page) {
		this.page = page;
		signupButtonLocator = page.locator("xpath=//nav[@aria-label='Desktop nav']//span[text()='Sign up']");
	}
	
	//Action
	
	public void clickOnSignup() {
		signupButtonLocator.click();
	}
	
	public void assertPageLoad() {
		PlaywrightAssertions.assertThat(page).hasTitle(homepageTitle);
	}
}
