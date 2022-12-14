package sm.api.pojo;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import sm.api.pojo.DivisionData;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class DivisionContainer{
	
	private Map<String, DivisionData> div1Data = new HashMap<>();
	
	@JsonAnySetter
    public void setDiv1(String name, Object value) {
	
		DivisionData d=new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true).convertValue(value, DivisionData.class);
		div1Data.put(name, d);
    }
	
}		
//	@JsonAlias({ "27762" })
//	public DivisionData division1;
//	
//
//	@JsonAlias({ "27763" })
//	    public DivisionData division2;
	
	
	
