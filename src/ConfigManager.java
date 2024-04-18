import java.util.Properties;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ConfigManager {
    private static final String CONFIG_FILE_PATH = "config.properties";
    private static final Properties configProperties  = new Properties(); //create new properties object
    private static ConfigManager instance;

    private ConfigManager() {
        loadProperties(); //call method  to load properties
    }

    //check for instance of ConfigManager class, if not present, return instance
    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private void loadProperties() { //method loading properties from file config.propeties
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            configProperties.load(input);
        } catch (IOException ex) {
            System.err.println("Failed to load configuration file: " + ex.getMessage());
        }
    }

    //two getters that access configProperties and parse them into their respective data types
    public static int getIntProperty(String key) {
        return Integer.parseInt(configProperties.getProperty(key));
    }
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(configProperties.getProperty(key));
    }

    //method to set properties
    public static void setProperty(String key, String value) {
        try (OutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
            configProperties.store(output, "Updated configuration");
        } catch (IOException ex) {
            System.err.println("Failed to save configuration: " + ex.getMessage());
        }
    }
}
