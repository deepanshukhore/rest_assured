package com.restassured.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.*;

import  io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class DynamicJson {

	@Test(dataProvider="booksData")
	public void AddBook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
	    String responce=	given().header("Content-Type","application/json").body(payLoad.addbook(isbn,aisle)).
		when().post("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
	   
		JsonPath js = new JsonPath(responce);
		String id = js.getString("ID");
		System.out.println(id);
		
		 //delete api
	}
	@DataProvider(name="booksData")
	public Object[][] getdata()
	{
        return new Object[][] {{"nsd23",2323},{"deep","12dswm"},{"nkdcu","s233"}};
		
		
	}
}
