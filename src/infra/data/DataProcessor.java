package infra.data;

import objects.pages.RegisterUserPageData;
import org.jooq.tools.csv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DataProcessor {

    public static List<Object> readCSV(Class<?> clazz) {

        List<Object> result = new ArrayList<>();
        try {
            String classPath = clazz.getName().replace("objects.", "").replace(".", "\\");
            Reader reader = new FileReader("resources\\csv\\" + classPath + ".csv");
            CSVReader csvReader = new CSVReader(reader, '|');
            List<String[]> rows = csvReader.readAll();


                for (String s : rows.get(0)) {
                    System.out.print(s + ",");
                }

            Object o = clazz.newInstance();
            for (int i = 1; i< rows.size(); i++) {
                // create new Object (newInstance)
                for (String s : rows.get(i)) {
                    System.out.print(s + ",");
                    // fill instance
                }
                System.out.println();
                result.add(o);
            }

            {
                Method[] methods = clazz.getMethods();
                Method[] declaredMethods = clazz.getDeclaredMethods();

                methods[0].invoke(o, rows.get(1)[2]); //  var.setSomething("abc");
            }
            {
                int columnIndex = 1;
                Method method = clazz.getMethod(rows.get(0)[columnIndex]);
                Method declaredMethod = clazz.getDeclaredMethod(rows.get(0)[columnIndex]);

                method.invoke(o, rows.get(1)[columnIndex]); //  var.setSomething("abc");
            }



        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return result;
    }

//    public static Object readCSVRow(String id){
//
//    }

    public static void main(String[] args) {
        List<Object> objects = DataProcessor.readCSV(RegisterUserPageData.class);
        System.out.println();
    }

}