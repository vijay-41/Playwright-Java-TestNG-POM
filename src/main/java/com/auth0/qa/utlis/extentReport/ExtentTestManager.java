package com.auth0.qa.utlis.extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ExtentReports extentReports;

    public static synchronized ExtentReports createInstance(String browserName) {
        extentReports = ExtentManager.getInstance(browserName);
        return extentReports;
    }

    public static synchronized ExtentTest startTest(String testName, String browserName) {
        ExtentTest test = extentReports.createTest(testName + " [" + browserName + "]");
        extentTest.set(test);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return extentTest.get();
    }
}
