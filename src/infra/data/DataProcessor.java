package infra.data;

import objects.BaseData;
import objects.pages.LoginPageData;
import objects.pages.RegisterUserPageData;
import objects.tests.TestNewRegisterUserData;
import org.jooq.tools.csv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

public class DataProcessor {

    public static List<BaseData> readCSV(Class<? extends BaseData> clazz) {

        List<BaseData> result = new ArrayList<>();
        try {
            String classPath = clazz.getName().replace("objects.", "").replace(".", "\\");
            System.out.println("resources\\csv\\" + classPath + ".csv");
            Reader reader = new FileReader("resources\\csv\\" + classPath + ".csv");
            System.out.println("resources\\csv\\" + classPath + ".csv");
            CSVReader csvReader = new CSVReader(reader, '|');
            List<String[]> rows = csvReader.readAll();
            List<String> NamesOfMethods = new ArrayList<>();
            String row = null;
            for (int k = 0; k < rows.get(0).length; k++) {
                NamesOfMethods.add("set" + StringUtils.capitalize((rows.get(0)[k]).trim()));
            }
            Method[] methodList = clazz.getMethods();
            for (int i = 1; i < rows.size(); i++) {
                // create new Object (newInstance)
                BaseData o = clazz.newInstance();
                for (int j = 0; j < (rows.get(i)).length; j++) {
                    Method method = null;
                    for (Method m : methodList) {
                        System.out.println(m.getName());
                        System.out.println(NamesOfMethods.get(j));
                        if (m.getName().equals(NamesOfMethods.get(j))) {
                            method = m;
                            break;
                        }
                    }
                    System.out.println(method.getParameterTypes()[0]);
                    if (method.getParameterTypes()[0] == String.class) {
                        method.invoke(o, (rows.get(i)[j]).trim());
                    } else if (method.getParameterTypes()[0].asSubclass(BaseData.class) == method.getParameterTypes()[0]) {
                        BaseData parameterOfSetter = null;
                        List<BaseData> csv = readCSV(method.getParameterTypes()[0].asSubclass(BaseData.class));
                        for (BaseData bd : csv) {
                            if (bd.getId().equals(rows.get(i)[j])) {
                                parameterOfSetter = bd;
                                break;
                            }
                        }
                        method.invoke(o, parameterOfSetter);
                    }else{
                        throw new Exception("UNKNOWN TYPE");
                    }

                }
                result.add(o);
            }
        } catch (Throwable e) {

            throw new RuntimeException(e);
        }
        return result;
    }



    public static void main(String[] args) {
        List<BaseData> objects = DataProcessor.readCSV(TestNewRegisterUserData.class);
        System.out.println(objects);

    }
}