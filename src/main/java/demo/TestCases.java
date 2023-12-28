package demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
  ChromeDriver driver;

  public TestCases() {
    System.out.println("Constructor: TestCases");

    WebDriverManager.chromedriver().timeout(30).setup();
    ChromeOptions options = new ChromeOptions();
    LoggingPreferences logs = new LoggingPreferences();

    // Set log level and type
    logs.enable(LogType.BROWSER, Level.ALL);
    logs.enable(LogType.DRIVER, Level.ALL);
    options.setCapability("goog:loggingPrefs", logs);

    // Set path for log file
    System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

    driver = new ChromeDriver(options);

    // Set browser to maximize and wait
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    driver.get("https://www.google.com");
  }

  public void endTest() {
    System.out.println("End Test: TestCases");
    driver.close();
    driver.quit();

  }

  public boolean testCase01() throws InterruptedException {
    System.out.println("Start Test case: testCase01");
    WebElement input = driver.findElement(By.cssSelector("#APjFqb"));
    input.sendKeys("calculator");
    input.sendKeys(Keys.RETURN);
    Thread.sleep(1000);

    String url=driver.getCurrentUrl();
    if(url.contains("google")){
        System.out.println("URL verified");
    }

    String res = driver.findElement(By.cssSelector(" #cwos")).getText();
    if (res.equals("0")) {
      System.out.println("initial display shows zero(0)");

      return true;
    }
    return false;

  }

  public boolean testCase02() throws InterruptedException {
    String res1 = Calci("5", "7", "Add");
    if (res1.equals("12")) {
      System.out.println("Successfully added");
    }
    String res2 = Calci("15", "8", "Sub");
    if (res2.equals("7")) {
      System.out.println("Successfully subtract");
    }
    return true;
  }

 public boolean testCase03() throws InterruptedException {
    String res1 = Calci("10", "3", "Mul");
    if (res1.equals("30")) {
      System.out.println("Successfully Multipled");
    }
    driver.findElement(By.xpath("//div[text()='=']")).click();
      driver.findElement(By.xpath("//div[text()='AC']")).click();
     String res = driver.findElement(By.cssSelector(" #cwos")).getText();
    if (res.equals("0")) {
      System.out.println("initial display shows zero(0)");
      return true;
    }
    return false;
  }

  
 public boolean testCase04() throws InterruptedException {
    String res1 = Calci("20", "4", "Div");
    if (res1.equals("5")) {
      System.out.println("Successfully Divided");
        return true;
    }
    return false;
  }

  public String Calci(String a, String b, String Operator) throws InterruptedException {

    String xpathop;
    if (a.length() > 1) {
      for (int i = 0; i < a.length(); i++) {
        String xpatha = String.format("//div[text()='%s']", a.charAt(i));
        driver.findElement(By.xpath(xpatha)).click();
        Thread.sleep(1000);
      }
    } else {
      String xpatha = String.format("//div[text()='%s']", a);
      driver.findElement(By.xpath(xpatha)).click();
    }

    if (Operator.equals("Add")) {
      xpathop = "//table[@class='ElumCf']//tr[5]/td[4]";
    } else if (Operator.equals("Sub")) {
      xpathop = "//table[@class='ElumCf']//tr[4]/td[4]";
    } else if (Operator.equals("Mul")) {
      xpathop = "//table[@class='ElumCf']//tr[3]/td[4]";
    } else {
      xpathop = "//table[@class='ElumCf']//tr[2]/td[4]";
    }

    driver.findElement(By.xpath(xpathop)).click();

    if (b.length() > 1) {
      for (int i = 0; i < b.length(); i++) {
        String xpathb = String.format("//div[text()='%s']", b.charAt(i));
        driver.findElement(By.xpath(xpathb)).click();
        Thread.sleep(1000);
      }
    } else {
      String xpathb = String.format("//div[text()='%s']", b);
      driver.findElement(By.xpath(xpathb)).click();
    }

    // driver.findElement(By.xpath(xpathb)).click();
    driver.findElement(By.xpath("//div[text()='=']")).click();
    String res = driver.findElement(By.cssSelector(" #cwos")).getText();
    driver.findElement(By.xpath("//div[text()='0']")).click();
    return res;
  }

}
