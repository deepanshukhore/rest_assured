package com.restassured.tests;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class JiraProject {

	public static void main(String[] args) {

//login scenario//
		SessionFilter Session = new SessionFilter();
		String response = given().header("Content-type", "application/json")
				.body("{ \"username\": \"deepanshukhore\", \"password\": \"1234\" }").filter(Session).when()
				.post("/rest/auth/1/session").then().log().all().extract().response().asString();

		String ExpectedMessage = "Hi!how are you?";

		// ADD comment
		RestAssured.baseURI = "http://localhost:8080";
		String AddcommnetResponce = given().pathParam("Id", "10100").log().all()
				.header("Content-Type", "application/json")
				.body("{\r\n" + "\"body\": \"" + ExpectedMessage + "\",\r\n" + "    \"visibility\": {\r\n"
						+ "\"type\": \"role\",\r\n" + " \"value\": \"Administrators\"\r\n" + "}\r\n" + "}")
				.filter(Session).when().post(" /rest/api/2/issue/{Id}/comment").then().log().all().assertThat()
				.statusCode(201).extract().response().asString();

		JsonPath js = new JsonPath(AddcommnetResponce);
		String commentid = js.getString("Id");

		// Add Attachment

		given().header("X-Atlassian-Token", "no-check").filter(Session).pathParam("key", "10100")
				.header("Content-Type", "multipart/form-data").multiPart("file", new File("jira.txt")).when()
				.post("/rest/api/2/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

		// Get issue

		String Getissue = given().filter(Session).pathParam("key", "10100").queryParam("field", "comment").log().all()
				.when().get("/rest/api/2/issue/{key}").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		System.out.println(Getissue);
		JsonPath js1 = new JsonPath(Getissue);

		int commentsCount = js1.getInt("fields.comment.comments.size()");
		for (int i = 0; i < commentsCount; i++) {
			String commentIdIssue = js1.get("fields.comment.comments[" + i + "].id").toString();
			if (commentIdIssue.equalsIgnoreCase(commentid)) {
				String message = js1.get("fields.comment.comments[" + i + "].body").toString();
				System.out.println(message);
				Assert.assertEquals(message, ExpectedMessage);

			}

		}

	}

}
