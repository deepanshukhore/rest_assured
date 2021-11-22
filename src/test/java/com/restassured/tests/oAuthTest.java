package com.restassured.tests;

import static io.restassured.RestAssured.*;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) {

		// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AX4XfWj8rQTUb_MKYdu_gaaUaHh7PQrJvIfXe_pTV8Timut5ioFPntKZgo5D0DrIeGDPGw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none#";

		String partialcode = url.split("code=")[1];

		String code = partialcode.split("&scope")[0];
    
		System.out.println(code);

		String accesstokenResponse = given()
			.queryParam("code",code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
				.queryParam("grant_type", "authorization_code").when()
				.post("https://www.googleapis.com/ /v4/token").then().extract().response().asString();
		JsonPath js = new JsonPath(accesstokenResponse);
		String access_token = js.getString("access_token");
		
		
	   getcourse gs = given().queryParam("access_token", access_token).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(getcourse.class);
	   
	  System.out.println(gs.getLinkedIn());
	  gs.getCourses().getMobile().get(2).getPrice();
	
		//System.out.println(response);

	}

}
