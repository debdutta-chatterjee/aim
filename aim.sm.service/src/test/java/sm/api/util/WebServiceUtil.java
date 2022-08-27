package sm.api.util;

import sm.api.pojo.ClubSearchResponseJson;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class WebServiceUtil {
	
	static ObjectMapper mapper = new ObjectMapper();
	
	//public <T> deserialize(T){retrun T;}
	
	public static <T> T deserialize(String path,Class<T> classType) throws StreamReadException, DatabindException, IOException {
		return mapper.readValue(new File(path), classType);
	}
		
	public static String getCookie() {
		
		String cookie ="ga=GA1.2.1657188083.1650376235; __auc=a84a71b61804217db5b765f73e0; _fbp=fb.1.1650376311787.855847968; __utmz=57106600.1650376429.2.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not provided); cookieconsent_status=dismiss; __gads=ID=d8c344c7fc51cdf4-2217ebda5ad20060:T=1650429500:RT=1650429500:S=ALNI_Ma1bK1AQFv5IXqR0b5YcjWTNTWjhw; interface2=1; PHPSESSID=drruq1p09bl4l7j1l8gjp40proc6quvc; _gid=GA1.2.156364359.1661010275; __utma=57106600.1657188083.1650376235.1657462999.1661010282.11; __utmc=57106600; smuid=4617948; smlgin=1; __icuc7=4617948; __smvs=1660950000_-_en_EN_-_member; __asc=99fcd6b7182bbef231988f18635; __utmb=57106600.6.10.1661010282; freeCGW=true; arenaPopupLive4=true; arenaPopupCounter4=2";
		return cookie;
		
		//logic to be implemented
	}
		
	public static String serializeToString(Collections objCollection) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        
       return  mapper.writeValueAsString(objCollection);
	}
	
	public static JSONObject getJsonObjet(String key, Collection objCollection) {
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(key, objCollection);
		
		return jsonObject;
		
	}
	
	
}
