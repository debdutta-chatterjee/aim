package sm.api.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Game_Data {
	
	@Test
	public void getGameData() {
		String cookie ="_ga=GA1.2.1657188083.1650376235; __auc=a84a71b61804217db5b765f73e0; interface2=2; _fbp=fb.1.1650376311787.855847968; __utmz=57106600.1650376429.2.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); cookieconsent_status=dismiss; __gads=ID=d8c344c7fc51cdf4-2217ebda5ad20060:T=1650429500:RT=1650429500:S=ALNI_Ma1bK1AQFv5IXqR0b5YcjWTNTWjhw; PHPSESSID=thmhrjr050knhqauvhvpuqqh5btahc85; _gid=GA1.2.213575639.1654236966; __utma=57106600.1657188083.1650376235.1652445924.1654236968.7; __utmc=57106600; smuid=4617948; smlgin=1; __icuc7=4617948; __smvs=1654210800_-_en_EN_-_member; freeCGW=true; arenaPopupLive4=true; arenaPopupCounter4=2; __utmt_UA-488419-6=1; _gat=1; _gat_UA-488419-6=1; __utmb=57106600.18.10.1654236968";
		String sid;
		String sName;
		String sClubsTaken;
		
			RequestSpecification objRequest= RestAssured.given()
			.baseUri("https://www.soccermanager.com")
			.header("content-type","application/json")
			.header("cookie",cookie);
				
			
		Response objResponse= objRequest.request(Method.GET,"/club-add-ajax.php?action=sgwname&setuptypeid=1&name=10758");
			
		JsonPath objJson=objResponse.jsonPath();
		sid=objJson.getString("searchResults[0].setupId");
		sName=objJson.getString("searchResults[0].setupName");
		sClubsTaken=objJson.getString("searchResults[0].clubsTaken");
		
		
		//Get Club Details
		objRequest= RestAssured.given()
				.baseUri("https://www.soccermanager.com")
				.header("content-type","application/json")
				.header("cookie",cookie);
		objResponse= objRequest.request(Method.GET,"/club-add-ajax.php?action=setup&sid="+sid);
		objJson=objResponse.jsonPath();
		System.out.println(objResponse.asString());
		System.out.println(objJson.get("gwData.clubData.ENG.42895.clubId[0]").toString());
		
		
	}
	
	@Test(enabled=false)
	public void getGameData_2() {
		String cookie ="_ga=GA1.2.1657188083.1650376235; __auc=a84a71b61804217db5b765f73e0; interface2=2; _fbp=fb.1.1650376311787.855847968; __utmz=57106600.1650376429.2.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); cookieconsent_status=dismiss; __gads=ID=d8c344c7fc51cdf4-2217ebda5ad20060:T=1650429500:RT=1650429500:S=ALNI_Ma1bK1AQFv5IXqR0b5YcjWTNTWjhw; PHPSESSID=thmhrjr050knhqauvhvpuqqh5btahc85; _gid=GA1.2.213575639.1654236966; __utma=57106600.1657188083.1650376235.1652445924.1654236968.7; __utmc=57106600; smuid=4617948; smlgin=1; __icuc7=4617948; __smvs=1654210800_-_en_EN_-_member; freeCGW=true; arenaPopupLive4=true; arenaPopupCounter4=2; __utmt_UA-488419-6=1; _gat=1; _gat_UA-488419-6=1; __utmb=57106600.18.10.1654236968";
		String sid;
		String sName;
		String sClubsTaken;
		
			RequestSpecification objRequest= RestAssured.given()
			.baseUri("https://www.soccermanager.com")
			.header("content-type","application/json")
			.header("cookie",cookie);
				
			
		Response objResponse= objRequest.request(Method.GET,"/club-add-ajax.php?action=sgwname&setuptypeid=1&name=10758");
			System.out.println(objResponse.getBody().asPrettyString());
		JsonPath objJson=objResponse.jsonPath();
		sid=objJson.getString("searchResults[0].setupId");
		sName=objJson.getString("searchResults[0].setupName");
		sClubsTaken=objJson.getString("searchResults[0].clubsTaken");
		
		
		//Get Club Details
		objRequest= RestAssured.given()
				.baseUri("https://www.soccermanager.com")
				.header("content-type","application/json")
				.header("cookie",cookie);
		objResponse= objRequest.request(Method.GET,"/club-add-ajax.php?action=setup&sid="+sid);
		objJson=objResponse.jsonPath();
		System.out.println(objResponse.asString());
		
		String selectedLeagueTypeId=objJson.get("gwData.selectedLeagueTypeId");
		int size=objJson.getList("gwData.clubData.ENG."+selectedLeagueTypeId+".clubId").size();
		
		String str_clubId;
		String str_clubName;
		String str_isManaged;
		String str_clubBalance;
		String str_clubValue;
		String str_clubAveRating;
		String str_playerCount;
		List<DataStore> lst_data= new ArrayList<DataStore>();
		//https://www.soccermanager.com/club-add-ajax.php?action=sgwadv&setuptypeid=1&cdid=0&access=1&season=8&clubstaken=0
		for(int i=0;i<size;i++) {
			
			str_clubId=objJson.get("gwData.clubData.ENG."+selectedLeagueTypeId+".clubId["+i+"]");
			str_clubName=objJson.get("gwData.clubData.ENG."+selectedLeagueTypeId+".clubName["+i+"]");
			str_isManaged=objJson.get("gwData.clubData.ENG."+selectedLeagueTypeId+".isManaged["+i+"]");
			str_clubBalance=objJson.get("gwData.clubData.ENG."+selectedLeagueTypeId+".clubBalance["+i+"]");
			str_clubValue=objJson.get("gwData.clubData.ENG."+selectedLeagueTypeId+".clubValue["+i+"]");
			str_clubAveRating=objJson.get("gwData.clubData.ENG."+selectedLeagueTypeId+".clubAveRating["+i+"]");
			str_playerCount=objJson.get("gwData.clubData.ENG."+selectedLeagueTypeId+".playerCount["+i+"]");
			
			
			double balance=Double.parseDouble(str_clubBalance.substring(0, str_clubBalance.length()));
			double value=Double.parseDouble(str_clubValue.substring(0, str_clubValue.length()));
			double worth=balance+value;
			
			if(worth>=900) {
				lst_data.add(new DataStore(str_clubId,str_clubName,str_isManaged,str_clubBalance,str_clubValue,str_clubAveRating,str_playerCount));
			}
		
		}
		
	}
	
}
