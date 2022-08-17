package com.appiumbasics;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class AlertHandling 
{
	DesiredCapabilities dc;
	static AndroidDriver<WebElement> driver;
	private static final String API_DEMOS_PACKAGE_NAME="io.appium.android.apis:id/";
	@BeforeClass
	public void setup() {
		try {
			dc = new DesiredCapabilities();
			dc.setCapability("platformName", "Android");
			dc.setCapability("platformVersion", "8.0");
			dc.setCapability("deviceName", "Android Emulator");
			dc.setCapability("appPackage", "io.appium.android.apis");
			dc.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver=new AndroidDriver<WebElement>(url,dc);
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		} catch (Exception e) {

		}
	}
	
	@Test
	public void handlingDifferentAlerts() {
		driver.findElementByAccessibilityId("App").click();
		driver.findElementByAccessibilityId("Alert Dialogs").click();
		//SimpleAlert
		simpleAlertWithSimpleText();
		//singleChoiceList
		singleChoiceList().stream().anyMatch(match->match.contains("Satellite"));
	}
	
	public void simpleAlertWithSimpleText() {
		driver.findElementById(API_DEMOS_PACKAGE_NAME+"two_buttons").click();
		driver.findElement(By.id("android:id/button1")).click();
		
	}
	
	public List<String> singleChoiceList() {
		driver.findElementByAccessibilityId("Single choice list").click();
		List<WebElement> allChoices=driver.findElementsById("android:id/text1");
		List<String> choiceName=new LinkedList<String>();
		for(WebElement choice:allChoices) {
			choiceName.add(choice.getText());
		}
		return choiceName;
	}
}
