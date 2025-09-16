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

    //Email Generator
    public class emailgenerator{

        private static String generatedEmail;

        public static String generateEmail(){
            if (generatedEmail == null) {
                String timeStamp = new SimpleDateFormat("HH_mm_ss_dd-MM-yyyy").format(new Date());
                generatedEmail = "prodtestuser_"+timeStamp+"@mailinator.com";
            }
            return generatedEmail;
        }
    }
}