package com.restassured.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class addBook {

	@Test(dataProvider = "booksData")
	public void addbook(String isbn, String aisle) throws IOException {
		RestAssured.baseURI = "http://216.10.245.166";
		String responce = given().log().all().header("content-Type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("D:\\Addplace.Json")))).when().post("http://216.10.245.166/Library/Addbook.php   ").then()
				.log().all().assertThat()
				.statusCode(200).extract().response().asString();

		JsonPath js = new JsonPath(responce);
		String id = js.get("ID");
		System.out.println(id);

		// delete api

	}

	@DataProvider(name = "booksData")
	public Object[][] getData() 
	{
		return new Object[][] { { "ass212", "121314" }, { "1sae32", "12346gg1" }, { "sscevhk1", "2vjr1" } };
	}
}
