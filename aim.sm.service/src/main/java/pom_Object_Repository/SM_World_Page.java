package pom_Object_Repository;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SM_World_Page {

	// @FindBy(xpath="//*[@id='addClub-ClubSearch-tab']")

	By btnClubSearch = By.xpath("//*[@id='addClub-ClubSearch-tab']");
	@FindBy(xpath = "//*[@id='addClub-GameWorldSearch-Types']")
	WebElement weCountry;

	@FindBy(xpath = "//*[@id='addClub-GameWorldSearch-GameWorldNames']")
	WebElement weSearch;

	@FindBy(xpath = "//*[@id='addClub-GameWorldSearchInput']/span/button/i")
	WebElement webtnSearch;

	@FindBy(xpath = "//h2[normalize-space(text())='Game World Name Search']")
	WebElement GWNameSearhc_Header;

	//By gameWorldEntry = By.xpath("//*[@id='addClub-GameWorldSearch-table']/tbody/tr[2]/td[2]");
	By gameWorldEntry = By.xpath("//div[@id='currentScreenA']//tr[@data-generated='true']/td[@data-name='GameWorldName']");
	// By btnBack=By.xpath("//*[@id='footerNavBar-club-backarrow']/i");
	By btnBack = By.xpath("//div[@id='currentScreenA']//*[@id='footerNavBar-club-backarrow']/i");

	WebElement wegameWorldEntry;

	By errorMessage = By.cssSelector("#playerpopup-header > button");
	By dtTable = By.xpath("//div[@id='currentScreenA']//table[@id='addClubTable']");
	List<WebElement> dtTableRows;
	List<WebElement> dtColumn;
	By noworldfound = By
			.xpath("//div[@id='modalB']//div[contains(text(),'No Game Worlds found')]/parent::*/parent::*//button");
	By unexpectedError = By
			.xpath("//div[@id='modalB']//div[contains(text(),'Sorry an unexpected error has occurred. Please try again')]/parent::*/parent::*//button");
	

	WebElement weTable;
	WebElement we;
	WebDriverWait wdWait;
	WebDriver wd;

	public SM_World_Page(WebDriver wd) {
		// TODO Auto-generated constructor stub
		this.wd = wd;
		wdWait = new WebDriverWait(wd, 7);
		PageFactory.initElements(wd, this);
	}

	public void clickClubSearch() {

		wdWait.until(ExpectedConditions.visibilityOfElementLocated(btnClubSearch)).click();
	}

	public void selectCountry(String country) {

		Select slCountry = new Select(weCountry);
		slCountry.selectByVisibleText(country);

	}

	public void searchGameWorld(String gameWorldNum) {

		try {
			wdWait.until(ExpectedConditions.visibilityOf(GWNameSearhc_Header));
		} catch (Exception e) {
			this.goBackToSearch();
		}
		weSearch.clear();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

		}
				try {
			Thread.sleep(3000);
			weSearch.sendKeys(gameWorldNum);

		} catch (Exception e) {
			
			try {
				wd.findElement(unexpectedError).click();
				wdWait.until(ExpectedConditions.invisibilityOfElementLocated(unexpectedError));
			} catch (Exception e1) {
				
			}
		}

		try {
			webtnSearch.click();
		} catch (Exception e) {
			
		}

	}

	public int clickGameWorld() {

		try {
			wdWait.until(ExpectedConditions.visibilityOfElementLocated(gameWorldEntry)).click();
			return 0;
		} catch (Exception e) {

			try {
				wd.findElement(errorMessage).click();
				wdWait.until(ExpectedConditions.invisibilityOfElementLocated(errorMessage));
			} catch (Exception e1) {
				try {
					wd.findElement(noworldfound).click();
					wdWait.until(ExpectedConditions.invisibilityOfElementLocated(noworldfound));
				} catch (Exception e2) {

				}
			}

			return 1;
		}

	}
	
	//div[@id='currentScreenA']//tr[@data-generated='true']/td[@data-name='GameWorldName']
	
	public int clickGameWorld(String gwID) {

		try {
			wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='currentScreenA']//tr[@data-generated='true']/td[@data-name='GameWorldName'][contains(text(),"+gwID+")]"))).click();
			return 0;
		} catch (Exception e) {

			try {
				wd.findElement(errorMessage).click();
				wdWait.until(ExpectedConditions.invisibilityOfElementLocated(errorMessage));
			} catch (Exception e1) {
				try {
					wd.findElement(noworldfound).click();
					wdWait.until(ExpectedConditions.invisibilityOfElementLocated(noworldfound));
				} catch (Exception e2) {

				}
			}

			return 1;
		}

	}
	

	public void goBackToSearch() {

		if (wd.findElements(By.xpath("//h2[normalize-space(text())='Game World Name Search']")).size() == 0) {
			try {
				wd.findElement(btnBack).click();
				wdWait.until(ExpectedConditions.visibilityOf(weCountry));
			} catch (Exception e) {

			}
		}
	}

	public List<String> readWorldData(double minimumBalance, String gameWorldNum) {

		String clubName;
		String balance;
		String totalValue;
		String rating;
		String numplayers;
		String retMessage;
		List<String> clubList = new ArrayList<String>();

		weTable = wd.findElement(dtTable);
		// dtTableRows=weTable.findElements(By.tagName("tr"));
		dtTableRows = weTable.findElements(By.xpath("tbody/tr[@id='addClub-helpers-row']"));
		// System.out.println("size"+dtTableRows.size());
		for (int i = 1; i < dtTableRows.size(); i++) {

			String xpathbase = "(//div[@id='currentScreenA']//table[@id='addClubTable']/tbody/tr[@id='addClub-helpers-row'])["
					+ i + "]/";

			clubName = this.getElementText(wd, xpathbase + "td[@data-name='clubName']/span");
			balance = this.getElementText(wd, xpathbase + "td[@data-name='balance']");
			totalValue = this.getElementText(wd, xpathbase + "td[@data-name='value']");
			numplayers = this.getElementText(wd, xpathbase + "td[@data-name='numplayers']");
			rating = this.getElementText(wd, xpathbase + "td[@data-name='rating']");
			
			if (totalValue.toUpperCase().contains("- ") && totalValue.toUpperCase().contains("K")) {
				totalValue = String.valueOf(Double.parseDouble(totalValue.toUpperCase().replace("- ", "").replace("K", "")) * -1/1000);
			}
			if (balance.toUpperCase().contains("- ") && balance.toUpperCase().contains("K")) {
				balance = String.valueOf(Double.parseDouble(balance.toUpperCase().replace("- ", "").replace("K", "")) * -1/1000);
			}
			

			if (totalValue.toUpperCase().contains("B")) {
				totalValue = String.valueOf(Double.parseDouble(totalValue.toUpperCase().replace("B", "")) * 1000);
			}
			if (balance.toUpperCase().contains("B")) {
				balance = String.valueOf(Double.parseDouble(balance.toUpperCase().replace("B", "")) * 1000);
			}
			
			if (totalValue.toUpperCase().contains("K")) {
				totalValue = String.valueOf(Double.parseDouble(totalValue.toUpperCase().replace("K", "")) / 1000);
			}
			if (balance.toUpperCase().contains("K")) {
				balance = String.valueOf(Double.parseDouble(balance.toUpperCase().replace("K", "")) / 1000);
			}
			
			if (totalValue.toUpperCase().contains("- ")) {
				totalValue = String.valueOf(Double.parseDouble(totalValue.toUpperCase().replace("- ", "").replace("M", "")) * -1);
			}
			if (balance.toUpperCase().contains("- ")) {
				balance = String.valueOf(Double.parseDouble(balance.toUpperCase().replace("- ", "").replace("M", "")) * -1);
			}

			if ((Double.parseDouble(totalValue.replace("M", ""))
					+ Double.parseDouble(balance.replace("M", "")) >= minimumBalance) || Double.parseDouble(totalValue.replace("M", ""))>399) {
				retMessage = gameWorldNum + "," + clubName + "," + balance + "," + totalValue + "," + numplayers + ","
						+ rating;
				double totalMoney=Double.parseDouble(totalValue.replace("M", ""))
						+ Double.parseDouble(balance.replace("M", ""));
				clubList.add(retMessage);
				System.out.println("[GW]"+gameWorldNum+"---"+clubName+"  [Money]"+totalMoney+"  [PlayerCount]"+numplayers+" [Ratings]"+rating);
			} else {

				retMessage = "no Good Club Found";
			}

			clubName = "";
			balance = "0";
			totalValue = "0";
			numplayers = "";
			rating = "";
			retMessage = "";

		}

		return clubList;

	}

	public String getElementText(WebDriver driver, String locator) {
		WebElement we = new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class)
				.until((WebDriver d) -> {
					return d.findElement(By.xpath(locator));

				});

		return we.getText();
	}

	public WebElement getElementRaw(WebDriver driver, String locator) {

		WebElement we = new WebDriverWait(driver, 10).ignoring(StaleElementReferenceException.class)
				.until((WebDriver d) -> {
					return d.findElement(By.xpath(locator));

				});

		return we;
	}
	
	
	
	
	public List<String> readWorldData(double minimumBalance, String gameWorldNum,double totalVal,String strClubTaken,String strSeason,String str_criteria_clubsTaken) {

		String clubName;
		String balance;
		String totalValue;
		String rating;
		String numplayers;
		String retMessage;
		List<String> clubList = new ArrayList<String>();

		weTable = wd.findElement(dtTable);
		// dtTableRows=weTable.findElements(By.tagName("tr"));
		dtTableRows = weTable.findElements(By.xpath("tbody/tr[@id='addClub-helpers-row']"));
		// System.out.println("size"+dtTableRows.size());
		for (int i = 1; i < dtTableRows.size(); i++) {

			String xpathbase = "(//div[@id='currentScreenA']//table[@id='addClubTable']/tbody/tr[@id='addClub-helpers-row'])["
					+ i + "]/";

			clubName = this.getElementText(wd, xpathbase + "td[@data-name='clubName']/span");
			balance = this.getElementText(wd, xpathbase + "td[@data-name='balance']");
			totalValue = this.getElementText(wd, xpathbase + "td[@data-name='value']");
			numplayers = this.getElementText(wd, xpathbase + "td[@data-name='numplayers']");
			rating = this.getElementText(wd, xpathbase + "td[@data-name='rating']");
			
			if (totalValue.toUpperCase().contains("- ") && totalValue.toUpperCase().contains("K")) {
				totalValue = String.valueOf(Double.parseDouble(totalValue.toUpperCase().replace("- ", "").replace("K", "")) * -1/1000);
			}
			if (balance.toUpperCase().contains("- ") && balance.toUpperCase().contains("K")) {
				balance = String.valueOf(Double.parseDouble(balance.toUpperCase().replace("- ", "").replace("K", "")) * -1/1000);
			}
			

			if (totalValue.toUpperCase().contains("B")) {
				totalValue = String.valueOf(Double.parseDouble(totalValue.toUpperCase().replace("B", "")) * 1000);
			}
			if (balance.toUpperCase().contains("B")) {
				balance = String.valueOf(Double.parseDouble(balance.toUpperCase().replace("B", "")) * 1000);
			}
			
			if (totalValue.toUpperCase().contains("K")) {
				totalValue = String.valueOf(Double.parseDouble(totalValue.toUpperCase().replace("K", "")) / 1000);
			}
			if (balance.toUpperCase().contains("K")) {
				balance = String.valueOf(Double.parseDouble(balance.toUpperCase().replace("K", "")) / 1000);
			}
			
			if (totalValue.toUpperCase().contains("- ")) {
				totalValue = String.valueOf(Double.parseDouble(totalValue.toUpperCase().replace("- ", "").replace("M", "")) * -1);
			}
			if (balance.toUpperCase().contains("- ")) {
				balance = String.valueOf(Double.parseDouble(balance.toUpperCase().replace("- ", "").replace("M", "")) * -1);
			}
			
			double coeff=Double.parseDouble(totalValue.replace("M", ""))+((Integer.parseInt(strClubTaken)-Integer.parseInt(str_criteria_clubsTaken))*15);
			//System.out.println("Coeff"+coeff+ " total value Input-"+totalVal );
			
			//(Double.parseDouble(totalValue.replace("M", ""))+((Integer.parseInt(strClubTaken)-Integer.parseInt(str_criteria_clubsTaken)*25))
			
			if ((Double.parseDouble(totalValue.replace("M", ""))
					+ Double.parseDouble(balance.replace("M", "")) >= minimumBalance) || (coeff >=totalVal && Integer.parseInt(strClubTaken)>= Integer.parseInt(str_criteria_clubsTaken))) {
				retMessage = gameWorldNum + "," + clubName + "," + balance + "," + totalValue + "," + numplayers + ","
						+ rating+","+strClubTaken+","+strSeason;
				double totalMoney=Double.parseDouble(totalValue.replace("M", ""))
						+ Double.parseDouble(balance.replace("M", ""));
				clubList.add(retMessage);
				System.out.println("[GW]"+gameWorldNum+"---"+clubName+" [Worth]"+totalMoney+"  [Money]"+balance+" [Value]"+totalValue+"  [PlayerCount]"+numplayers+" [Ratings]"+rating+" [Clubs Taken]"+strClubTaken+" [Season]"+strSeason);
			} else {

				retMessage = "no Good Club Found";
			}

			clubName = "";
			balance = "0";
			totalValue = "0";
			numplayers = "";
			rating = "";
			retMessage = "";

		}

		return clubList;

	}

}
