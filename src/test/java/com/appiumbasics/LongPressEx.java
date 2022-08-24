package com.appiumbasics;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.ElementOption;

public class LongPressEx 
{
	DesiredCapabilities dc;
	static AndroidDriver<WebElement> driver;
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
	public void longPressTest() {
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Views\")").click();
		driver.findElementByAccessibilityId("Expandable Lists").click();
		WebElement customAdapter=driver.findElementByAccessibilityId("1. Custom Adapter");
		TouchAction touchAction=new TouchAction<>(driver);
		touchAction.tap(ElementOption.element(customAdapter)).perform();
		WebElement peopleNames=driver.findElementByAndroidUIAutomator("new UiSelector().text(\"People Names\")");
		touchAction.longPress(ElementOption.element(peopleNames)).perform();
		List<WebElement> options=driver.findElements(By.id("android:id/title"));
		for (WebElement expandableOption : options) {
			System.out.println(expandableOption.getText());
		}
	}
}
