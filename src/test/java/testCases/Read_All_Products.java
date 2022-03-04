package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

//import static io.restassured.RestAssured.given;	this one is only for when, but we need to import when and then too
import static io.restassured.RestAssured.*;



public class Read_All_Products {
	
	@Test
	public void read_all_product() {
		
		Response response = 
		given()
			.baseUri("https://techfios.com/api-prod/api/product")
			.header("Content-type","application/json; charset=UTF-8").
		when()
			.get("/read.php").
		then()
			.extract().response();
		
		int actualStatusCode = response.getStatusCode();
		Assert.assertEquals(actualStatusCode, 200);
		
		String actualHeader = response.getHeader("Content-type");
		Assert.assertEquals(actualHeader, "application/json; charset=UTF-8", "Oh no! wrong header");
		
	}

}
