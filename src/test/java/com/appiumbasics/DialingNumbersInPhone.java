package com.appiumbasics;

import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class DialingNumbersInPhone {
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
	
	public static void iterateMethod() {
		try {
			for(String numbers:list) {
				driver.findElement(By.id(DIALER_PACKAGES_ID+numbers)).click();
			}
		}
		catch(Exception e) {
			
		}
	}
	
	@Test
	public void dialingSomeNumbers() throws InterruptedException {
		driver.findElement(By.id(DIALER_PACKAGES_ID+"floating_action_button")).click();
		/*
		 * the below code are basically Boiler Plate so we can put in a loop to automate
		 * the same hence the code will be optimized better
		 */
//		driver.findElement(By.id(DIALER_PACKAGES_ID+"one")).click();
//		driver.findElement(By.id(DIALER_PACKAGES_ID+"two")).click();
//		driver.findElement(By.id(DIALER_PACKAGES_ID+"three")).click();
//		driver.findElement(By.id(DIALER_PACKAGES_ID+"four")).click();
//		driver.findElement(By.id(DIALER_PACKAGES_ID+"five")).click();
//		driver.findElement(By.id(DIALER_PACKAGES_ID+"six")).click();
//		driver.findElement(By.id(DIALER_PACKAGES_ID+"seven")).click();
//		driver.findElement(By.id(DIALER_PACKAGES_ID+"eight")).click();
//		driver.findElement(By.id(DIALER_PACKAGES_ID+"nine")).click();
//		driver.findElement(By.id(DIALER_PACKAGES_ID+"two")).click();
		
		DialingNumbersInPhone.iterateMethod();
		driver.findElement(By.id(DIALER_PACKAGES_ID+"dialpad_floating_action_button")).click();
		Thread.sleep(6000);
		//driver.findElement(By.id(DIALER_PACKAGES_ID+"endButton")).click();
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
}
