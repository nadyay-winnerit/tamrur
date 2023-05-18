package infra.data;

import infra.general.Config;
import infra.general.Prop;
import infra.general.Utils;
import objects.BaseData;
import objects.pages.newTherapist.UserInformationPageData;
import org.apache.commons.lang3.StringUtils;
import org.jooq.tools.csv.CSVReader;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class DataProcessor {

    public static String currentTestId;

    public static BaseData currentTestData;

    private static List<BaseData> readCSV(Class<? extends BaseData> clazz) {

        List<BaseData> result = new ArrayList<>();
        try {
            String classPath = clazz.getName().replace("objects.", "").replace(".", "\\");
            Reader reader = new FileReader("resources\\csv\\" + classPath + ".csv");
            CSVReader csvReader = new CSVReader(reader, '|');
            List<String[]> rows = csvReader.readAll();
            List<String> namesOfMethods = new ArrayList<>();
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
                        if (m.getName().equals(namesOfMethods.get(j))) {
                            method = m;
                            break;
                        }
                    }

                    if (rows.get(i)[j] == null)
                        continue;

                    String csvValue = rows.get(i)[j].trim();
                    if (method.getParameterTypes()[0] == String.class) {
                        String value = getValue(csvValue);
                        method.invoke(o, value);
                    } else if (BaseData.class.isAssignableFrom(method.getParameterTypes()[0])) {
                        BaseData parameterOfSetter = null;
                        List<BaseData> csv = readCSV((Class<? extends BaseData>) method.getParameterTypes()[0]);
                        for (BaseData bd : csv) {
                            if (bd.getId().equals(csvValue)) {
                                parameterOfSetter = bd;
                                break;
                            }
                        }
                        method.invoke(o, parameterOfSetter);
                    } else if (method.getParameterTypes()[0].isEnum()) {
                        for (Object enumc : method.getParameterTypes()[0].getEnumConstants()) {
                            if (((Enum) enumc).name().equals(csvValue)) {
                                method.invoke(o, enumc);
                                break;
                            }
                        }

                    } else if (method.getParameterTypes()[0] == Boolean.class) {
                        method.invoke(o, Boolean.valueOf(csvValue));
                    } else {
                        throw new Exception("UNKNOWN TYPE - " + method.getParameterTypes()[0].getName());
                    }
                }
                result.add(o);
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static List<Object[]> createTestData(Class<? extends BaseData> clazz) {
        List<Object[]> data = new ArrayList<>();

        if (currentTestData != null) {
            data.add(new Object[]{currentTestData.getId(), currentTestData});
            currentTestData = null;
            return data;
        }

        List<BaseData> csvData = readCSV(clazz);
        List<BaseData> filteredResult = new ArrayList<>();
        String testCase = Config.getInstance().getValueOfProperty(Prop.TESTCASE);
        if (!Utils.isNullOrEmpty(currentTestId) || !Utils.isNullOrEmpty(testCase)) {
            for (BaseData dataObj : csvData) {
                if (dataObj.getId().equals(currentTestId) || dataObj.getId().equals(testCase)) {
                    filteredResult.add(dataObj);
                    break;
                }
            }
        } else {
            filteredResult.addAll(csvData);
        }

        currentTestId = null;

        for (BaseData d : filteredResult) {
            data.add(new Object[]{d.getId(), d});
        }

        return data;

    }

    public static List<Object[]> createSuiteData(Class<? extends BaseData> clazz) {

        List<BaseData> csvData = readCSV(clazz);
        List<BaseData> filteredResult = new ArrayList<>();
        String suiteCase = Config.getInstance().getValueOfProperty(Prop.SUITECASE);
        if (!Utils.isNullOrEmpty(suiteCase)) {
            for (BaseData dataObj : csvData) {
                if (dataObj.getId().equals(suiteCase)) {
                    filteredResult.add(dataObj);
                }
            }
        } else {
            filteredResult.addAll(csvData);
        }

        List<Object[]> data = new ArrayList<>();
        for (BaseData d : filteredResult) {
            data.add(new Object[]{d.getId(), d});
        }
        return data;
    }

    private static String getValue(String str) {
        Random random = new Random();
        String value = str;
        String[] splitList = null;
        if (str.startsWith("[") && str.endsWith("]")) {
            if (str.startsWith("[TODAY")) {
                //[TODAY-18Y+1M-2D]
                value = getDate(str);
            } else if (str.startsWith("[RANDOM")) {
                //[RANDOM_N_100_1000]
                if (str.startsWith("[RANDOM_N")) {
                    splitList = str.split("_");
                    int origin = Integer.parseInt(splitList[2]);
                    int bound = Integer.parseInt(splitList[3].substring(0, splitList[3].length() - 1));
                    int n = bound - origin;
                    if (n > 0) {
                        return String.valueOf(random.nextInt(n) + origin);
                    }
                    //[RANDOM_S_50]
                } else if (str.startsWith("[RANDOM_S")) {
                    splitList = str.split("_");
                    int n = Integer.parseInt(splitList[2].substring(0, splitList[2].length() - 1));
                    return getAlphaNumericString(n);
                }

            }
        }
        return value;
    }

    public static String getAlphaNumericString(int n) {
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz"
                + " ,:!@#$%^&*()_+-;?<>";
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (alphaNumericString.length() * Math.random());
            sb.append(alphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    //return string in format dayOfMonth/month/year
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
        int month = calendar.get(Calendar.MONTH) + 1;// Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth + "/" + month + "/" + year;
    }


    public static void main(String[] args) {
        currentTestId = "22";
        //List<Object[]> objects = DataProcessor.createSuiteData(TestNewRegisterUserData.class);
        List<Object[]> objects = DataProcessor.createSuiteData(UserInformationPageData.class);
        System.out.println(objects);
    }
}