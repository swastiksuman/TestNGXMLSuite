import java.util.Random;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MyTestClass {
    private String className;

    @BeforeClass
    public void setupClassName(ITestContext context) {
        String testName = context.getCurrentXmlTest().getName();
        String suiteName = context.getCurrentXmlTest().getSuite().getName();
        this.className = "[" + suiteName + "," + testName + "]";
    }

    @Test(dataProvider = "dp")
    public void testMethod(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.className);
        sb.append(", Thread ID = ");
        sb.append(Thread.currentThread().getId());
        sb.append(",Value got was ");
        sb.append(i);
        System.out.println(sb.toString());
    }

    @DataProvider(name = "dp")
    public Object[][] generateTestData(ITestContext context) {
        String iterations = context.getCurrentXmlTest().getLocalParameters().get("iterations");
        int count = 5;
        try {
            count = Integer.parseInt(iterations);
        } catch (NumberFormatException e) {
            // do nothing with the exception.
            // exception would be generated, when the parameter iterations is not passed
            // or when it cannot be read as an int. In those situations default to the value of 5
        }
        Object[][] objectToReturn = new Object[count][1];
        for (Object[] iter : objectToReturn) {
            iter[0] = new Random().nextInt();
        }
        return objectToReturn;

    }
}
