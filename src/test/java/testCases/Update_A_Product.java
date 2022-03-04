package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

//import static io.restassured.RestAssured.given;	this one is only for when, but we need to import when and then too
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;



public class Update_A_Product {
	
	SoftAssert softassert;
	//constructor of a class Read_A_Product
	public Update_A_Product() {
		softassert = new SoftAssert();
	}
	
	@Test
	public void Update_a_product() {
		
		//we can send the body as a map of String:
//		HashMap<String, String> payload = new HashMap<String, String>(); 
//		payload.put("name", "sokoeurn amazing fan 2.0");
//		payload.put("price", "299");
//		payload.put("description", "a beautiful fan imported from Thailand");
//		payload.put("catogory_id", "2");
		
		
		Response response = 
		given()
			.baseUri("https://techfios.com/api-prod/api/product")
			.header("Content-type","application/json; charset=UTF-8")
//			.auth().preemptive().basic("sokoeurn88", "#Kem1988#")
//			.header ("Authorization", "Bearer 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")
			.body(new File(".\\src\\main\\java\\data\\UpdatePayload.json")).	//sending as a file
//			.body(payload).		//sending as a String of HashMap
		when()
			.put("/update.php").
		then()
			.extract().response();
		
		int actualStatusCode = response.getStatusCode();
//		Assert.assertEquals(actualStatusCode, 201);
		softassert.assertEquals(actualStatusCode, 200);
		
		String actualResponseHeader = response.getHeader("Content-type");
//		Assert.assertEquals(actualResponseHeader, "application/json; charset=UTF-8", "Oh no! wrong header");
		softassert.assertEquals(actualResponseHeader, "application/json; charset=UTF-8", "Oh no! wrong header");
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body:" + responseBody);
		
		JsonPath jp = new JsonPath(responseBody);
		String actualResponseMessage = jp.getString("message");
//		Assert.assertEquals(actualResponseMessage, "Product was updated.");
		softassert.assertEquals(actualResponseMessage, "Product was updated.");
		
		Long actualResponseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("ResponseTime: "+ actualResponseTime);
		
		if(actualResponseTime<2000) {
			System.out.println("Response time is within range");
			
		} else {
			
			System.out.println("Response time is out of range");
		}
		
		softassert.assertAll();
	}

}
