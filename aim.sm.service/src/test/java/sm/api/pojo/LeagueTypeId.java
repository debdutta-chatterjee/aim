package sm.api.pojo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class LeagueTypeId {

	@JsonProperty("ENG") 
    public ArrayList<Integer> leagueTypeId;

}
