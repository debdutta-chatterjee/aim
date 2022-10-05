package sm.api.pojo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter @JsonIgnoreProperties("CUS")
public class LeagueTypeId {

	//@JsonProperty("ENG") 
	@JsonAlias({ "ENG", "ESP" })
    public ArrayList<Integer> leagueTypeId;

}
