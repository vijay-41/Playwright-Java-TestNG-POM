package com.auth0.qa.utlis;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Utilities {
    //src-main-java-com-auth0-qa folder path
    public static final String folderPath = System.getProperty("user.dir")+File.separator+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+File.separator+"auth0"+File.separator+"qa";

    //config file path 
    public static final String configFilePath = folderPath+File.separator+"config"+File.separator+"config.properties";

    //tracingFiles file path
    public static final String tracingFolderPath = System.getProperty("user.dir")+File.separator+"tracingFiles";

    //reports file path
    public static final String reportsPath = System.getProperty("user.dir")+File.separator+"reports";

    //Cleaning Tracing Folder
    public static void cleanAndCreateTracingFolder(){
        Path tracePath = Paths.get(tracingFolderPath);
        try {
            if (Files.exists(tracePath)) {
                Files.walk(tracePath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
            }
            if (!Files.exists(tracePath)) {
		            Files.createDirectories(tracePath);
		        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Cleaning Reports Folder
    public static void cleanAndCreateReportsFolder(){
        Path reporPath = Paths.get(reportsPath);
        try {
            if (Files.exists(reporPath)) {
                Files.walk(reporPath).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);   
            }
            if (!Files.exists(reporPath)) {
                Files.createDirectories(reporPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Email Generator
    public class emailgenerator{

        private static final ThreadLocal<String> threadLocalEmail = ThreadLocal.withInitial(() -> {
            String timeStamp = new SimpleDateFormat("HH_mm_ss_SSS_ddMMyyyy").format(new Date());
            String threadName = Thread.currentThread().getName().replaceAll("\\s+", "_");
            return "prodtestuser_" + timeStamp + "_" + threadName + "@mailinator.com";
        });

        private static String generatedEmail;

         // Returns the same email for the entire test suite (until JVM ends)
        public static String getEmail(){

            return threadLocalEmail.get();
            /* 
            if (generatedEmail == null){
                generatedEmail = generateEmail();// first time, generate
            }
            return generatedEmail;
            */
        }

        public static String generateEmail(){
                String timeStamp = new SimpleDateFormat("HH_mm_ss_dd-MM-yyyy").format(new Date());
                generatedEmail = "prodtestuser_"+timeStamp+"@mailinator.com";
            return generatedEmail;
        }
    }
}