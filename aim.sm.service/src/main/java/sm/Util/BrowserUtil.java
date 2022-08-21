package sm.Util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserUtil {
	
	public WebDriver Launchchrome(){
		  
		  // System Property for Chrome Driver   
	      System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");  
	        
	      // Instantiate a ChromeDriver class.     
	      WebDriver wdMain=new ChromeDriver();  
	      wdMain.manage().window().maximize();
		  wdMain.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		  return wdMain;
	  }
}
