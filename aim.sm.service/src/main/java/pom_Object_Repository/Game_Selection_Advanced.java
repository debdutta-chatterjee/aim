package pom_Object_Repository;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sun.corba.se.spi.orbutil.fsm.Action;

public class Game_Selection_Advanced {
	
	WebDriver wd;
	WebDriverWait wdWait;


	public Game_Selection_Advanced(WebDriver wd) {
		this.wd = wd;
		wdWait = new WebDriverWait(wd, 7);
		}
	
	// xpath
	String advanced_GWType = "//div[@id='currentScreenA']//select[@id='addClub-GameWorldAdvSearch-Types']";
	String advanced_GWSeason = "//div[@id='currentScreenA']//select[@id='addClub-GameWorldAdvSearch-Season']";
	String advanced_GWClubTaken = "//div[@id='currentScreenA']//select[@id='addClub-GameWorldAdvSearch-ClubTaken']";
	String advanced_GWSearchBtn = "//div[@id='currentScreenA']//div[@id='addClub-AdvGameWorldSearch-searchBtn']";
	String advanced_GWClubName = "//div[@id='currentScreenA']//input[@id='addClub-searchInput']";
	// String advanced= "//a[contains(text(),'Manchester United')]";

	String advanced_GWSearchResultsTable = "// div[@id='currentScreenA']//table[@id='addClub-AdvGameWorldSearch-table']";
	String advanced_GWResultsRow="//div[@id='currentScreenA']//table[@id='addClub-AdvGameWorldSearch-table']/tbody/tr[@onclick]";
	String advanced_GWNext="//li[@id='nextSearchRes_click']/a[not(@class='disabled')]";
			
			
	public void advance_SearchClub(String strType,String strClub,String strSeason,String strClubsTaken) {
		
		new Select(wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(advanced_GWType)))).selectByVisibleText(strType);
		new Select(wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(advanced_GWSeason)))).selectByVisibleText(strSeason);
		new Select(wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(advanced_GWClubTaken)))).selectByVisibleText(strClubsTaken);
		
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(advanced_GWClubName))).sendKeys(strClub);
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'"+strClub+"')]"))).click();
		
		
		
		JavascriptExecutor executor = (JavascriptExecutor)wd;
		executor.executeScript("arguments[0].click();", wd.findElement(By.xpath(advanced_GWSearchBtn)));
		executor.executeScript("window.scrollBy(0,700)", "");
		
		new Actions(wd).moveToElement(wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(advanced_GWSearchBtn))))
		.click().build().perform();
		
		//wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(advanced_GWSearchBtn))).click();
		
		wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(advanced_GWSearchResultsTable)));
		
	}
	
	public List<String> collectGWNames(String strType) {
		
		int resultSize=wd.findElements(By.xpath(advanced_GWResultsRow)).size();
		List<String> clubList= new ArrayList<String>();
		String clubname="";
		JavascriptExecutor executor;
		
		System.out.println("*************************************************************");
		while(wd.findElements(By.xpath(advanced_GWNext)).size()>0) {
			resultSize=wd.findElements(By.xpath(advanced_GWResultsRow)).size();
			System.out.println(resultSize);
			for(int index=1;index<=resultSize;index++) {
				try {
					clubname=wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(advanced_GWResultsRow+"["+index+"]"))).getText();
				} catch (Exception e) {
					break;
				}
				clubname=clubname.replace(strType, "").trim().replace(" ", ",");
				clubList.add(clubname);
				System.out.println(clubname);
				clubname="";
			}
			try {
				wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(advanced_GWNext))).click();
			} catch (Exception e1) {
				break;
			}
			executor = (JavascriptExecutor)wd;
//			try {
//				executor.executeScript("argumnts[0].click();", wd.findElement(By.xpath(advanced_GWNext)));
//			} catch (Exception e1) {
//				break;
//			}
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			System.out.println("*************************************************************");
			
		}
		
		return	clubList;
	}
	
	
	
	
	
	

}
