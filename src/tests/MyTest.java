//package tests;
//
//import infra.Browser;
//import infra.TableCell;
//import infra.TableWe;
//import infra.test.TestBase;
//import objects.pages.LoginPageData;
//import org.junit.AfterClass;
//import org.junit.FixMethodOrder;
//import org.junit.Test;
//import org.junit.runners.MethodSorters;
//import org.openqa.selenium.By;
//import pages.LoginPage;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class MyTest extends TestBase {
//
//    @Test
//    public void test_1() throws InterruptedException {
//        List<Integer> listNumberRows = new ArrayList<>();
//        Map<String, String> map = new HashMap<>();
//        List<TableCell> tableCells = new ArrayList<>();
//        TableWe tableWe = new TableWe("table", By.tagName("table"));
//        LoginPage loginPage = new LoginPage();
//        LoginPageData loginPageData = new LoginPageData();
//        loginPageData.setUserNameUi("auto@mation.com");
//        loginPageData.setPassWordUi("mation");
//        loginPageData.setRememberCheckBoxUi(true);
//        //List<Object> objects = DataProcessor.readCSV(LoginPageData.class);
//        loginPage.fillPage(loginPageData);
//        loginPage.connectionButtonClick();
//        Thread.sleep(2002);
//        //click on תיקים רפואיים
//        Browser.driver().findElement(By.cssSelector("a.nav-link.active[ng-reflect-router-link='/home/dashboard']")).click();
//        Thread.sleep(2022);
//        //tableCells.add(new TableCell("שם מטופל","אליהו הנביא",false));
//        tableCells.add(new TableCell("סטטוס", "תיק חדש", true));
//        tableCells.add(new TableCell("שם מטופל", "נחום לוי", false));
//        //tableCells.add(new TableCell("מספר","1",false));
//        listNumberRows = tableWe.search(tableCells);
//        System.out.println(tableWe.validate(tableCells, 2));
//        for (int num : listNumberRows) {
//            System.out.print(num + " ,");
//        }
//        System.out.println("=========================");
//        System.out.println(listNumberRows);
//
//    }
//
//
//    @AfterClass
//    public static void afterClass() {
//        Browser.close();
//    }
//}
