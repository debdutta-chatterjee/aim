package sm.api.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class GWSearchResponseJson {
	
	    public int newUser;
	    public int setupState;
	    public Object setupStateMsg;
	    public int setupId;
	    public int ltid;
	    public int sgw;
	    public String setupName;
	    public GwData gwData;
	    public String rcPriceCredits;
	    public String currency;
	    public double rcPriceCash;
	    public String rcPrice;
	    public String rcMsg;
	    public int rep;
	    public int isGM;
	    public int goldDays;
	    public int credits;
	    public Object gwFriends;
	    public int numGWFriends;
	}

