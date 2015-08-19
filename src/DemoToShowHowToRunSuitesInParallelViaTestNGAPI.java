import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;


public class DemoToShowHowToRunSuitesInParallelViaTestNGAPI {
	public static XmlSuite createSuite(String suiteName, String testName, int iteration) {
        XmlSuite suite = new XmlSuite();
        suite.setName(suiteName);
        suite.setParallel(XmlSuite.PARALLEL_NONE);

        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("iterations", Integer.toString(iteration));

        XmlTest test = new XmlTest(suite);
        test.setName(testName);
        test.addParameter("iterations", Integer.toString(iteration));
        List<XmlClass> clazzes = new ArrayList<XmlClass>();
        XmlClass clazz = new XmlClass(MyTestClass.class);
        clazzes.add(clazz);
        test.setClasses(clazzes);
        List<XmlTest> tests = new ArrayList<XmlTest>();
        tests.add(test);
        suite.setTests(tests);
        return suite;
    }

	
    public static void main(String[] args) {
        TestNG testNG = new TestNG();
        testNG.setSuiteThreadPoolSize(3);
        List<XmlSuite> suites = new ArrayList<XmlSuite>();
        suites.add(createSuite("Sylvester Stallone", "Rambo", 20));
        suites.add(createSuite("Jim Carrey", "The Mask", 10));
        testNG.setXmlSuites(suites);

        testNG.run();

    }

} 
