package pom_Object_Repository;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Game_Selection_Page {

	By btnSMWorld=By.xpath("//*[@id='currentScreenA']/div/div/a/div/div[2]/button");
	WebElement we;
	WebDriver wd;
	WebDriverWait wdWait;
	
	public Game_Selection_Page(WebDriver wd) {
		// TODO Auto-generated constructor stub
		this.wd=wd;
	}
	
	public void goToSMWorld(){
		wdWait= new WebDriverWait(wd,5);
		we=wdWait.until(ExpectedConditions.visibilityOfElementLocated(btnSMWorld));
		Assert.assertEquals(wd.getTitle(), "Soccer Manager 2016 / Soccer Manager Worlds - Free Soccer Manager game");
		we.click();
		
	}
	
	
}
