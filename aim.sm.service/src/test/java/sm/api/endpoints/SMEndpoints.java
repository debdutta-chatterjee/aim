package sm.api.endpoints;

public class SMEndpoints {
	
	private static String  baseUri= "https://www.soccermanager.com";
	
	public static String advGWSearch(int season,int clubsTaken,int offset) {
		
		return "/club-add-ajax.php?action=sgwadv&setuptypeid=1&cdid=21&access=1&season="+season+"&clubstaken="+clubsTaken+"&offset="+offset;
	}
	
	public static String getBaseUri() {return baseUri;}
	
	public static String gwSelect(int setupId) {
		return "club-add-ajax.php?action=setup&sid="+setupId;
	}
	
	
	public static String openClub(int clubId,int setupId) {
		
		return "/multiplayer.php?action=setclub&clubid="+clubId+"sid="+setupId+"&xgw=0";
	}
}
