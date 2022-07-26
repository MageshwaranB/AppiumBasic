package com.appiumbasics;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class CalculatorOperation {
	static AndroidDriver<WebElement> driver;
	private static final String CALCULATOR_PACKAGE_ID="com.android.calculator2:id/";
	
	@BeforeClass
	public void basicSetup() {
		try {
		DesiredCapabilities dc=new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
		dc.setCapability("appPackage", "com.android.calculator2");
		dc.setCapability("appActivity", "com.android.calculator2.Calculator");
		
		URL url=new URL("http://127.0.0.1:4723/wd/hub");
		driver=new AndroidDriver<WebElement>(url,dc);
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	public void subtraction() {
		driver.findElement(By.id(CALCULATOR_PACKAGE_ID+"digit_9")).click();
		driver.findElement(By.id(CALCULATOR_PACKAGE_ID+"op_sub")).click();
		driver.findElement(By.id(CALCULATOR_PACKAGE_ID+"digit_6")).click();
		driver.findElement(By.id(CALCULATOR_PACKAGE_ID+"eq")).click();
		String res=driver.findElement(By.id(CALCULATOR_PACKAGE_ID+"result")).getText();
		System.out.println(Integer.parseInt(res));
	}

	
}
