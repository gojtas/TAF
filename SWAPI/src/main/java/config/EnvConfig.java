package config;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.getProperty;

public class EnvConfig {

    private static final Logger LOGGER = Logger.getLogger(EnvConfig.class.getName());
    private static EnvConfig instance = null;
    private Configuration configuration;

    private static EnvConfig instance() {
        if (instance == null) {
            instance = new EnvConfig();
        }
        return instance;
    }

    private EnvConfig() {
        String configName = getProperty("configName", "");
        if (configName.isEmpty()) {
            configName = "dev";
        }
        loadProperties(configName);
    }

    private void loadProperties(String configName) {
        String pathName = MessageFormat.format("{0}config/{1}.properties", getPathToConfigsFolder(), configName);
        Configurations configurations = new Configurations();
        File file = new File(pathName);
        try {
            configuration = configurations.properties(file);
        } catch (ConfigurationException e){
            LOGGER.log(Level.SEVERE, "Problem with load configuration. ", e);
            System.exit(1);
        }
    }

    public static String getString(String key) {
        return instance().configuration.getString(key);
    }

    private String getPathToConfigsFolder() {
        return String.format("%s/src/main/java/", getProperty("user.dir"));
    }
}

