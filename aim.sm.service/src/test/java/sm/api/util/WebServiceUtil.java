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
		
		String cookie ="_ga=GA1.2.1657188083.1650376235; __auc=a84a71b61804217db5b765f73e0; _fbp=fb.1.1650376311787.855847968; __utmz=57106600.1650376429.2.2.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); cookieconsent_status=dismiss; __gads=ID=d8c344c7fc51cdf4-2217ebda5ad20060:T=1650429500:RT=1650429500:S=ALNI_Ma1bK1AQFv5IXqR0b5YcjWTNTWjhw; interface2=2; PHPSESSID=0vs9qgufsqemdes83lnhrova80egk71e; _gid=GA1.2.1021809084.1664639566; __utma=57106600.1657188083.1650376235.1661592835.1664639567.15; __utmc=57106600; smuid=5010626; smlgin=1; __icuc7=5010626; __smvs=1664578800_-_en_EN_-_member; __utmb=57106600.2.10.1664639567; __gpi=UID=00000a15553c1795:T=1664639623:RT=1664639623:S=ALNI_MbD48t8y2PQtgL7YMFe8GGk32iihg";
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
