package sm.api.pojo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class GwData{
    public int selectedLeagueTypeId;
    public String selectedCountryId;
    public ArrayList<String> leagueName;
    @JsonProperty("AccessStart") 
    public Object accessStart;
    public int cashTransfers;
    public int setupReputation;
    public int isTest;
    public Object isFreeCustom;
    public String vShort;
    public int access;
    public String accessDesc;
    public int ownerId;
    public String ownerName;
    public int creatorId;
    public Object creatorName;
    public String setupType;
    public int setupRating;
    public int setupTypeId;
    public String dateOpening;
    public int setupFormat;
    public Object description;
    public String setupDesc;
    public Object chairman;
    public Object endControl;
    public Object unmanagedBuying;
    public Object externalBuying;
    public Object ageBuyingLimit;
    public Object ratingBuyingLimit;
    public Object cashBuyingLimit;
    public Object tw1TurnStart;
    public Object tw1TurnEnd;
    public Object tw2TurnStart;
    public Object tw2TurnEnd;
    public Object buyingClubNationality;
    public Object managerSackedPosition;
    public Object loginDays;
    public Object draftPlayers;
    @JsonProperty("EconomyLevel") 
    public Object economyLevel;
    public Object playerAI;
    public String loginDaysText;
    public String managerSackedText;
    @JsonProperty("EconomyLevelText") 
    public String economyLevelText;
    public int custAlreadyInSetup;
    public int clubsUnderOfferCount;
    public LeagueTypeId leagueTypeId;
    public DivisionName divisionName;
    public Division division;
    public ArrayList<Object> countryName;
    public int totalClubCount;
    public ArrayList<Integer> setupClubArray;
    public ArrayList<Integer> leagueId;
    public int isManagedTotal;
    public int currentSeasonNum;
    public int currentTurnNum;
    public ArrayList<String> clubHomeColour1;
    public ClubData clubData;
}

