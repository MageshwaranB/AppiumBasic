package com.appiumbasics;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

public class SwipeGesture {
	DesiredCapabilities dc;
	static AndroidDriver<WebElement> driver;
	private final static String DIALER_PACKAGES_ID="com.google.android.dialer:id/";
	static List<String> list;
	@BeforeClass
	public void setup() {
	list=Arrays.asList("one","two","three","four","five","six","seven","eight","nine","two");
		try {
			dc = new DesiredCapabilities();
			dc.setCapability("platformName", "Android");
			dc.setCapability("platformVersion", "8.0");
			dc.setCapability("deviceName", "Android Emulator");
			dc.setCapability("appPackage", "com.google.android.dialer");
			dc.setCapability("appActivity", "com.google.android.apps.dialer.extensions.GoogleDialtactsActivity");
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver=new AndroidDriver<WebElement>(url,dc);
		} catch (Exception e) {

		}
	}
	
	@Test
	public void swipeTest() {
		WebElement panel=driver.findElement(By.id(DIALER_PACKAGES_ID+"lists_pager"));
		String actualText=null;
		swipeActionMethod(panel);
		//Assert.assertEquals("CALL VOICEMAIL", actualText);
	}
	
	public void swipeActionMethod(WebElement panel) {
		Dimension totalPanelSize=panel.getSize();
		/*
		 * In the below line, we're identifying in which point(Y axis) we want to press
		 * in this case it is half of the panel size
		 */
		int anchor=panel.getSize().getHeight()/2;
		/*
		 * Now, we will identify the x axis point. for the we will be taking 80% of the screen
		 * from left to right, so we'll be swipping from the 10% of the screen on the right
		 * to the remaining 80% on the left
		 */
		Double screenWidthStart=totalPanelSize.getWidth()*0.8;
		int swipeStart=screenWidthStart.intValue();
		
		Double screenWidthEnd=totalPanelSize.getWidth()*0.1;
		int swipeEnd=screenWidthEnd.intValue();
		
		new TouchAction<>(driver)
			.press(PointOption.point(swipeStart,anchor))
			.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
			.moveTo(PointOption.point(swipeEnd,anchor))
			.release().perform();
	}
	
	public List<WebElement> getElement() {
		return driver.findElements(By.id(DIALER_PACKAGES_ID+"emptyListViewMessage"));
	}
}
