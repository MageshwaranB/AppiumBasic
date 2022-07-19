package com.appiumbasics;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class SendingMessages 
{
	DesiredCapabilities dc;
	static AndroidDriver<WebElement> driver;
	private static final String PACKAGE_NAME_ID="com.google.android.apps.messaging:id/";
	@BeforeClass
	public void setup() {
		try {
			dc = new DesiredCapabilities();
			dc.setCapability("platformName", "Android");
			dc.setCapability("platformVersion", "8.0");
			dc.setCapability("deviceName", "Android Emulator");
			dc.setCapability("appPackage", "com.google.android.apps.messaging");
			dc.setCapability("appActivity", "com.google.android.apps.messaging.home.HomeActivity");
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver=new AndroidDriver<WebElement>(url,dc);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {

		}
	}
	
	@Test
	public void sendMessage() throws InterruptedException {
//		WebElement positiveAnswerButton=driver.findElement(By.id(PACKAGE_NAME_ID+"conversation_list_spam_popup_positive_button"));
//		WebDriverWait wait=new WebDriverWait(driver, 15);
//		wait.until(ExpectedConditions.visibilityOf(positiveAnswerButton));
//		positiveAnswerButton.click();
		driver.findElement(By.id("android:id/list")).click();
		//Thread.sleep(10000);
		WebElement positiveAnswerButton=driver.findElement(By.id(PACKAGE_NAME_ID+"conversation_list_spam_popup_positive_button"));
		positiveAnswerButton.click();
		driver.findElement(By.id(PACKAGE_NAME_ID+"conversation_list_google_tos_popup_positive_button")).click();
		//driver.findElement(By.id("android:id/button1")).click();
	}
}
