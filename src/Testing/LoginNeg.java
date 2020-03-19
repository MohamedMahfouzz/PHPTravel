package Testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginNeg {
	
	private static WebDriver driver;
	private WebDriverWait wait;
	private String Mail, Pw;

	@BeforeClass
	public void LoginDriver() {
		driver = Initialize.getDriver();
		wait = new WebDriverWait(driver, 5);
	}
		
	@Test(priority=0)
	public void EnterNeg() throws IOException {
		
		try {
			
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[1]/section/div/div[1]/div[1]/div/img")));

		String actualUrl="https://www.phptravels.net/login";
        String expectedUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
		
		FileReader fileReader = new FileReader("Credentials.txt");
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		Mail = bufferedReader.readLine();
		Pw = bufferedReader.readLine();
		bufferedReader.close();

		WebElement Email = driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/div[3]/div[1]/label/input"));
		Email.sendKeys(Mail);
//		Email.sendKeys(Mail.substring(0, Mail.length()/2));
		  
		WebElement PW = driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/div[3]/div[2]/label/input"));
//		PW.sendKeys(Pw);
		PW.sendKeys(Pw.substring(0, Pw.length()/2));
		
		WebElement LoginButton = driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/button"));
		LoginButton.sendKeys(Keys.RETURN);
	}
	catch(AssertionError e) {
		
	}
}

@Test(priority=1)
public void VerifyLoginNeg() {
	
	try {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div/div/div[1]/div/div[2]/h3")));

		String actualUrl="https://www.phptravels.net/account/";
        String expectedUrl= driver.getCurrentUrl();
        
        Assert.assertEquals(expectedUrl, actualUrl);
	}
    catch(AssertionError e) {
    	//On failed listener and call TerminateTests
    	System.out.println("Assertion failed");
    }
}
}
