package com.appiumbasics;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class InstallingAppUsingScript {
	public static void main(String[] args) throws MalformedURLException {
	/*
	 * To install an app through, first we need to have both virtual device and
	 * appium server up and running.
	 * If one want to check whether the virtual device is connected to the appium
	 * server, type adb.exe devices in cmd, it will show the device name and attached status
	 * For the appium server to understand our code, we need to provide the desired capabilities
	 * We can set various capabilities of the device type using setCapability method 
	 */
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		dc.setCapability(MobileCapabilityType.APP, "E:\\APK Files\\screencoordinater.apk");
	/*
	 * Using the URL class, we can specify the appium server running url
	 * 4723 is the port number
	 * http://127.0.0.1 is the local host
	 * wd is the short form of WebDriver
	 * hub refers to Selenium Grid configuration where the two components are:
	 * Selenium Grid Hub (is commonly termed as hub)
	 * Selenium Grid Node (is commonly termed as node)
	 * wd/hub is the part of the uri through which the node communicates with the hub
	 */
		URL url=new URL("http://127.0.0.1:4723/wd/hub");
	/*
	 * By creating the object of AndroidDriver, we can install the app in the virtual devices
	 * the constructor takes both the appium server url and desired capabilities object
	 */
		AndroidDriver<WebElement> driver=new AndroidDriver<WebElement>(url,dc);
	
		/*
		 * To close the appium server session, use quit method
		 */
		driver.quit();
		
	}
}	
