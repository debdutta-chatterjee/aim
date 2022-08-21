package demo.restassured;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Rest_Assured_Test {
	
	
	
	public void TC01_Get(){
		baseURI="";
		
		RequestSpecification http_request= given();
		http_request.contentType("application/json");
		
		Response http_response=http_request.request(Method.GET,"");
		
		System.out.println(http_response.asPrettyString());
		
		http_response.getStatusCode();
		http_response.getStatusLine();
		http_response.getBody();
		http_response.jsonPath();
		http_response.header("");
		Headers h=http_response.headers();
		
		http_response.contentType();
		Map<String,String> str_map=http_response.cookies();
		
		http_response.getSessionId();
		
		http_response.getTime();
		
		http_response.statusLine();
				
	}
	
	
	@Test(enabled=false)
	public void TC02_Get(){
		baseURI="https://demoqa.com/BookStore/v1";
		
		RequestSpecification http_request= given();
		http_request.contentType("application/json");
		
		Response http_response=http_request.request(Method.GET,"/Books");
		
		System.out.println(http_response.asPrettyString());
		
		System.out.println(http_response.getStatusCode());
		System.out.println(http_response.getStatusLine());
		System.out.println(http_response.getBody());
		System.out.println(http_response.jsonPath());
		System.out.println(http_response.header(""));
		
		Headers h=http_response.headers();
		for(Header h1: h){System.out.println(h1);}
		
		
		System.out.println(http_response.contentType());
		Map<String,String> str_map=http_response.cookies();
		
		System.out.println(http_response.getSessionId());
		
		System.out.println(http_response.getTime());
		
		System.out.println(http_response.statusLine());
				
	}
	
	@Test(enabled=false)
	public void TC03_POST(){
		baseURI="https://reqres.in/api";
		
		RequestSpecification http_request= given();
		http_request.contentType("application/json");
		
		JSONObject obj_json= new JSONObject();
		obj_json.put("name", "morpheus");
		obj_json.put("job", "leader");
		
		http_request.body(obj_json.toJSONString());
		
		Response http_response=http_request.request(Method.POST,"/users");
		
	
		System.out.println(http_response.getStatusCode());
		System.out.println(http_response.getTime());
		System.out.println(http_response.asPrettyString());
		
	}
	
	@Test(enabled=false)
	public void TC04_POST(){
		baseURI="https://reqres.in/api";
		
		RequestSpecification http_request= given();
		http_request.contentType("application/json");
		
		JSONObject obj_json= new JSONObject();
		obj_json.put("name", "morpheus");
		obj_json.put("job", "leader");
		
		//http_request.body(obj_json.toJSONString());
		http_request.body(new File(System.getProperty("user.dir")+"/target/payload.json"));
		
		Response http_response=http_request.request(Method.PUT,"/users/2");
		
		List<Map<String,String>> employees = http_response.jsonPath().get("company.employee.findAll { employee -> employee.id >= 15 && employee.id <= 300 }");
		
                		
                		System.out.println(http_response.getStatusCode());
		System.out.println(http_response.getTime());
		System.out.println(http_response.asPrettyString());
		
	}
	
	
	@Test
	public void TC05_methodchaining(){
		
		JSONObject obj_json= new JSONObject();
		obj_json.put("name", "morpheus");
		obj_json.put("job", "leader");
		
		given()
		.baseUri("https://reqres.in/api")
		.contentType("application/json")
		.body(obj_json.toJSONString())
		
		.when()
		.post("/users/2")
		.then()
		.statusCode(200).log().ifValidationFails();
	
	}
}
