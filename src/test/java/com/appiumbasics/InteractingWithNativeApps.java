package com.appiumbasics;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class InteractingWithNativeApps 
{
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		/*
		 * To interact with any apps, we need to know package and activity name
		 * APKInfo app provides us with those two things
		 */
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		/*
		 * The below two statements will automatically launch the calculator app
		 * in the virtual device
		 */
		
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
		
		URL url=new URL("http://127.0.0.1:4723/wd/hub");
		AndroidDriver<WebElement> driver=new AndroidDriver<WebElement>(url,dc);
		Thread.sleep(5000);
		/*
		 * Appium Inspector is used to identify elements in an app. What it does is it will
		 * take a screenshot of the view and from that we can search for an element
		 */
		
		driver.findElementById("com.android.calculator2:id/digit_7").click();//Clicking 7
		driver.findElementById("com.android.calculator2:id/op_add").click();//Clicking add button
		driver.findElementById("com.android.calculator2:id/digit_3").click();//Clicking 3
		driver.findElementById("com.android.calculator2:id/eq").click();//Clicking the equals button
		Thread.sleep(4000);
		/*
		 * By manually adding the numbers in virtual devices, once done the addition is done
		 * To capture the result element, we have refresh the appium inspector
		 */
		String result=driver.findElementById("com.android.calculator2:id/result").getText(); //Capturing Result 
		if(result.equals("10")) {
			System.out.println("Test passed");
		}
		else {
			System.out.println("Test failed");
		}
		Thread.sleep(4000);
		driver.quit();
	}
}
