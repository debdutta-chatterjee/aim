package sm.api.pojo;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClubSearchResponseJson {
	
	    public int newUser;
	    public Object search;
	    public int setupTypeId;
	    public Object errorMsg;
	    public Object searchMsg;
	    public ArrayList<SearchResult> searchResults;
	    public int rep;
	    public int isGM;
	    public int goldDays;
	    public String rcPriceCredits;
	    public String currency;
	    public double rcPriceCash;
	    public String rcPrice;
	    public String rcMsg;
	    public int access;
	    public int clubDataId;
	    public int offset;
	    public int gameWorldFindType;
	    public int numGameWorlds;
	    public int numRows;
	}

