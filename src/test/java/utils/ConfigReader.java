package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties prop;
    //этот метод считывает файл свойств
    public static Properties readProperties(String filePath) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        prop = new Properties();
        prop.load(fis);
        fis.close();
        return prop;
    }

    //этот метод возвращает значение на основе ключа
    public static String getPropertyValue(String key) {
        return prop.getProperty(key);
    }
}
