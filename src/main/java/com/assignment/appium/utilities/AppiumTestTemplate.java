package com.assignment.appium.utilities;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumTestTemplate {

	private AppiumDriver<MobileElement> driver;

	/**
	 * @return the driver
	 */
	public AppiumDriver<MobileElement> getDriver() {
		return driver;
	}
	
	@BeforeClass(alwaysRun=true)
	public void setupDriver()
	{
		System.out.println("Launching Application");
		driver = AppLauncher.launch();
		System.out.println("Application Launched Successfully");
	}
	
	@AfterClass(alwaysRun = true)
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
