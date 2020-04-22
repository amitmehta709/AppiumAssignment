package com.assignment.appium.utilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class AppLauncher {

	private static final String LOCAL_URL = "http://127.0.0.1:4723/wd/hub";

	
	public static AppiumDriver<MobileElement> launch() {
		URL url = getUrl();
		AppiumDriver<MobileElement> driver = null;

		driver = new AndroidDriver<>(url, getAndriodCapabilities());
		
		((AndroidDriver<MobileElement>)driver).unlockDevice();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.rotate(ScreenOrientation.PORTRAIT);

		return driver;

	}
	
	private static URL getUrl() {
		String url = AutomationProperties.getProperty("com.assignment.appium.url");
		try {
			if (StringUtils.isBlank(url)) {

				return new URL(LOCAL_URL);

			} else {
				return new URL(url);
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	private static DesiredCapabilities getAndriodCapabilities() {
		
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
				
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
	
		String deviceName = AutomationProperties.getProperty("com.assignment.appium.devicename");
		desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
		
		String udid = AutomationProperties.getProperty("com.assignment.appium.udid");
		desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);
		

		String appPackage = AutomationProperties.getProperty("com.assignment.appium.app.pacakge");
		String appActivity = AutomationProperties.getProperty("com.assignment.appium.app.activity");

		desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
		desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);

		// Platform version
		String version = AutomationProperties.getProperty("com.assignment.appium.platform.version");
		desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
		
		desiredCapabilities.setCapability("newCommandTimeout", 300);
		return desiredCapabilities;
	}
}
