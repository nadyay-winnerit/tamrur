package infra.test;

import infra.Browser;
import infra.reporter.Reporter;
import objects.BaseData;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.model.Statement;

@RunWith(Parameterized.class)
public abstract class TestBase {

    private BaseData testData;
    public static Reporter report = Reporter.reporter();

    public TestBase(String testCase,BaseData testData) {
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
            report.resetErrorCounter();
            report.openLevel("the test" + description + " started", null);
            before();
        }

        @Override
        protected void succeeded(Description description) {
            if (!report.ifNoErrors()) {
                report.error("the test has been failed", null);
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            //if the test failed, the reporter writes an error msg, and counts the errors
            Assert.fail("the test failed");
        }

        @Override
        protected void finished(Description description) {
            after();
            Browser.close();
            report.closeLevel();
        }
    };

    public void before() {
    }

    public void after() {
    }
}