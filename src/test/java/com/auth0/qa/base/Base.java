package com.auth0.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import com.auth0.qa.utlis.Utilities;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class Base {

    protected Playwright pw;
        protected Browser browser;
        protected BrowserContext browserContext;
        protected Page page;
        protected Properties propConfig;
        String baseBrowser;

        public Base(){
            loadConfigPropertiesFile();
        }

        private void loadConfigPropertiesFile(){
            propConfig = new Properties();
            File propConfigFile = new File(Utilities.configFilePath);

            try {
                FileInputStream fileInputStream = new FileInputStream(propConfigFile);
                propConfig.load(fileInputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void openHomepage(Page page){
            page.navigate(propConfig.getProperty("url"));
        }

        public void initializeBrowserAndOpenHomepage(String browserName){
            if(browserName == null || browserName.isEmpty()){
                browserName = propConfig.getProperty("browser", "chrome");//Get the value of browser from the Config.properties file, If it's missing or not defined, then use "chrome" as the default.
            }
            baseBrowser = browserName;
            pw = Playwright.create();
            BrowserType browserType;

            switch (browserName.toLowerCase()) {
                case "firefox":
                    browserType = pw.firefox();
                    break;
                case "safari":
                    browserType = pw.webkit();
                    break;
                case "chrome":
                default:
                    browserType = pw.chromium();
                    break;
            }
            browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
            browserContext = browser.newContext();
            browserContext.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
            page = browserContext.newPage();

            openHomepage(page);
        }

        public void stopTracing(String methodName){
            String tracingFileName = baseBrowser+"_"+methodName+"_tracing.zip";
            Path tracingFilePath = Paths.get(Utilities.tracingFolderPath, tracingFileName);
            browserContext.tracing().stop(new Tracing.StopOptions().setPath(tracingFilePath));
        }

        public void close(Method method){
            String methodName = method.getName();
            stopTracing(methodName);
            page.close();
            browserContext.close();
            browser.close();
            pw.close();
        }

        public int timeout(){ // used in Signup
            return Integer.parseInt(propConfig.getProperty("timeout"));
        }

        public String defaultPassword(){ // used in Signup
            String password = propConfig.getProperty("defaultPassword");
            return password;
        }

}
