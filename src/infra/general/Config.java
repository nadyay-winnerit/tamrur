package infra.general;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Config {

    private static Config config = null;

    private Properties properties;

    private Config() {
        try {
            properties = new Properties();
            FileInputStream in = new FileInputStream("config.properties");
            properties.load(new InputStreamReader(in, StandardCharsets.UTF_8));
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Config getInstance() {
        if (config == null)
            config = new Config();
        return config;
    }

    public String getValueOfProperty(Prop prop) {
        return properties.getProperty(prop.name());
    }

    public void setValueOfProperty(Prop key, String value) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream("config.properties");
            properties.setProperty(key.name(), value);
            properties.store(out, null);
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // MyEnum myVariable = MyEnum.valueOf(getValueOfProperty(property));
    public <T extends Enum> T getValueAsEnum(Prop property, Class<T> enumClass) {
        for (T enm : enumClass.getEnumConstants()) {
            if (enm.name().equals(getValueOfProperty(property))) {
                return enm;
            }
        }
        return null;
    }

}
