package infra.test;

import infra.*;
import objects.BaseData;
import org.junit.*;
import org.junit.rules.*;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public abstract class TestBase {

    public static Reporter reporter = Reporter.reporter();
    private BaseData testData;
    Reporter report= Reporter.reporter();

    public TestBase(BaseData testData) {
        this.testData = testData;
    }

    @Test
    public abstract void test();

    @Rule
    public TestRule junitRule = new TestWatcher() {
        @Override
        public Statement apply(final Statement base, final Description description) {
            return new Statement() {
                @Override
                public void evaluate() {
                    try {
                        starting(description);
                        base.evaluate();  //לולאה?
                        succeeded(description);
                    } catch (Throwable t) {
                        failed(t, description);
                    } finally {
                        finished(description);
                    }
                }
            };
        }


        @Override
        protected void starting(Description description) {
            report.result("the test"+ description+" started", true);

            //צריך לבדוק האם הדרייבר כבר מאותחל? או כל טסט לאתחל אותו מחדש?
            Browser.driver();
        }

        @Override
        protected void succeeded(Description description) {
            report.result("the test passed successfully", true);
        }

        @Override
        protected void failed(Throwable e, Description description) {
            //if the test failed, the reporter writes an error msg, and counts the errors
            report.result("the test has been failed", false);
        }

        @Override
        protected void finished(Description description) {
            report.result("finished the test", true);
            Browser.driver().close();
        }
    };












    @Before
    public void before(){

        //צריך להחליף ל   open level
        //זאת אומרת שצריל ליצור פונקציה שתיצור בדף הדוח רמות קטגוריה, על כל טסט שייפתח בר נוסף עם שורות של כל הצעדים, ולאחר מכן ייסגר, ובטסט הבא תהיה פתיחה חדשה
        reporter.message("enter test ["+ testData.getId() + "] in [" + this.getClass().getSimpleName() +"]");

        //before every test the errors counter should be reset
//        reporter.resetErrorCounter();
    }

    @After
    public void after(){
        if(!reporter.ifNoErrors()){
            throw new RuntimeException("test ["+ testData.getId() + "] in [" + this.getClass().getSimpleName() +"] was finished with errors");
        }

        //צריך להוסיף  close level
    }
}
