package com.auth0.qa.utlis.extentReport;

import java.io.File;

import com.auth0.qa.utlis.Utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extentReports;

    public static ExtentReports getInstance(String browserName) {
        if (extentReports == null) {
            File reportFile = new File(Utilities.reportsPath+File.separator+"ExtentReports"+File.separator+"extentReport.html");
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFile);
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setReportName("Auth0 Automation Test Report");
            sparkReporter.config().setDocumentTitle("Auth0 Automation Report");
            sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            // you can set system info once here
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
        }

        // add/update browser info for current run
        extentReports.setSystemInfo("Browser Name", browserName);
        return extentReports;
    }
}
