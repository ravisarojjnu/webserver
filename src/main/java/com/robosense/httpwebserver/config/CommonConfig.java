package com.robosense.httpwebserver.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class CommonConfig {
	  public final static Properties properties = new Properties();
	  public static void read() {
		
		ConfigurationReader.read(properties);
	}
	
}

class ConfigurationReader {
    public static void  read(Properties properties) {
       
        InputStream inputStream = null;

        try {
            // Get the resource file as an input stream
            ClassLoader classLoader = ConfigurationReader.class.getClassLoader();
            inputStream = classLoader.getResourceAsStream("config.properties");

            if (inputStream != null) {
                // Load the properties from the input stream
                properties.load(inputStream);

            } else {
                System.err.println("Unable to locate the configuration file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }
}