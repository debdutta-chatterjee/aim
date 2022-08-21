package sm.api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ManagerRepDatum {
	@JsonProperty("LevelText") 
    public String levelText;
    @JsonProperty("Badge") 
    public String badge;
}
