package sm.api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class League{
    @JsonProperty("1") 
    public DivisionData div1;
    @JsonProperty("2") 
    public DivisionData div2;
    @JsonProperty("3") 
    public DivisionData div3;
    @JsonProperty("4") 
    public DivisionData div4;
}
