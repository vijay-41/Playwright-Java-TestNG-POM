package com.auth0.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class DashboardPage {
    
    //Define Locator
	private Locator headingLocator;
	
	//Assign Element
	public DashboardPage(Page page) {
		headingLocator = page.locator("xpath=//h3[text()='Create a Sample App']");
		
	}
	
	//Action
	public Locator checkHeading() {
		return headingLocator;
	}
	
	public void assertDashboardHeaderIsVisible(int timeout) {
		PlaywrightAssertions.assertThat(headingLocator).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(timeout));
	}
}
