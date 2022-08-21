package sm.api.test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.restassured.response.Response;
import sm.api.endpoints.SMEndpoints;
import sm.api.pojo.DivisionData;
import sm.api.pojo.GWSearchResponseJson;
import sm.api.pojo.SearchOutputJson;
import sm.api.util.APIRequest;
import sm.api.util.GenericUtil;
import sm.api.util.WebServiceUtil;

public class SMTestHelper {
	
	public static Response getGameDataResponse(int season,int clubsTaken,int offset){
		
		String endpoint = SMEndpoints.advGWSearch(season, clubsTaken, offset);
		return APIRequest.getRequest(SMEndpoints.getBaseUri(), WebServiceUtil.getCookie(), endpoint);
	}
	
	public static Response getSelectGW(int setupId){
		
		String endpoint = SMEndpoints.gwSelect(setupId);
		return APIRequest.getRequest(SMEndpoints.getBaseUri(), WebServiceUtil.getCookie(), endpoint);
	}
	
	
	public static Response openClub(int clubId,int setupId){
		
		String endpoint = SMEndpoints.openClub(clubId,setupId);
		return APIRequest.getRequest(SMEndpoints.getBaseUri(), WebServiceUtil.getCookie(), endpoint);
	}
	
	public static SearchOutputJson getGWData(GWSearchResponseJson objGWResponse) {

		//GW details
		int setupId= objGWResponse.setupId;
		String setupName=objGWResponse.setupName;
		String selectedCountryId=objGWResponse.gwData.selectedCountryId;
		String setupType=objGWResponse.gwData.getSetupType();
		String economyLevelText=objGWResponse.gwData.getEconomyLevelText();
		
		//Club details
		DivisionData div1Data=objGWResponse.gwData.clubData.getDivisionContainer().getDiv1Data().entrySet().iterator().next().getValue();
				
		List<String> clubName=div1Data.getClubName().stream().map((club) -> club.toString()).collect(Collectors.toList());
		List<Double> clubBalance=div1Data.getClubBalance().stream().map(value -> GenericUtil.getAmount(value.toString())).collect(Collectors.toList());
		List<Double> clubValue=div1Data.getClubValue().stream().map(value -> GenericUtil.getAmount(value.toString())).collect(Collectors.toList());
		List<Integer> playerCount=div1Data.getPlayerCount();
		List<Double> clubAveRating=div1Data.getClubAveRating();
		int isManagedTotal=div1Data.getIsManagedTotal();
		int currentSeasonNum=div1Data.getCurrentSeasonNum();
		List<Integer> isManaged=div1Data.getIsManaged();
		
		return new SearchOutputJson(setupId, setupName, selectedCountryId, setupType,
		economyLevelText,clubName,clubBalance,clubValue,playerCount,
		clubAveRating,isManagedTotal,currentSeasonNum,isManaged);
	}

}
