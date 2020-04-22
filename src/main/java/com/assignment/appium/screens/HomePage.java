package com.assignment.appium.screens;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.assignment.appium.utilities.AppScreenObject;
import com.assignment.appium.utilities.AutomationProperties;
import com.assignment.appium.utilities.DriverUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends AppScreenObject {

	public HomePage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	//@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/chrome_action_bar_home_logo")
	@AndroidFindBy(xpath="//android.widget.ImageView[@resource-id='com.amazon.mShop.android.shopping:id/chrome_action_bar_home_logo']")
	public MobileElement homePageLogo;
	
	@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/rs_search_src_text")
	public MobileElement searchBar;
	
	public boolean validateHomePage()
	{

		DriverUtils.waitForElement(getDriver(), searchBar);
		if(DriverUtils.checkElement(searchBar)) 
		{
			System.out.println("Home Page is displayed");
			return true;
		}
		else 
		{
			System.out.println("Home Page is not displayed");
			return false;
		}
	}
	
	public void search()
	{
		DriverUtils.sleep();
		
		DriverUtils.waitForElement(getDriver(), searchBar);
		searchBar.click();
		DriverUtils.waitForElement(getDriver(), searchBar);
		String searchText = AutomationProperties.getProperty("com.assignment.appium.searchText");
		
		if (StringUtils.isBlank(searchText))
		{
			throw new RuntimeException("Provide Search Text Properties File");
		}
		searchBar.sendKeys(searchText);
		
		Actions action = new Actions(getDriver());
		action.sendKeys(Keys.ENTER).perform();
	}
	
	public void clickRandomProduct()
	{
		DriverUtils.sleep();
		
		//Scrolling Down On page using Touch Actions
		DriverUtils.scrollDowm(getDriver());
		
		WebElement parentElement = getDriver().findElement(By.id("com.amazon.mShop.android.shopping:id/rs_vertical_stack_view"));
		
		List<WebElement> childElements = parentElement.findElements(By.id("com.amazon.mShop.android.shopping:id/list_product_linear_layout"));
		
		//Selecting any random Product after scrolling down
		WebElement mainElement = childElements.get(3);
		
		WebElement productDescLayout = mainElement.findElement(By.id("com.amazon.mShop.android.shopping:id/list_product_description_layout"));
		
		WebElement itemDesc = productDescLayout.findElement(By.id("com.amazon.mShop.android.shopping:id/item_title"));
		
		String productDecription = itemDesc.getText();
		
		System.out.println("Product Description: "+productDecription);
		
		itemDesc.click();				
	}

}
