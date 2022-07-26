package com.appiumbasics;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import junit.framework.Assert;






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
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		} catch (Exception e) {

		}
	}
	
	@Test
	public void sendMessage() throws InterruptedException {
		driver.findElement(By.id("android:id/list")).click();
		WebElement positiveAnswerButton=driver.findElement(By.id(PACKAGE_NAME_ID+"conversation_list_spam_popup_positive_button"));
		positiveAnswerButton.click();
		driver.findElement(By.id(PACKAGE_NAME_ID+"conversation_list_google_tos_popup_positive_button")).click();
		driver.findElement(By.id(PACKAGE_NAME_ID+"start_chat_fab")).click();
		WebElement recepientText=driver.findElement(By.id(PACKAGE_NAME_ID+"recipient_text_view"));
		recepientText.sendKeys("123456892");
		//driver.pressKey(new KeyEvent(AndroidKey.DEL));	
		/*
		 * To press the native keyboard keys, use pressKey
		 */
		driver.pressKey(new KeyEvent(AndroidKey.ENTER));
		String sendingText="Hi, this is your friendly neighborhood QA";
		driver.findElement(By.id(PACKAGE_NAME_ID+"compose_message_text")).sendKeys(sendingText);
		driver.findElement(By.id(PACKAGE_NAME_ID+"send_message_button_icon")).click();
		String textSent=driver.findElement(By.id(PACKAGE_NAME_ID+"message_text")).getText();
		Assert.assertEquals(sendingText, textSent);
	}
}
