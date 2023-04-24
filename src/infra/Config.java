package infra;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Config {
    private static Config config = null;

    private Properties properties;
        private Config()
        {
            try {
                properties = new Properties();
                FileInputStream in = new FileInputStream("config.properties");
                properties.load(in);
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static Config getInstance()
        {
            if (config == null)
                config = new Config();
            return config;
        }

    public String getValueOfProperty(Prop prop){
        return properties.getProperty(prop.name());
    }
    public void setValueOfProperty(Prop Key,String value){
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("config.properties");
            properties.setProperty(Key.name(), value);
            properties.store(out, null);
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
