import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;


public class ConfigManager {
    private static Properties configProperties  = new Properties(); //create new properties object
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
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("No config file present!");
                return;
            }
            configProperties.load(input);
        } catch (IOException ex) {
            System.err.println("Failed to load properties: " + ex.getMessage());
        }
    }

    //two getters that access configProperties and parse them into their respective data types
    public static int getIntProperty(String key) {
        return Integer.parseInt(configProperties.getProperty(key));
    }
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(configProperties.getProperty(key));
    }
}
