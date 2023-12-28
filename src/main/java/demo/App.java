package demo;
import java.net.MalformedURLException;


public class App {
    public void getGreeting() throws InterruptedException, MalformedURLException {
        
        // This is to remove unnecessary warnings from your console
        System.setProperty("java.util.logging.config.file", "logging.properties");
        
        TestCases tests = new TestCases(); // Initialize your test class

        //TODO: call your test case functions one after other here
        int count=0;

        if(tests.testCase01()){
            count++;
            System.out.println("end Test case: testCase01");
        }
          if(tests.testCase02()){
            count++;
            System.out.println("end Test case: testCase02");
        }

        if(tests.testCase03()){
            count++;
            System.out.println("end Test case: testCase03");
        }

        if(tests.testCase04()){
            count++;
            System.out.println("end Test case: testCase04");
        }

        System.out.println("Testcase passed count "+count);

        //END Tests


        tests.endTest(); // End your test by clearning connections and closing browser
    }

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        new App().getGreeting();
    }
}
