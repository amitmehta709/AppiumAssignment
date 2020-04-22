package com.assignment.appium.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.assignment.appium.screens.HomePage;
import com.assignment.appium.screens.LoginPage;
import com.assignment.appium.utilities.AmazonTestTemplate;

public class TestClass extends AmazonTestTemplate {
	
	@Test
	public void basicTest()
	{
		LoginPage startScreen = new LoginPage(this.getDriver());
		Assert.assertEquals(startScreen.validateLandingPage(), true);
		System.out.println("Login Page Validated Successfully");
		System.out.println("Performing Login");
		startScreen.performLogin();
		
		HomePage homePage = new HomePage(this.getDriver());
		Assert.assertEquals(homePage.validateHomePage(), true);
		System.out.println("Home Page Validated Successfully");
		System.out.println("Searching for product");
		homePage.search();
		System.out.println("Scrolling and Clicking on random product");
		homePage.clickRandomProduct();
		
		System.out.println("Test Completed");
	}
	
	

}
