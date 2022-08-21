package sm_Automation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import pom_Object_Repository.Game_Selection_Advanced;
import pom_Object_Repository.Game_Selection_Page;
import pom_Object_Repository.SM_Home_Page;
import pom_Object_Repository.SM_World_Page;
import sm.Util.BrowserUtil;
import sm.Util.FileActions;
import sm.Util.commonUtil;

public class SM_Automation_Solution {

	public WebDriver wdMain;
	WebDriverWait wdWait;
	WebElement we;
	List<String> clubList_Master = new ArrayList<String>();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	FileActions fileOps = new FileActions();
	BrowserUtil objBrowserUtil = new BrowserUtil();
	commonUtil objCommonUtil = new commonUtil();

	
	@Parameters({ "url", "username", "password", "type", "clubname", "season", "clubtaken", "typename","gw_outputfile" })
	@Test(priority = 0, groups = "GW_Search")
	public void homePageNavigation(String url, String username, String password, String type, String clubname,
			String season, String clubtaken, String typename,String gw_outputfile) {

		System.out.println("Start Time" + formatter.format(new Date()));
		this.navigation_to_searchPage(url, username, password);

		Game_Selection_Advanced objGame_Selection_Advanced = new Game_Selection_Advanced(wdMain);
		objGame_Selection_Advanced.advance_SearchClub(type, clubname, season, clubtaken);
		List<String> clubList = objGame_Selection_Advanced.collectGWNames(typename);

		fileOps.write_DTS_ToFile("#####" + formatter.format(new Date()) + "####"+"Epoch Point-"+Instant.now().toEpochMilli(),gw_outputfile);

		System.out.println("*************************************************************");
		if (clubList.size() >= 1) {
			fileOps.writeToFile_ignoring_duplicates(clubList, gw_outputfile);
		}
		System.out.println("*************************************************************");

		System.out.println("End Time-" + formatter.format(new Date()));
	}

	

	
	
	@Parameters({ "url", "username", "password", "type", "clubname", "season", "clubtaken", "typename","club_outputfile","minbal","totVal","epochpoint","gw_outputfile","minimumSearchCriteria_ClubsTaken","minimumSelectionCriteria_ClubsTaken","maxSearchCriteria_ClubsTaken" })
	@Test(priority = 0, groups = "clubsearch")
	public void find_club_from_list(String url, String username, String password, String type, String clubname,
			String season, String clubtaken, String typename,String club_outputfile,String minbal,String totVal,String epochpoint,String gw_outputfile,String minSearch_ClubsTaken,String minSelection_ClubsTaken, String maxSearch_ClubsTaken) {

		System.out.println("Start Time" + formatter.format(new Date()));
				
		String output_clubsearch_file=club_outputfile.split("[.]")[0]+"_"+epochpoint+"."+club_outputfile.split("[.]")[1];
		
		this.navigation_to_searchPage(url, username, password);

		SM_World_Page objSMWPage = new SM_World_Page(wdMain);
		objSMWPage.selectCountry(type);
		
		boolean flag=false;
		
		for (String record : fileOps.readFile(gw_outputfile)) {
			int wait=1;
			
			
			if(record.contains(epochpoint)) {flag=true;}
			else if(flag==false) {
				//System.out.println("["+wait+"]Reaching epochpoint-"+epochpoint+". Please wait!-"+record);
				wait++;
				continue;
			}
			if(record.contains("###")) {continue;}
			
			
			String strIndex = objCommonUtil.getListItemFromCSV(record, 0);
			String strSeason = objCommonUtil.getListItemFromCSV(record, 1);
			String strClubTaken = objCommonUtil.getListItemFromCSV(record, 2);
			int intMinSearch_ClubsTaken=Integer.parseInt(minSearch_ClubsTaken);
			int intMaxSearch_ClubsTaken=Integer.parseInt(maxSearch_ClubsTaken);
			
			
			
			System.out.println("*********************[GW]" + strIndex + "*************[ClubsTaken]"+Integer.parseInt(strClubTaken)+"*********************");//+intMinSearch_ClubsTaken);
			if(Integer.parseInt(strClubTaken)<intMinSearch_ClubsTaken || Integer.parseInt(strClubTaken)>intMaxSearch_ClubsTaken) {
				//System.out.println("Skipped");
				//System.out.println("*************************************************************");
				continue;
				
			}
			
			objSMWPage.searchGameWorld(strIndex);

			int retVal = objSMWPage.clickGameWorld(strIndex);

			if (retVal == 0) {
				List<String> clubList = objSMWPage.readWorldData(Double.valueOf(minbal), strIndex, Double.valueOf(totVal),strClubTaken,strSeason,minSelection_ClubsTaken);
				if (clubList.size() >= 1) {
					clubList_Master.addAll(clubList);
					//fileOps.writeToFile_ignoring_duplicates(clubList, club_outputfile);
					fileOps.writeToFile_ignoring_duplicates(clubList, output_clubsearch_file);
				}
			}

			objSMWPage.goBackToSearch();
			System.out.println("*************************************************************");
		}
		System.out.println("End Time" + formatter.format(new Date()));

	}
	
	
	@Parameters({ "Mainfile","Cleanedfile" })
	@Test(priority = 0, groups = "FileCleanup")
	public void cleanupfile_remove_duplicates(String input_file,String output_file) {
		objCommonUtil.removeDuplicateFromFile(input_file,output_file);
	}
	
	
	
	
	
	public void navigation_to_searchPage(String url, String username, String password) {
		// Launch page & login - navigate to GW search
		wdMain.get(url);
		SM_Home_Page objHomePage = new SM_Home_Page(wdMain);
		objHomePage.normalLogin(username, password);
		objHomePage.ClubSearchPageNavigation();
	}

	@BeforeTest(groups = {"GW_Search","clubsearch"})
	@Parameters({ "browser" })
	public void beforeTest(@Optional("Chrome") String browserName) {

		switch (browserName) {
		case "IE":
			wdMain = objBrowserUtil.Launchchrome();
			break;
		case "Firefox":
			wdMain = objBrowserUtil.Launchchrome();
			break;
		case "Chrome":
			wdMain = objBrowserUtil.Launchchrome();
			break;
		default:
			wdMain = objBrowserUtil.Launchchrome();
		}

	}
	
	@AfterTest
	public void afterTest() {
		// wdMain.quit();
	}

}
