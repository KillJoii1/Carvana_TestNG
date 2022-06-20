package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static{
        try {
            FileInputStream fileInputStream = new FileInputStream("config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getPropertyString(String key){
        return properties.getProperty(key);
    }
    public static int getPropertyInt(String key){return Integer.parseInt(properties.getProperty(key));}
    public static long getPropertyLong(String key){return Long.parseLong(properties.getProperty(key));}
}
