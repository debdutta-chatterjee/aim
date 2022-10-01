package sm.api.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter @JsonIgnoreProperties("ManagerRepData")
public class DivisionData {
	private int currentSeasonNum;
    private int currentTurnNum;
    private ArrayList<Integer> clubId;
    private ArrayList<Integer> clubDataId;
    private ArrayList<Object> clubName;
    private ArrayList<Object> clubLogo;
    @JsonProperty("ManagerName") 
    private ArrayList<Object> managerName;
    @JsonProperty("ManagerRep") 
    private ArrayList<Object> managerRep;
    //@JsonProperty("ManagerRepData") 
    //@JsonIgnore @JsonIgnoreProperties("ManagerRepData")
    //private ArrayList<ManagerRepDatum> managerRepData;
    @JsonProperty("ManagerPic") 
    private ArrayList<Object> managerPic;
    private ArrayList<Object> clubHomeColour1;
    private ArrayList<Object> countryId;
    private Object clubsUnderOffer;
    private Object clubsReserved;
    private int totalClubCount;
    private int isManagedTotal;
    private ArrayList<Integer> isManaged;
    private ArrayList<Object> clubBalance;
    private ArrayList<Object> clubValue;
    private ArrayList<Double> clubAveRating;
    private ArrayList<Integer> playerCount;
    private ArrayList<Integer> clubTopPlayers;
    private ArrayList<ArrayList<Object>> squadForename;
    private ArrayList<ArrayList<Object>> squadSurname;
    private ArrayList<ArrayList<Object>> squadRating;
    private ArrayList<ArrayList<Integer>> squadValue;
}
