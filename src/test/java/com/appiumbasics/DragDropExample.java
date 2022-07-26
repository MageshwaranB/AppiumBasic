package com.appiumbasics;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.ElementOption;

public class DragDropExample 
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
	public void dragAndDropTest() {
		WebElement viewsEntity=driver.findElements(By.id("android:id/text1")).get(11);
		viewsEntity.click();
		driver.findElementByAccessibilityId("Drag and Drop").click();
		
		/*
		 * Dragging the ball from source and dropping it in the target
		 */
	    WebElement source=driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
	    WebElement target=driver.findElement(By.id("io.appium.android.apis:id/drag_dot_2"));
	    //String draggingText=driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
	    /*
	     * To perform actions like drag and drop we need to use TouchAction, or by AndroidTouchAction
	     * In the longPress method we need to specify the source and in moveTo we need to mention the target
	     * we can't directly pass the source and target like in selenium but through only ElementOption it is possible in appium
	     */
	    
	    //1st AndroidTouchAction Class
	    //androidTouchActionMethod(source, target);
	    //2nd TouchAction Class
	    touchActionMethod(source, target);
	}
	
	public String androidTouchActionMethod(WebElement source, WebElement target) {
		AndroidTouchAction actions=new AndroidTouchAction(driver);
		actions.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();
		return driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
	}
	
	public String touchActionMethod(WebElement source, WebElement target) {
		TouchAction action=new TouchAction(driver);
		action.longPress(ElementOption.element(source)).moveTo(ElementOption.element(target)).release().perform();
		return driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).getText();
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
