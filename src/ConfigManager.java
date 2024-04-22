import java.util.Properties;
import java.io.*;


public class ConfigManager {
    private static final String CONFIG_FILE_PATH = "C:\\Users\\Administrator\\Documents\\Code\\Projekt_Parkhaus\\Parkhaus\\src\\config.properties";
    private static volatile ConfigManager instance;
    private Properties configProperties; //create new properties object

    private ConfigManager() {
        configProperties = new Properties();
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

    private void loadProperties() { //method loading properties from file config.properties
        try (InputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
            configProperties.load(input);
        } catch (IOException ex) {
            System.err.println("Failed to load configuration file: " + ex.getMessage());
        }
    }

    //two getters that access configProperties and parse them into their respective data types
    public int getIntProperty(String key) {
            return Integer.parseInt(configProperties.getProperty(key));
    }

    public boolean getBooleanProperty(String key) {

        return Boolean.parseBoolean(configProperties.getProperty(key));

    }

    //method to set properties
    public void setProperty(String key, String value) {
        synchronized (this) { //synchronize object to cover setting and saving
            configProperties.setProperty(key, value);
            saveProperties();
        }
    }

    private void saveProperties() {

        try (OutputStream output = new FileOutputStream(CONFIG_FILE_PATH)) {
            configProperties.store(output, "Updated configuration");
        } catch (IOException ex) {
            System.err.println("Failed to save configuration: " + ex.getMessage());
        }
    }
}

