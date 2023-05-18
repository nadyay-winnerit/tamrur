package tests;

import infra.data.DataProcessor;
import infra.enums.Users;
import infra.test.TestBase;
import objects.tests.TestExampleData;
import org.junit.runners.Parameterized;
import pages.LoginPage;

import java.util.Collection;

public class TestExample extends TestBase {

    TestExampleData testData;

    public TestExample(String testCase, TestExampleData testData) {
        super(testCase, testData);
        this.testData = testData;
    }

    @Parameterized.Parameters(name = "{index}::{0}")
    public static Collection data() {
        return DataProcessor.createTestData(TestExampleData.class);
    }

    @Override
    public void test() {
        new LoginPage().fillPage(Users.melave).finish();
//         new HomePage().fillPage(testData.getLoginPageData()).finish();
//         new LoginPage().fillPage(testData.getLoginPageData()).finalAct();
//         new LoginPage().fillPage(testData.getLoginPageData()).finalAct();
//         new LoginPage().fillPage(testData.getLoginPageData()).finalAct();
//        logout();
//        new LoginPage().fillPage(testData.getLoginPageData()).validatePage().finalAct();
    }
}
