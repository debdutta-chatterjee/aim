package pom_Object_Repository;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SM_Home_Page {

	WebDriver wd;
	//By btnPlayNow= By.xpath("//*[@id='page-top']/div/div/a");
	By btnPlayNow= By.xpath("//button[normalize-space(text())='Play Now']");
	By btnLogin =By.xpath("//*[@id='loginTab']/a");
	By fbLink= By.xpath("//*[@id='loginDiv']/div/div[1]/a");
			//"//*[@id='RES_ID_fb_login_text']");
	By txtEmailID=By.id("email");
	By strPassword= By.id("pass");
	By btnFbLogin=By.id("u_0_2");
	By btnClubSearch=By.xpath("//*[@id='addClub-ClubSearch-tab']");
	
	//2.0
	By txtEmailID_normal=By.xpath("//form[@id='myform']//input[@id='username']");
	By strPassword_normal= By.xpath("//form[@id='myform']//input[@id='password']");
	By btnFbLogin_normal=By.xpath("//input[@value='Login' and contains(@class,'primary')]");
	By goldmanagement_modal_Dismiss=By.xpath("//div[@id='modalA']//*[normalize-space(text())='Gold Management']/parent::*[@id='playerpopup-header']/button");
	By freemanagement_modal_Dismiss=By.xpath("//div[contains(@class,'fade in')]//*[@id='freeCustomGameworldModal-header']/parent::*/button");
	By SM2022_modal_Dismiss=By.xpath("//div[contains(@class,'fade in')]//*[@id='drawInviteDesktopModalTemplate-header']/parent::*/button");
	By btnAddClub=By.xpath("//div[@id='ClubPicker-gws']//a[normalize-space(.)='Add Club']/div");
	
	String strWindowName;
	Set <String> windowNames;
	Iterator <String> windowNameIterator;
	String strChildWindowName;
	WebDriverWait wdWait;
	
	public SM_Home_Page(WebDriver wd) {
		// TODO Auto-generated constructor stub
		this.wd=wd;
		wdWait= new WebDriverWait(wd,10);
	}
	
	
	public void clickPlayNow(){
	
		//Actions acClick = new Actions(wd);
		//			//.click()
					//.build();
		//ac.perform();
		wd.findElement(btnPlayNow).click();
		
		
	}
	
	public void fbLogin(String uID,String password){
		wd.findElement(btnLogin).click();
		
		strWindowName= wd.getWindowHandle();
		wd.findElement(fbLink).click();
		windowNames= wd.getWindowHandles();
		windowNameIterator= windowNames.iterator();
		
		while(windowNameIterator.hasNext()){
			strChildWindowName=windowNameIterator.next();
			if(!strWindowName.equalsIgnoreCase(strChildWindowName)){
				wd.switchTo().window(strChildWindowName);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				wd.findElement(txtEmailID).sendKeys(uID);
				wd.findElement(strPassword).sendKeys(password);
				wd.findElement(btnFbLogin).click();
			}
			
		}
		
		wd.switchTo().window(strWindowName);
		
	}
	
	
	
	public void normalLogin(String uID,String password) {
		
		wd.findElement(txtEmailID_normal).sendKeys(uID);
		wd.findElement(strPassword_normal).sendKeys(password);
		wd.findElement(btnFbLogin_normal).click();
	}
	
	public void ClubSearchPageNavigation() {
		try {
		wd.findElement(btnPlayNow).click();
		//wdWait.until(ExpectedConditions.visibilityOfElementLocated(goldmanagement_modal_Dismiss)).click();
		
		for(int index=0;index<20;index++) {
			try {
				Thread.sleep(3000);
				wdWait.until(ExpectedConditions.visibilityOfElementLocated(goldmanagement_modal_Dismiss)).click();
				break;
			} catch (Exception e) {
				wd.navigate().refresh();
			}
				
		}
		
		
		//wd.findElement(goldmanagement_modal_Dismiss).click();
		wd.findElement(freemanagement_modal_Dismiss).click();
		wd.findElement(SM2022_modal_Dismiss).click();
		}catch(Exception ex) {}
		wd.findElement(btnAddClub).click();
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(btnClubSearch)).click();
	}
	
	public void addClubNavigation() {
		
		try {wd.findElement(btnAddClub).click();}catch(Exception ex) {}
	}
		
}
