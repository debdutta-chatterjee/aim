package sm.api.test;


import java.util.HashSet;
import java.util.Set;

import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import sm.api.pojo.ClubSearchResponseJson;
import sm.api.pojo.GWSearchResponseJson;
import sm.api.pojo.SearchOutputJson;


public class SMServiceTest {
	
	@Test
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