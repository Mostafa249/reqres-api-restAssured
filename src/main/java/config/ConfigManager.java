package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
            FileInputStream file =
                    new FileInputStream("src/test/java/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
