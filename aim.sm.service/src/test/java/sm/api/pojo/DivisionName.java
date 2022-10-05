package sm.api.pojo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class DivisionName{
   // @JsonProperty("ENG") 
    @JsonAlias({ "ENG", "ESP" })
    public ArrayList<String> divisionName;
}
