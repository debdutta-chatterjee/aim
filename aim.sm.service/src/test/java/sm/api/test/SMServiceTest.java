package sm.api.test;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import sm.api.pojo.ClubSearchResponseJson;
import sm.api.pojo.GWRecordsJson;
import sm.api.pojo.GWSearchResponseJson;
import sm.api.pojo.OutputContainer;
import sm.api.pojo.SearchOutputJson;


public class SMServiceTest {
	
	static String path;
	GWRecordsJson objGWRecords=null;
	
	@Test(enabled=false)
	public void storeAllGameworld() {
		
		Set<Integer> setSetupId = new HashSet<Integer>();
		int offsetLimit=4000;
		
		try {
			for(int offset=0;offset<=offsetLimit;offset=offset+20) {
				
				System.out.println("OFFSET-"+offset);
				Response response= SMTestHelper.getGameDataResponse(0,0,offset);
				
				Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,"Request Failed");
				ClubSearchResponseJson objResponse=response.as(ClubSearchResponseJson.class);
				setSetupId.addAll(objResponse.getSearchResults().stream().map(p -> p.getSetupId()).collect(Collectors.toSet()));
			}
		} catch (Exception e) {}
		
		System.out.println("Record Count-"+setSetupId.size());
		path=SMTestHelper.writeJson("gw_records", setSetupId);
		System.out.println("Setup Ids stored at- "+path);
	}
	
	
	@Test(priority=1,enabled=false)
	public void parseGWRecords() {
		path="./output/gw_records-2022-10-01_214741.json";
		objGWRecords=SMTestHelper.deserializeJson(path, GWRecordsJson.class);

		System.out.println("GW size-"+objGWRecords.getGwrecord().size());
		
//		for(int setupId:objGWRecords.getGwrecord()) {
//			
//			System.out.println(setupId);
//		}
		
	}
	
	
	
	@Test(enabled=false)
	@Deprecated
	public void GetClubs_old() {
		
		Set<SearchOutputJson> outputSet = new HashSet<SearchOutputJson>();
		
		Response response= SMTestHelper.getGameDataResponse(0,0,0);
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,"Request Failed");
		ClubSearchResponseJson objResponse=response.as(ClubSearchResponseJson.class);
		
		int stupId=objResponse.getSearchResults().get(0).getSetupId();
		response= SMTestHelper.getSelectGW(stupId);
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,"Request Failed");
		
		//System.out.println(response.getBody().asString());
		
		GWSearchResponseJson objGWResponse=response.as(GWSearchResponseJson.class);
		
		outputSet.add(SMTestHelper.getGWData(objGWResponse));
		
		
		System.out.println(outputSet.toString());
		
	}
	
	@Test(priority=2,enabled=true)
	public void GetClubs() {
		
		path="./output/gw_records-2022-10-01_214741.json";
		objGWRecords=SMTestHelper.deserializeJson(path, GWRecordsJson.class);
		
		List<SearchOutputJson> outputSet = new ArrayList<SearchOutputJson>();
		
		Response response=null;
		double thresold= 750;
		int countTracker=0;
		SearchOutputJson output =null;
		
			for(int setupId:objGWRecords.getGwrecord()) {
				System.out.print(++countTracker);
				try {
				response= SMTestHelper.getSelectGW(setupId); 
				//System.out.println(response.asString());
				Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,"Request Failed");
				
				GWSearchResponseJson objGWResponse=response.as(GWSearchResponseJson.class);			
				output = SMTestHelper.getGWData(objGWResponse);
				outputSet.add(output);
				//System.out.println("Scanned-"+setupId);
				output.displayAll(output.getIndex(thresold));
				
			}catch (Exception e) {System.out.println("Skipped-"+setupId);}
		} 
		
			System.out.println("Record Count-"+outputSet.size());
			path=SMTestHelper.writeJson("club_records", outputSet);
			System.out.println("outputSets stored at- "+path);
	}
	
	@Test(priority=2,enabled=false)
	public void FilterClubs() {
		
		Set<SearchOutputJson> outputSet = new HashSet<SearchOutputJson>();
		List<String> resultList= new ArrayList<>();
		Response response=null;
		double thresold= 1200;
		int countTracker=0;
		SearchOutputJson output =null;
		
		path="./output/club_records-2022-10-02_021457.json";
		OutputContainer objOutputContainer=SMTestHelper.deserializeJson(path, OutputContainer.class);
		
			for(GWSearchResponseJson objGWResponse:objOutputContainer.getLst_GWSearchResponseJson()) {
				output = SMTestHelper.getGWData(objGWResponse);
				outputSet.add(output);
				//System.out.println("Scanned-"+setupId);
				output.displayAll(output.getIndex(thresold));
		} 
	}
	
	@Test(enabled=false)
	public void OpenClub() {
		
		Response response= SMTestHelper.openClub(0,0);
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,"Request Failed");
		
		
	}
	
}
