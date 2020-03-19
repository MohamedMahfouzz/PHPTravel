package Testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class InitializeNeg {
	
	private static WebDriver driver;
	private String BaseURL, ExpectedTitle, ActualTitle;
    
    public static WebDriver getDriver() {
        return driver;
    }
    
    @BeforeTest
    public void LaunchWebsite() {
    	
    	try {
    		BaseURL = "https://www.phptravels.net/login";
    		ExpectedTitle = "Login";
	        System.setProperty("webdriver.chrome.driver","C:\\Webdrivers\\chromedriver.exe");
	        driver = new ChromeDriver();
			driver.manage().window().maximize();
	        driver.get(BaseURL);
	        ActualTitle = driver.getTitle();
	       
	        Assert.assertEquals(ActualTitle, ExpectedTitle);
        }
        catch(AssertionError e) {
        	//On failed listener and call TerminateTests
        	System.out.println("LaunchWebsite failed");
        }
    }
    
    @AfterTest
    public static void TerminateTest() {
    	driver.close();
    }
}
