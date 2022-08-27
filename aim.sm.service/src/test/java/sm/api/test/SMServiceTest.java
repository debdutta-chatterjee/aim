package sm.api.test;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import sm.api.pojo.ClubSearchResponseJson;
import sm.api.pojo.GWRecordsJson;
import sm.api.pojo.GWSearchResponseJson;
import sm.api.pojo.SearchOutputJson;


public class SMServiceTest {
	
	static String path;
	
	@Test
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
	}
	
	
	@Test(enabled=false)
	public void parseGWRecords() {
		path="./output/gw_records-2022-08-27_132148.json";
		GWRecordsJson objGWRecords=SMTestHelper.deserializeJson(path, GWRecordsJson.class);

		System.out.println(objGWRecords.getGwrecord().size());
		
		for(int setupId:objGWRecords.getGwrecord()) {
			
			System.out.println(setupId);
		}
		
	}
	
	
	
	@Test(enabled=false)
	public void test1() {
		
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
	
	
	@Test(enabled=false)
	public void test2() {
		
		Response response= SMTestHelper.openClub(0,0);
		Assert.assertEquals(response.statusCode(), HttpStatus.SC_OK,"Request Failed");
		
		
	}
	
}
