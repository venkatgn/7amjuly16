package yahoo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

@Listeners({ ATUReportsListener.class, ConfigurationListener.class,MethodListener.class })
public class Home 
{
   
    WebDriver driver;
    {
    	System.setProperty("atu.reporter.config", "E:\\7AM-july-28\\myproj\\atu.properties");
    }
    
    public Home(WebDriver driver)
    {
    	this.driver=driver;
    }
	public void open()
	{		
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.yahoomail.com");
	}
	public void validate_links()
	{
		open();
		String exp[]={"About Mail","Features","Get App","Help"};
		WebElement w=driver.findElement(By.xpath("//ul[@class='Fl(end) Mend(10px) Lts(-0.31em) Tren(os) Whs(nw) My(6px)']"));
		List<WebElement> lst=w.findElements(By.xpath("li/a/b"));
		for(int i=0;i<exp.length;i++)
		{
			if(exp[i].matches(lst.get(i).getText()))
			{
				ATUReports.add("Link is matching :"+exp[i],LogAs.PASSED,new CaptureScreen(ScreenshotOf.DESKTOP));
			}
			else
			{
				ATUReports.add("Link is NOT matching :"+exp[i],LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			}
		}
		
	}
	public void createacc()
	{
		open();
		driver.findElement(By.id("login-signup")).click();
    	//
    	driver.findElement(By.name("firstName")).sendKeys("abcdx");
    	
    	
    	
    	new Select(driver.findElement(By.name("shortCountryCode")))
    	                .selectByValue("IN");
    	driver.findElement(By.name("phone")).sendKeys("9880987678");
    	new Select(driver.findElement(By.name("mm")))
    	                  .selectByVisibleText("March");
    	new Select(driver.findElement(By.name("dd"))).selectByVisibleText("20");
    	new Select(driver.findElement(By.name("yyyy"))).selectByVisibleText("1984");
    	
	}
	public void login() throws Exception
	{
		open();
		driver.findElement(By.name("username")).sendKeys("venkat123456a");
		driver.findElement(By.name("signin")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("passwd")).sendKeys("mq123456");
		driver.findElement(By.name("signin")).click();
		Thread.sleep(5000);
	}
}