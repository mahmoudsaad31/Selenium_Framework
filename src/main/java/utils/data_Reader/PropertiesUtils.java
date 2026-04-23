package utils.data_Reader;

import org.apache.commons.io.FileUtils;
import utils.report.LogsUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

public class PropertiesUtils {

    public final static String PROPERTIES_PATH = "src/main/resources/";

    private PropertiesUtils() {
        super();
    }

    // method to load all . properties files to system
    public static Properties loadProperties() {
        try {
            Properties properties = new Properties();
            Collection<File> propertiesFilesList; // make a collection to save all .properties files
            propertiesFilesList = FileUtils.listFiles(new File(PROPERTIES_PATH), new String[]{"properties"}, true);
            propertiesFilesList.forEach(propertyFile -> { // for each property file load the data
                try {
                    properties.load(new FileInputStream(propertyFile));
                } catch (IOException ioe) {
                    LogsUtils.error(ioe.getMessage());
                }
                properties.putAll(System.getProperties());
                System.getProperties().putAll(properties); // pull all properties to system
            });
            LogsUtils.info("Loading Properties File Data");
            return properties;
        } catch (Exception e) {
            LogsUtils.error("Failed to Load Properties File Data because: " + e.getMessage());
            return null;
        }
    }

    // Get the value of the property
    public static String getPropertyValue(String key) {

        try {
            return System.getProperty(key); // get the value by the key
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return "";
        }
    }


    public static String getBaseUrl() {
        String env = System.getProperty("env");
        if (env == null) {
            env = "QA"; // default environment
        }
        return getPropertyValue(env + ".url");
    }

}
