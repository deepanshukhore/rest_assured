package com.restassured.tests;

import io.restassured.RestAssured;
import  static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
public class Basics {

	public static void main(String[] args) {
		
		// given - all inputs details
		// when - submit the file / resource HTTP method
		// then - validate the resource

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String responce = given().log().all().queryParam("key", "qaclick123").header("content_Type", "application/json")
				.body(payLoad.body()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
				.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

		System.out.println(responce);
		JsonPath js = new JsonPath(responce);
		String placeid = js.getString("place_id");
		System.out.println(placeid);
		
		//update place
		given().log().all().queryParam("key", "qaclick123").header("content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeid+"\",\r\n"
				+ "\"address\":\"70 winter walk, USA\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ " \r\n"
				+ " \r\n"
				+ "").
		when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200);//.body("msg", equalTo("Address successfully updated"))
		
		
		// Get place validation 
	String getresponce = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeid)
		.when().get("maps/api/place/get/json").then()
		.assertThat().log().all().statusCode(200).extract().response().asString();
	
	JsonPath Js1 = new JsonPath(getresponce);
	String actualadd = Js1.getString("address");
	System.out.println(actualadd);
	
	}

}


