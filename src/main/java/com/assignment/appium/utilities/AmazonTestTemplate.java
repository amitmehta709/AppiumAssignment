package com.assignment.appium.utilities;

import org.testng.annotations.BeforeMethod;

public class AmazonTestTemplate extends AppiumTestTemplate {

	@BeforeMethod
	public void resetApp()
	{
		System.out.println("Performing App Reset");
		this.getDriver().resetApp();;
	}
}
