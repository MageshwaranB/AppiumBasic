package com.appiumbasics;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class HandlingAutoCompleteDropdown 
{
	DesiredCapabilities dc;
	static AndroidDriver<WebElement> driver;
	static final String PACKAGE_NAME="io.appium.android.apis:id/";
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
	public void handlingDropdownTest() {
		driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Views\")")).click();
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"Auto Complete\")").click();
		driver.findElementByAccessibilityId("1. Screen Top").click();
		driver.findElementById(PACKAGE_NAME+"edit").sendKeys("United");
		/*
		 * There's some issue in the AUT that's why we need to press Enter and Del
		 * keys in order to press the down arrow keys
		 * Using presskey method we can simulate the android keyboard actions
		 * 
		 */
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		driver.pressKey(new KeyEvent(AndroidKey.DEL));
		driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
		driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
		driver.pressKey(new KeyEvent(AndroidKey.DPAD_DOWN));
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
	}
}
