package Testing;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.testng.ITestContext ;		
import org.testng.ITestListener ;		
import org.testng.ITestResult ;
 
public class ListenerTest implements ITestListener						
{
	private FileWriter MR;
	
    @Override		
    public void onFinish(ITestContext Result) 					
    {		
    	try {
    		MR.write(Result.getName() + " testing done");
			MR.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }		

    @Override		
    public void onStart(ITestContext Result)					
    {
		try {
			MR = new FileWriter(Result.getName() + ".txt");
			MR.write(Result.getName() + " testing starting\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)					
    {		
    		
    }		

    @Override		
    public void onTestFailure(ITestResult Result) 		
    {	
    	try {
    		MR.write("The name of the testcase failed is :" + Result.getName() + "\n");
    		MR.write("Check screenshot Error_" + Result.getName() + ".png for more details\n");
	    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	    BufferedImage capture = new Robot().createScreenCapture(screenRect);
	    ImageIO.write(capture, "bmp", new File("Error_" + Result.getName() + ".png"));
    	}catch(Exception e) {
    		
    	}
    Initialize.TerminateTest();
    }		

    @Override		
    public void onTestSkipped(ITestResult Result)					
    {		
    }		

    @Override		
    public void onTestStart(ITestResult Result)					
    {		
    	try {
			MR.write("\t" + Result.getName()+" test case started\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
    }		

    @Override		
    public void onTestSuccess(ITestResult Result)					
    {		
    	try {
			MR.write("\t\tThe name of the testcase passed is :" + Result.getName() + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}					
    }		
}		