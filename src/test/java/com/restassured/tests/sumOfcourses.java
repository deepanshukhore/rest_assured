package com.restassured.tests;


import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class sumOfcourses {
	
	
	
	@Test
	public void validation() {
		int sum=0;
		JsonPath js = new JsonPath(payLoad.courseprice());
		int count = js.getInt("courses.size()");
		
		for(int i=0;i<count;i++) {
			
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			int amount = price*copies;
			System.out.println(amount);
			sum = sum+amount;
			
			}
		System.out.println(sum);
		int purchaseAmount = js.getInt("dashborad.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
		
			
	}
	
	
	

}
