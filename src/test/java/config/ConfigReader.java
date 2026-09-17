package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigReader {

    public static final String configFile = "config.properties";
    public static final Properties properties = new Properties();
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    // static initialization block, Java will run this automatically, when
    // initialising Config Reader
    static {
        // Java try with resource
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(configFile)) {
            if (input == null) {
                throw new RuntimeException(configFile + " not found");
            }

            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load " + configFile);

        }
    }

    public static String get(String key) {

        String systemProperty = System.getProperty(key);
        if (systemProperty != null && !systemProperty.isBlank()) {
            return systemProperty;
        }

        String envKey = key.replace(".", "_").toUpperCase();
        String envValue = dotenv.get(envKey);

        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        return properties.getProperty(key);

        // system properties: mvn test -Dbrowser=firefox
        // return System.getProperty(key, properties.getProperty(key));
    }

    public static String baseUrl() {
        return get("base.url");
    }

    public static String browser() {
        return get("browser");
    }

    public static long explicitWait() {
        return Long.parseLong(get("explicit.wait.seconds"));
    }

    public static String standardUsername() {
        return get("standard.username");
    }

    public static String standardPassword() {
        return get("standard.password");
    }

}
