package com.appiumbasics;


import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;


public class VerticalScrollExample 
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
			//driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
			action=new TouchAction(driver);
		} catch (Exception e) {

		}
	}
	
	@Test
	public void verticalScrollUsingUIScrollableTest() {
		WebElement viewsEntity=driver.findElements(By.id("android:id/text1")).get(11);
		viewsEntity.click();
		
		
		/* 
		 * There are many ways to scroll down to a particular element
		 * One of the ways is by using UiScrollable. Even in UiScrollable, there are plenty
		 * of ways we can perform the scrolling functionality
		 * The below line of code illustrates the scrolling till 
		 * that particular element is found using getChildByText()
		 * It can scroll in both up and down direction
		 * 
		 * getChildByText() method searches for a child element, using its text,
		 * in a scrollable list.
		 * To use this method, you would need to identify the following 3 parameters
		 * Identifier for the scrollable container (this is common across all the methods, 
		 * as this is the list in which the code would scroll to find the element
		 * Identifier for the child element. Example: whether the child element is a TextView, Button etc
		 * The actual text of the child element
		 */
		WebElement scrollToParticularElement=driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"android:id/decor_content_parent\")).getChildByText("
				+"new UiSelector().className(\"android.widget.TextView\"),\"ImageView\")"));
		scrollToParticularElement.click();
	}
	
	TouchAction action;
	
	@Test
	public void verticalScrollUsingDimensionTest() {
		AndroidElement viewsElement=(AndroidElement) driver.findElementByAccessibilityId("Views");
		//tapping Views Element
		action.tap(ElementOption.element(viewsElement)).perform();
		//Scrolldown
		scrollDownTillElement();
		AndroidElement pickerElement=(AndroidElement) driver.findElementByAccessibilityId("Picker");
		//Tapping pickerElement
		//pickerElement.click();
		
		action.tap(ElementOption.element(pickerElement)).perform();
	}
	
	public void scrollDownTillElement() {
		Dimension totalSize=driver.manage().window().getSize();
		int startPoint=(int) (totalSize.getHeight()*0.8);
		int endPoint=(int)(totalSize.getHeight()*0.1);
		action.press(PointOption.point(0,startPoint))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(4)))
		.moveTo(PointOption.point(0,endPoint))
		.release().perform();
	}
	
	//	@AfterClass
//	public void teardown() {
//		driver.quit();
//	}
	
}
