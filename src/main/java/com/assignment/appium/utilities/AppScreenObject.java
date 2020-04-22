package com.assignment.appium.utilities;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AppScreenObject {

	private AppiumDriver<MobileElement> driver;

	public AppScreenObject(AppiumDriver<MobileElement> driver)
	{
		this.driver = driver;
		int wait = Integer
				.parseInt(AutomationProperties.getProperty("com.assignment.appium.page.wait"));
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(wait)), this);
	}

	/**
	 * @return the driver
	 */
	public AppiumDriver<MobileElement> getDriver() {
		return driver;
	}
}
