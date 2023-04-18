package infra.data;

import objects.BaseData;
import objects.tests.TestNewRegisterUserData;
import org.jooq.tools.csv.CSVReader;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.IntStream;

import org.apache.commons.lang3.StringUtils;

import static java.util.Calendar.DATE;

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
            List<String> namesOfMethods = new ArrayList<>();
            String row = null;
            for (int k = 0; k < rows.get(0).length; k++) {
                namesOfMethods.add("set" + StringUtils.capitalize((rows.get(0)[k]).trim()));
            }
            Method[] methodList = clazz.getMethods();
            for (int i = 1; i < rows.size(); i++) {
                // create new Object (newInstance)
                BaseData o = clazz.newInstance();
                for (int j = 0; j < (rows.get(i)).length; j++) {
                    Method method = null;
                    for (Method m : methodList) {
                        System.out.println(m.getName());
                        System.out.println(namesOfMethods.get(j));
                        if (m.getName().equals(namesOfMethods.get(j))) {
                            method = m;
                            break;
                        }
                    }
                   System.out.println(method.getName());
                    if (method.getParameterTypes()[0] == String.class) {
                        String value= getValue ((rows.get(i)[j]).trim());
                        method.invoke(o,value);
                    } else if (BaseData.class.isAssignableFrom(method.getParameterTypes()[0])) {
                        BaseData parameterOfSetter = null;
                        List<BaseData> csv = readCSV((Class<? extends BaseData>) method.getParameterTypes()[0]);
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
//ללא התיחסות למקרים שתבנית לא נכונה
    private static String getValue(String str) {
        Random random=new Random();
        String value=str;
        String[] splitList= null;
        if(str.startsWith("[")&&str.endsWith("]")){
             if (str.startsWith("[TODAY")){
                 //[TODAY-18Y+1M-2D]
                 value=getDate(str);
             } else if (str.startsWith("[RANDOM")) {
                 //[RANDOM_N_100_1000]
                 if (str.startsWith("[RANDOM_N")){
                     splitList=str.split("_");
                     int origin= Integer.parseInt(splitList[2]);
                     int bound= Integer.parseInt(splitList[3].substring(0,splitList[3].length()-1));
                     int n = bound - origin;
                     if (n > 0) {
                         return String.valueOf(random.nextInt(n) + origin);
                     }
                  //[RANDOM_S_50]
                 } else if (str.startsWith("[RANDOM_S")) {
                     splitList=str.split("_");
                     int n= Integer.parseInt(splitList[2].substring(0,splitList[2].length()-1));
                     return getAlphaNumericString(n);
                 }

             }
        }
        return value;
    }

   public static String getAlphaNumericString(int n)
    {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"
                +" ,:!@#$%^&*()_+-;?<>";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    //retutn string in format dayOfMonth/month/year
    public static String getDate(String str) {
        String[] splitList = null;
        Calendar calendar = Calendar.getInstance();
        splitList = StringUtils.split(str.substring(6, str.length() - 1), "-+");
        for (String s : splitList) {
            if (s.endsWith("Y")) {
                int n = Integer.parseInt(StringUtils.remove(s, 'Y'));
                int i = str.indexOf(s) - 1;
                if (str.charAt(i) == '-') {
                    calendar.add(Calendar.YEAR, -n);
                } else {
                    calendar.add(Calendar.YEAR, n);
                }
            } else if (s.endsWith("M")) {
                int n = Integer.parseInt(StringUtils.remove(s, 'M'));
                int i = str.indexOf(s) - 1;
                if (str.charAt(i) == '-') {
                    calendar.add(Calendar.MONTH, -n);
                } else {
                    calendar.add(Calendar.MONTH, n);
                }

            } else if (s.endsWith("D")) {
                int n = Integer.parseInt(StringUtils.remove(s, 'D'));
                int i = str.indexOf(s) - 1;
                if (str.charAt(i) == '-') {
                    calendar.add(Calendar.DAY_OF_MONTH, -n);
                } else {
                    calendar.add(Calendar.DAY_OF_MONTH, n);
                }

            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;// Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        System.out.println(dayOfMonth+"/"+month+"/"+year);
        return dayOfMonth+"/"+month+"/"+year;
    }


    public static void main(String[] args) {
        List<BaseData> objects = DataProcessor.readCSV(TestNewRegisterUserData.class);

       System.out.println(objects);
//        System.out.println(getValue("[RANDOM_N_100_1001]"));
//        System.out.println(getValue("[RANDOM_S_50]"));
       System.out.println(getValue("[TODAY-18Y+1M-9D]"));
        System.out.println(getValue("[TODAY]"));



    }
}