package sm.api.pojo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GWRecordsJson {
	@JsonProperty("gw_records") 
    private ArrayList<Integer> gwrecord;
}
