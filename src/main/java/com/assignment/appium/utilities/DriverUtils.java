package com.assignment.appium.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static io.appium.java_client.touch.offset.PointOption.point;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class DriverUtils {

	private static final long SLEEP = 5000;
	
	public static void sleep(long sleep) {
		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
			// Ignore
		}
	}

	public static void sleep() {
		sleep(SLEEP);
	}

	public static void waitForPageLoad(WebDriver driver) {
		long wait = getWait();

		waitForPageLoad(driver, wait);
	}

	public static void waitForPageLoad(WebDriver driver, long wait) {

		new WebDriverWait(driver, wait).until(webDriver -> ((JavascriptExecutor) webDriver)
				.executeScript("return document.readyState").equals("complete"));
	}
	
	private static long getWait() {
		return Long.parseLong(AutomationProperties.getProperty("com.assignment.appium.page.wait"));
	}
	

	public static boolean checkElement(WebDriver driver, WebElement element) {
		long wait = getWait();

		return checkElement(driver, element, wait);
	}
	
	public static boolean checkElement(WebElement element)
	{
		try 
		{
			element.isDisplayed();
			return true;
		}
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	public static boolean checkElement(WebDriver driver, WebElement element, long wait) {
		try {
			waitForElement(driver, element, wait);
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	public static void waitForElement(WebDriver driver, WebElement element) {
		long wait = getWait();

		waitForElement(driver, element, wait);
	}

	public static void waitForElement(WebDriver driver, WebElement element, long wait) {
		new WebDriverWait(driver, wait, 1000).until(ExpectedConditions.and(ExpectedConditions.visibilityOf(element),
				ExpectedConditions.elementToBeClickable(element)));
	}
	
	public static void scrollDowm(AppiumDriver<MobileElement> appiumDriver)
	{
		    Dimension size = appiumDriver.manage().window().getSize();
		    int anchor = (int) (size.width / 2);
		    // Swipe up to scroll down
		    int startPoint = (int) (size.height - 10);
		    int endPoint = 10;

			new TouchAction(appiumDriver).longPress(point(anchor, startPoint))
				.moveTo(point(anchor, endPoint))
				.release()
				.perform();

	}
}
