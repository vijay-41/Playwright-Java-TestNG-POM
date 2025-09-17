package com.auth0.qa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class DashboardPage {
    
    //Define Locator
	private Locator gettingStartedLocator;
	
	//Assign Element
	public DashboardPage(Page page) {
		gettingStartedLocator = page.getByTitle("Getting Started");
	}
	
	//Action
	public void assertGettingStartedHeaderIsVisible(int timeout) {
		PlaywrightAssertions.assertThat(gettingStartedLocator).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(timeout));
	}
}
