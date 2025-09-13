package com.auth0.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class ProfilePage {
public String profilePageTitle = "Profile - Auth0";
	private Page page;
	
	//Define Locator
	private Locator notcodingLocator;
	private Locator nextButtonLocator;
	
	//Assign Element
	public ProfilePage(Page page) {
		this.page=page;
		notcodingLocator = page.locator("xpath=//label[text()='Not coding']");
		nextButtonLocator = page.locator("xpath=//button[text()='Next']");
		
	}
	//Action
	public void clickOnNotCodingLabel() {
		notcodingLocator.click();
	}
	
	public void clickOnNextButton() {
		nextButtonLocator.click();
	}
	
	public void assertPageLoad() {
		PlaywrightAssertions.assertThat(page).hasTitle(profilePageTitle);
	}
}
