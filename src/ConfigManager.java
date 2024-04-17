import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;


public class ConfigManager {
    private static Properties configProperties; //create new properties object

    public ConfigManager() {
        loadProperties(); //call method  to load properties
    }

    private void loadProperties() { //method loading properties from file config.propeties
        configProperties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("No config file present!");
                return;
            }
            configProperties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace(); //what does this do?
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
