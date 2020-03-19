package Testing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Register {
	
	private static WebDriver driver;
	private WebDriverWait wait;
	private String FName ,LName, MobNum, PW;
	private Random rand;
	private int SP; //Service Provider for Mobile Number

	@BeforeClass
	public void RegisterDriver() {
			driver = Initialize.getDriver();
			wait = new WebDriverWait(driver, 5);
	}
	
	@Test(priority=0)
	public void RegistrationInfo() {
		
		try {
			WebElement FirstName = driver.findElement(By.xpath("//*[@id=\"headersignupform\"]/div[3]/div[1]/div/label/input"));
			WebElement LastName = driver.findElement(By.xpath("//*[@id=\"headersignupform\"]/div[3]/div[2]/div/label/input"));
			WebElement MobileNumber = driver.findElement(By.xpath("//*[@id=\"headersignupform\"]/div[4]/label/input"));
			WebElement Email = driver.findElement(By.xpath("//*[@id=\"headersignupform\"]/div[5]/label/input"));
			WebElement Password = driver.findElement(By.xpath("//*[@id=\"headersignupform\"]/div[6]/label/input"));
			WebElement ConfirmPassword = driver.findElement(By.xpath("//*[@id=\"headersignupform\"]/div[7]/label/input"));
			WebElement RegisterButton = driver.findElement(By.xpath("/html/body/div[2]/div[1]/section/div/div/div[2]/div/form/div[8]/button"));

			rand = new Random();
			
			FileReader fileReader = new FileReader("names.txt");
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        List<String> result = new ArrayList<String>();
	        String line = null;
	        while ((line = bufferedReader.readLine()) != null) {
	        	result.add(line);
	        }
	        bufferedReader.close();
	        result.toArray(new String[result.size()]);
	        
			FName = result.get(rand.nextInt(result.size()));
			LName = result.get(rand.nextInt(result.size()));
			SP = rand.nextInt(6);
	
			while (FName.equals(LName))
				LName = result.get(rand.nextInt(result.size()));
			
			while (SP == 3 || SP == 4)
				SP = rand.nextInt(6);
			
			MobNum = "01" + SP + (10000000 + rand.nextInt(90000000));
			
			PW = RandomStringUtils.random(8, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
			while (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z]).+$", PW))
				PW = RandomStringUtils.random(8, "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
	
			FileWriter myWriter = new FileWriter("Credentials.txt");
			myWriter.write(FName + "." + LName + "@example.com\n");
			myWriter.write(PW);
			myWriter.close();
			
			FirstName.sendKeys(FName);
			LastName.sendKeys(LName);
			MobileNumber.sendKeys(MobNum);
			Email.sendKeys(FName + "." + LName + "@example.com");		  
			Password.sendKeys(PW);
			ConfirmPassword.sendKeys(PW);
			RegisterButton.sendKeys(Keys.RETURN);
		}
		catch(Exception e) {
			
		}
	}
	
	@Test(priority=1)
	public void RegistrationSuccess() {
		
		try {
			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[2]/div[1]/div[1]/div/div/div[1]/div/div[2]/h3")));

			String actualUrl="https://www.phptravels.net/account/";
	        String expectedUrl= driver.getCurrentUrl();
	        
	        Assert.assertEquals(actualUrl, expectedUrl);
		}
        catch(AssertionError e) {
        	//On failed listener and call TerminateTests
        	System.out.println("RegistrationSuccess failed");
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
