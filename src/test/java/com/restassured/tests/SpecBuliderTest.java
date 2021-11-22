package com.restassured.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuliderTest {

	public static void main(String[] args) {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Addplace p = new Addplace();
        p.setAccuracy(50);
		p.setLanguage("French-IN");
		p.setAddress("29, side layout, cohen 09");
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		location l = new location();
		
		l.setLng("-38.383494");
		l.setLng("33.427362");
		p.setLocation(l);
		
		List<String> mylist = new ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		p.setTypes(mylist);
		
	RequestSpecification req =	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("Key", "qaclick123").
	setContentType(ContentType.JSON).build();
	
	ResponseSpecification responcespec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		 RequestSpecification res = given().spec(req)
				 .body(p);
				 
				 
				Response responce= res.when().post("maps/api/place/add/json")
				.then().spec(responcespec).extract().response();
		 String responceStirng=responce.asString();
		System.out.println(responceStirng);

	}

}
