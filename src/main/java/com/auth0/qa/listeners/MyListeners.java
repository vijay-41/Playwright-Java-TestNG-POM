package com.auth0.qa.listeners;

import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.auth0.qa.utlis.Utilities;
import com.auth0.qa.utlis.extentReport.ExtentTestManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListeners implements ITestListener, IExecutionListener{
        String testName;
		ExtentReports extentReport;
		ExtentTest extentTest;
	
	 @Override
    public void onExecutionStart() {
        Utilities.cleanAndCreateTracingFolder();
		Utilities.cleanAndCreateReportsFolder();
    }

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Execution of Auth0 Automation has started");

		//
		String browserName = context.getCurrentXmlTest().getParameter("BrowserName");
    	extentReport = ExtentTestManager.createInstance(browserName);
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		this.testName = result.getName();
		System.out.println("Execution for "+testName+" test is started");

		//
		String browserName = result.getTestContext().getCurrentXmlTest().getParameter("BrowserName");
    	extentTest = ExtentTestManager.startTest(result.getName(), browserName);
    	extentTest.log(Status.INFO, result.getName() + " started on " + browserName);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("Execution of "+testName+" test is eneded with Success");

		//
		ExtentTestManager.getTest().log(Status.PASS, "This one is working");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		System.out.println("Execution of "+testName+" test is ended in Failure");

		//
		 ExtentTestManager.getTest().log(Status.FAIL, result.getThrowable());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
		System.out.println("Execution of "+testName+" test is skipped");
		ExtentTestManager.getTest().log(Status.SKIP, "This one is skipped");
		extentTest.log(Status.INFO, result.getThrowable());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println("Execution of Auth0 Automation has Ended");
		
		//
		 extentReport.flush();
	}
}
