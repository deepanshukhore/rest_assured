package com.restassured.tests;

import io.restassured.path.json.JsonPath;

public class ComplexJsonpath {

	public static void main(String[] args) {
		
		 JsonPath js = new JsonPath(payLoad.courseprice());
		 
		int count= js.getInt("courses.size()");
		System.out.println(count);
		
		//Purchase amount
		
		int count1= js.getInt("dashboard.purchaseAmount");
		System.out.println(count1);
		
		
		// print title of e first courses
		String Tittle=js.get("courses[2].title");
		System.out.println(Tittle);
		
		//print all the title and the price of courses
		
            for( int i=0;i<count;i++)
            {
            	
            	 String coursesdetials=js.get("courses["+i+"].title");
            	 System.out.println(js.get("courses["+i+"].price").toString());
            	 System.out.println(coursesdetials);
            }
            	 // print no of copies sold by RPA
            	System.out.println("print no of copies sold by RPA");

                 for( int i=0;i<count;i++)
                 {
                 	
                 	 String  coursesdetials=js.get("courses["+i+"].title");
                 	 if(coursesdetials.equalsIgnoreCase("RPA"))
                 	 {
                 		 int copies= js.getInt("courses["+i+"].copies");
                 		 System.out.println(copies);
                 		 break;
                       }
                 	 }
                 
            }
            	
            	
            	
            	
            	
            	
            	
            	
        
	}

