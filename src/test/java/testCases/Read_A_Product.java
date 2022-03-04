package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

//import static io.restassured.RestAssured.given;	this one is only for when, but we need to import when and then too
import static io.restassured.RestAssured.*;



public class Read_A_Product {
	
	//read a product URL:			 https://techfios.com/api-prod/api/product/read_one.php?id=2373
	//base Uri:						 https://techfios.com/api-prod/api/product
	//end point/ resource:			 /read_one.php
	//Query params:					 ?id=2373
	
	
	SoftAssert softassert;
	//constructor of a class Read_A_Product
	public Read_A_Product() {
		softassert = new SoftAssert();
	}
	
	
	@Test
	public void read_a_product() {
		
		Response response = 
		given()
			.baseUri("https://techfios.com/api-prod/api/product")
			.header("Content-type","application/json")
			.header("Authorization", "bearer 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")
			.queryParam("id", "3464")
			.queryParam("name", "TR samsung S21 2.0").
		when()
			.get("/read_one.php").
		then()
			.extract().response();
		
		int actualStatusCode = response.getStatusCode();
//		Assert.assertEquals(actualStatusCode, 201);					//hard assert if the expected fail, will not run code
		softassert.assertEquals(actualStatusCode, 200, "Status code is not matching!");				////soft assert if the expected fail, still run code. in this example suppose 200, but change 201
		
		String actualHeader = response.getHeader("Content-type");
		Assert.assertEquals(actualHeader, "application/json", "Oh no! wrong header");
		softassert.assertEquals(actualHeader, "application/json", "Oh no! wrong header");
		
		String responseBody = response.getBody().asString();
		System.out.println("Response Body: "+responseBody);
		
		
		JsonPath jp = new JsonPath(responseBody);
		String productId = jp.getString("id");
		Assert.assertEquals(productId, "3464");
		softassert.assertEquals(productId, "3464");
		
		String productName = jp.getString("name");
		Assert.assertEquals(productName, "TR samsung S21 2.0");
		softassert.assertEquals(productName, "TR samsung S21 2.0");

		String productPrice = jp.getString("price");
		Assert.assertEquals(productPrice, "950");
		softassert.assertEquals(productPrice, "950", "Price is not the same.");
		
		softassert.assertAll();
		
		
//		jp.getString("name");
//		jp.getString("description");
//		jp.getString("price");
//		jp.getString("catogory_id");
//		jp.getString("catogory_name");
		
	}

}
