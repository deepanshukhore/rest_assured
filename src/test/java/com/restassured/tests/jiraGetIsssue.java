package com.restassured.tests;
import static io.restassured.RestAssured.*;

import io.restassured.filter.session.SessionFilter;

public class jiraGetIsssue extends JiraProject {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFilter Session = new SessionFilter();

		String responce=given().header("Content-type", "application/json")
				.body("{ \"username\": \"deepanshukhore\", \"password\": \"1234\" }").filter(Session).when()
				.post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		// Get issue
		
		String Getissue=given().filter(Session).pathParam("key","10100")
				.queryParam("field", "comment").log().all().
		when().get("/rest/api/2/issue/{key}").then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(Getissue);

		

	}

}
