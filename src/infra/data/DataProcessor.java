package infra.data;

import objects.pages.RegisterUserPageData;
import org.jooq.tools.csv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class DataProcessor {

    public static List<Object> readCSV(Class<?> clazz)  {

        List<Object> result = new ArrayList<>();
       try {
            String classPath = clazz.getName().replace("objects.", "").replace(".", "\\");
            Reader reader = new FileReader("resources\\csv\\" + classPath + ".csv");
            System.out.println("resources\\csv\\" + classPath + ".csv");
            CSVReader csvReader = new CSVReader(reader, '|');
            List<String[]> rows = csvReader.readAll();
            List<String> NamesOfMethods = new ArrayList<>();
            String row = null;
            for (int k = 0; k<rows.get(0).length; k++){
                row =rows.get(0)[k];
                row=row.replaceFirst(row.substring(0,1),row.substring(0,1).toUpperCase());
                NamesOfMethods.add("set"+row);
            }
            for (int i = 1; i< rows.size(); i++) {
                // create new Object (newInstance)
                Object o = clazz.newInstance();
               for (int j = 0; j< (rows.get(i)).length; j++) {
//                    // fill instance
                       Method method = clazz.getDeclaredMethod(NamesOfMethods.get(j), String.class);
                       method.invoke(o,rows.get(i)[j]);
             }
                System.out.println();
                result.add(o);
            }
} catch (Throwable e) {

   throw new RuntimeException(e);
     }
        return result;
    }

    public static void main(String[] args)  {
        List<Object> objects = DataProcessor.readCSV(RegisterUserPageData.class);
        System.out.println(objects);
        RegisterUserPageData t=((RegisterUserPageData) (objects.get(0)));
        System.out.println( "email "+t.getEmailInput()+"   fname "+t.getFNameInput()+"   pwc "+t.getPwdInput()+"  lr"+t.getLrSelect()+"  active"+t.getActiveInput());
    }

}