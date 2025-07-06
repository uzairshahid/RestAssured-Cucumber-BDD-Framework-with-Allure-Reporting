package core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigUtil {
    public static Properties getConfig(String configName) {
        Properties propertyFile = new Properties();

        try {
            File config = new File(
                    System.getProperty("user.dir") +
                            File.separator +
                            "Reports" +
                            File.separator +
                            configName +
                            ".properties"
            );
            if (config.exists()) {
                propertyFile.load(new FileInputStream(config));
            } else {
                System.out.println("File NOT! found: " + config.getAbsolutePath());
            }
        } catch (Exception ex) {}
        return propertyFile;
    }
}
