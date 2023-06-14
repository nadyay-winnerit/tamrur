package infra.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import data.BaseData;
import data.pages.RegisterUserPageData;
import data.tests.TestExampleData;
import infra.general.AutomationException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class DWH {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping()
            .setDateFormat("dd/MM/yyyy").create();

    public static void saveData(DataKeys key, BaseData data) {
        try {
            String json = gson.toJson(data);
            File file = new File("dwh/" + key + ".json");
            FileUtils.writeStringToFile(file, json, StandardCharsets.UTF_8);
        } catch (Throwable t) {
            throw new AutomationException("כשלון בשמירת אובייקט למפתח " + key, null, t);
        }
    }

    public static <T extends BaseData> T getData(DataKeys key, Class<T> clazz) {
        try {
            File file = new File("dwh/" + key + ".json");
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            return (T) gson.fromJson(json, clazz);
        } catch (Throwable t) {
            throw new AutomationException("כשלון בקריאת אובייקט למפתח " + key, null, t);
        }
    }

    public static RegisterUserPageData getNewUser() {
        return getData(DataKeys.NewUser, RegisterUserPageData.class);
    }

    public static RegisterUserPageData getTherapist() {
        DataKeys key = DataKeys.Therapist;
        try {
            File file = new File("dwh/" + key + ".json");
            String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            return gson.fromJson(json, RegisterUserPageData.class);
        } catch (Throwable t) {
            throw new AutomationException("כשלון בקריאת אובייקט למפתח " + key, null, t);
        }
    }

    public static void main(String[] args) {
        TestExampleData testData = new TestExampleData();
        RegisterUserPageData data = new RegisterUserPageData();
        data.setEmailInput("DD@RR.GG");
        data.setFNameInput("משה חג'ג'");
//        data.date = new Date();
        testData.setRegisterUserPageData(data);
        saveData(DataKeys.NewUser, data);

        RegisterUserPageData newUser = getNewUser();
        RegisterUserPageData newUser2 = getData(DataKeys.NewUser, RegisterUserPageData.class);
    }

}
