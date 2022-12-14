package sm.api.test;

public class DataStore {
	private String str_clubId;
	
	private String str_clubName;
	private String str_isManaged;
	private String str_clubBalance;
	private String str_clubValue;
	private String str_clubAveRating;
	private String str_playerCount;
	
	
	public DataStore(String str_clubId, String str_clubName, String str_isManaged, String str_clubBalance,
			String str_clubValue, String str_clubAveRating, String str_playerCount) {
		super();
		this.str_clubId = str_clubId;
		this.str_clubName = str_clubName;
		this.str_isManaged = str_isManaged;
		this.str_clubBalance = str_clubBalance;
		this.str_clubValue = str_clubValue;
		this.str_clubAveRating = str_clubAveRating;
		this.str_playerCount = str_playerCount;
	}
	
	
	
	
	public String getStr_clubId() {
		return str_clubId;
	}
	public void setStr_clubId(String str_clubId) {
		this.str_clubId = str_clubId;
	}
	public String getStr_clubName() {
		return str_clubName;
	}
	public void setStr_clubName(String str_clubName) {
		this.str_clubName = str_clubName;
	}
	public String getStr_isManaged() {
		return str_isManaged;
	}
	public void setStr_isManaged(String str_isManaged) {
		this.str_isManaged = str_isManaged;
	}
	public String getStr_clubBalance() {
		return str_clubBalance;
	}
	public void setStr_clubBalance(String str_clubBalance) {
		this.str_clubBalance = str_clubBalance;
	}
	public String getStr_clubValue() {
		return str_clubValue;
	}
	public void setStr_clubValue(String str_clubValue) {
		this.str_clubValue = str_clubValue;
	}
	public String getStr_clubAveRating() {
		return str_clubAveRating;
	}
	public void setStr_clubAveRating(String str_clubAveRating) {
		this.str_clubAveRating = str_clubAveRating;
	}
	public String getStr_playerCount() {
		return str_playerCount;
	}
	public void setStr_playerCount(String str_playerCount) {
		this.str_playerCount = str_playerCount;
	}
	
	
}
