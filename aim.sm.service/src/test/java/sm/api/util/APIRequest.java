package sm.api.util;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIRequest {
	
	public static Response getRequest(String baseUri,String cookie,String endpoint) {
		
		return RestAssured.given()
				.baseUri(baseUri)
				.header("content-type","application/json")
				.header("cookie",cookie)
				.request(Method.GET,endpoint);
		
	}
	
}
