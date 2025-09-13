package com.auth0.qa.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListeners implements ITestListener{
        String testName;
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of Auth0 Automation has started");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		this.testName = result.getName();
		System.out.println("Execution for "+testName+" test is started");
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("Execution of "+testName+" test is eneded with Success");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		System.out.println("Execution of "+testName+" test is ended in Failure");
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
		System.out.println("Execution of "+testName+" test is skipped");
	}
	
	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println("Execution of Auth0 Automation has Ended");
	}
}
