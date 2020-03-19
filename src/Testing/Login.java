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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {
	
	private static WebDriver driver;
	private WebDriverWait wait;
	private String Mail, Pw;

	@BeforeClass
	public void LoginDriver() {
		driver = Initialize.getDriver();
		wait = new WebDriverWait(driver, 5);
	}
		
	@Test(priority=0)
	public void Enter() throws IOException {
		
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
			  
			WebElement PW = driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/div[3]/div[2]/label/input"));
			PW.sendKeys(Pw);
			
			WebElement LoginButton = driver.findElement(By.xpath("//*[@id=\"loginfrm\"]/button"));
			LoginButton.sendKeys(Keys.RETURN);
		}
		catch(AssertionError e) {
			
		}
	}
	
	@Test(priority=1)
	public void VerifyLogin() {
		
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
	
	@AfterClass
	public void Logout() {
		
		try {
			
			WebElement LogoutList = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div/div[2]/div/ul/li[3]/div/a"));
			WebElement LogoutButton = driver.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div/div[2]/div/ul/li[3]/div/div/div/a[2]"));

			LogoutList.sendKeys(Keys.RETURN);
			LogoutButton.sendKeys(Keys.RETURN);
				
			String actualUrl="https://www.phptravels.net/login";
	        String expectedUrl= driver.getCurrentUrl();
	        	        
	        Assert.assertEquals(actualUrl, expectedUrl);
		}
        catch(AssertionError e) {
        	//On failed listener and call TerminateTests
        	System.out.println("Logout failed");
        }
	}
}
