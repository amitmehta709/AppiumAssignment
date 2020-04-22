package com.assignment.appium.screens;

import org.apache.commons.lang3.StringUtils;

import com.assignment.appium.utilities.AppScreenObject;
import com.assignment.appium.utilities.AutomationProperties;
import com.assignment.appium.utilities.DriverUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends AppScreenObject {

	public LoginPage(AppiumDriver<MobileElement> driver) {
		super(driver);
	}
	
	@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/sso_continue")
	public MobileElement continueBtn;
	
	@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/sso_splash_logo")
	public MobileElement amazonLogo;
	
	@AndroidFindBy(id="com.amazon.mShop.android.shopping:id/sign_in_button")
	public MobileElement signInBtn;
	
	@AndroidFindBy(id="ap_email_login")
	public MobileElement usernameField;
	
	@AndroidFindBy(id="continue")
	public MobileElement continuePasswrdBtn;
	
	@AndroidFindBy(id="ap_password")
	public MobileElement passwrdField;
	
	@AndroidFindBy(id="signInSubmit")
	public MobileElement loginBtn;
	
	public boolean validateLandingPage()
	{
		DriverUtils.waitForElement(getDriver(), amazonLogo);
		if(DriverUtils.checkElement(amazonLogo)) 
		{
			System.out.println("Login Page is displayed");
			return true;
		}
		else 
		{
			System.out.println("Login Page is not displayed");
			return false;
		}
	}
	
	
	public void performLogin()
	{
		if(DriverUtils.checkElement(continueBtn)) 
		{
			System.out.println("Logging in with existing account");
			continueBtn.click();
		}
		else if(DriverUtils.checkElement(signInBtn))
		{
			System.out.println("Logging in with usename and password");
			login();
		}
	}
	
	public void login()
	{
		String username = AutomationProperties.getProperty("com.assignment.appium.amazonLogin");
		String passwOrd = AutomationProperties.getProperty("com.assignment.appium.amazonPasswrd");
		
		if (StringUtils.isBlank(username) || StringUtils.isBlank(passwOrd))
		{
			throw new RuntimeException("Provide Amazon Username and Password in Properties File");
		}
		
		DriverUtils.waitForElement(getDriver(), signInBtn);
		signInBtn.click();
		
		DriverUtils.waitForElement(getDriver(), usernameField);
		usernameField.click();
		usernameField.sendKeys(username);
		
		DriverUtils.waitForElement(getDriver(), continuePasswrdBtn);
		continuePasswrdBtn.click();
		
		DriverUtils.waitForElement(getDriver(), passwrdField);
		passwrdField.click();
		passwrdField.sendKeys(passwOrd);
		
		DriverUtils.waitForElement(getDriver(), loginBtn);
		loginBtn.click();
		
		DriverUtils.sleep();
		DriverUtils.sleep();
	}
}
