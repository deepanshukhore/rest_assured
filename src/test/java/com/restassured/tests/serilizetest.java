package com.restassured.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class serilizetest {

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
		
		 Response res = given().queryParam("Key", "qaclick123").body(p).when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).extract().response();
		 String responceStirng=res.asString();
		System.out.println(responceStirng);

	}

}
