package infra.data;

import data.BaseData;
import data.pages.newTherapist.ContactInformationPageData;
import infra.general.AutomationException;
import infra.general.Config;
import infra.general.Prop;
import infra.general.Utils;
import org.apache.commons.lang3.StringUtils;
import org.jooq.tools.csv.CSVReader;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

public class DataProcessor {

    public static String currentTestId;

    public static BaseData currentTestData;

    private static List<BaseData> readCSV(Class<? extends BaseData> clazz) {

        List<BaseData> result = new ArrayList<>();
        String currentColumn = null;
        Method[] methodList = null;
        try {
            String classPath = clazz.getName().replace("data.", "").replace(".", "\\");
            Reader reader = new FileReader("resources\\csv\\" + classPath + ".csv");
            CSVReader csvReader = new CSVReader(reader, '|');
            List<String[]> rows = csvReader.readAll();
            List<String> namesOfMethods = new ArrayList<>();
            for (int k = 0; k < rows.get(0).length; k++) {
                namesOfMethods.add("set" + StringUtils.capitalize((rows.get(0)[k]).trim()));
            }
            methodList = clazz.getMethods();
            for (int i = 1; i < rows.size(); i++) {

                BaseData o = clazz.newInstance(); // create new Object (newInstance)
                for (int j = 0; j < (rows.get(i)).length; j++) {
                    Method method = null;
                    for (Method m : methodList) {
                        currentColumn = namesOfMethods.get(j);
                        if (m.getName().equals(currentColumn)) {
                            method = m;
                            break;
                        }
                    }

                    if (rows.get(i)[j] == null)
                        continue;

                    String csvValue = rows.get(i)[j].trim();
                    Class<?> setterType = method.getParameterTypes()[0];
                    if (setterType == String.class) {
                        String value = getValue(csvValue);
                        method.invoke(o, value);
                    } else if (setterType == Boolean.class) {
                        method.invoke(o, Boolean.valueOf(csvValue));
                    } else if (setterType.isEnum()) {
                        for (Object enumc : setterType.getEnumConstants()) {
                            if (((Enum) enumc).name().equals(csvValue)) {
                                method.invoke(o, enumc);
                                break;
                            }
                        }
                    } else if (BaseData.class.isAssignableFrom(setterType)) {
                        BaseData parameterOfSetter = getDataObjectById((Class<? extends BaseData>) setterType, csvValue);
                        method.invoke(o, parameterOfSetter);
                    } else if (setterType == List.class) {
                        List<Object> listData = new ArrayList<>();
                        String[] ids = csvValue.split(";");
                        for (String id : ids) {
                            Class<?> actualTypeArgument = (Class<?>) ((ParameterizedType) method.getGenericParameterTypes()[0]).getActualTypeArguments()[0];
                            BaseData parameterOfSetter = null;
                            if (BaseData.class.isAssignableFrom(actualTypeArgument)) {
                                parameterOfSetter = getDataObjectById((Class<? extends BaseData>) actualTypeArgument, id);
                            } else {
                                throw new AutomationException("UNKNOWN TYPE for list - " + (actualTypeArgument).getName(), null, null);
                            }
                            listData.add(parameterOfSetter);
                        }
                        method.invoke(o, listData);
                    } else {
                        throw new AutomationException("UNKNOWN TYPE - " + setterType.getName(), null, null);
                    }
                }
                result.add(o);
            }
        } catch (Throwable e) {
            String methodsList = methodList == null ? "no methods determined" : Arrays.asList(methodList).toString().replace(", public", "\r\npublic");
            throw new AutomationException("כשלון ב CSV " + clazz.getSimpleName() + " for method " + currentColumn
                    , clazz.getSimpleName() + " Class Methods :\r\n" + methodsList, e);
        }

        return result;
    }

    private static BaseData getDataObjectById(Class<? extends BaseData> dataClass, String id) {
        List<BaseData> csv = readCSV(dataClass);
        for (BaseData bd : csv) {
            if (bd.getId().equals(id)) {
                return bd;
            }
        }
        return null;
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

        if (filteredResult.isEmpty()) {
            throw new AutomationException("There is nothing to run - please check your filter (on config.properties file?)"
                    , "Prop.TESTCASE = " + testCase + "; currentTestId = " + currentTestId, null);
        }

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

        if (filteredResult.isEmpty()) {
            throw new AutomationException("There is nothing to run - please check your filter (on config.properties file?)"
                    , "Prop.SUITECASE = " + suiteCase, null);
        }

        List<Object[]> data = new ArrayList<>();
        for (BaseData d : filteredResult) {
            data.add(new Object[]{d.getId(), d});
        }
        return data;
    }

    private static String getValue(String str) {
        if (str.startsWith("[") && str.endsWith("]")) {
            if (str.startsWith("[TODAY")) {
                //[TODAY-18Y+1M-2D]
                return getDate(str);
            } else if (str.startsWith("[RANDOM")) {
                //[RANDOM_N_100_1000]
                if (str.startsWith("[RANDOM_N")) {
                    String[] splitList = str.split("_");
                    int origin = Integer.parseInt(splitList[2]);
                    int bound = Integer.parseInt(splitList[3].substring(0, splitList[3].length() - 1));
                    int n = bound - origin;
                    if (n > 0) {
                        return String.valueOf(new Random().nextInt(n) + origin);
                    }
                    //[RANDOM_S_50]
                } else if (str.startsWith("[RANDOM_S")) {
                    String[] splitList = str.split("_");
                    int n = Integer.parseInt(splitList[2].substring(0, splitList[2].length() - 1));
                    return getAlphaNumericString(n);
                } else if (str.startsWith("[RANDOM_EMAIL")) {
                    String[] splitList = str.split("_");
                    int n = Integer.parseInt(splitList[2].substring(0, splitList[2].length() - 1));
                    return getEmail(n);
                } else {
                    throw new AutomationException("Unknown Formula :: " + str, null, null);
                }
            } else {
                throw new AutomationException("Unknown Formula :: " + str, null, null);
            }
        }
        return str;
    }

    private static String getAlphaNumericString(int n) {
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

    private static String getEmail(int n) {
        String alphaString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz";
        String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvxyz" + "0123456789";
        String endEmail = "@gmail.com";
        StringBuilder sb = new StringBuilder(n);
        int indexForFirstLetter = (int) (alphaString.length() * Math.random());
        sb.append(alphaString.charAt(indexForFirstLetter));
        for (int i = 0; i < n - 1; i++) {
            int index = (int) (alphaNumericString.length() * Math.random());
            sb.append(alphaNumericString.charAt(index));
        }
        sb.append(endEmail);
        return sb.toString();
    }


    //return string in format dayOfMonth/month/year
    private static String getDate(String str) {
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
            } else {
                throw new AutomationException("Unknown Formula :: " + s + " in " + str, null, null);
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;// Jan = 0, dec = 11
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth + "/" + month + "/" + year;
    }


    public static void main(String[] args) {
        System.out.println();
//        currentTestId = "22";
        List<Object[]> objects = DataProcessor.createSuiteData(ContactInformationPageData.class);
//        List<Object[]> objects = DataProcessor.createSuiteData(UserInformationPageData.class);
        System.out.println(objects);
    }
}