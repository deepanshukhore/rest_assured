package com.restassured.tests;

import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.filter.session.SessionFilter;

public class JiraAddAttachment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFilter Session = new SessionFilter();

		given().header("Content-type", "application/json")
				.body("{ \"username\": \"deepanshukhore\", \"password\": \"1234\" }").filter(Session).when()
				.post("/rest/auth/1/session").then().log().all().extract().response().asString();

		// Add attachment

		given().header("X-Atlassian-Token", "no-check").filter(Session).pathParam("Id", "10100")
				.header("Content-Type", "multipart/form-data").multiPart("file", new File("jira.txt")).when()
				.post("/rest/api/2/issue/{Id}/attachments").then().log().all().assertThat().statusCode(200);

	}

	

}
