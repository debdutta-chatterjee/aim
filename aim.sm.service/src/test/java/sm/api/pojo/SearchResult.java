package sm.api.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SearchResult{
    @JsonProperty("VShort") 
    public String vShort;
    public int setupId;
    public int setupNumber;
    public int isTest;
    public String setupName;
    public String clubsTaken;
    public String season;
}