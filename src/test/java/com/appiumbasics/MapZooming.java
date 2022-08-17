package com.appiumbasics;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MapZooming 
{
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
		dc.setCapability("appPackage", "com.google.android.apps.maps");
		dc.setCapability("appActivity", "com.google.android.maps.MapsActivity");
		
		URL url=new URL("http:localhost:4723/wd/hub");
		driver=new AndroidDriver<WebElement>(url,dc);
		}
		catch(Exception e)
		{
		
		}
	}
	
	@Test
	public void MapTest() throws InterruptedException {
		//driver.findElement(MobileBy.xpath("//android.widget.Button[text='SKIP']")).click();
		driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"SKIP\")")).click();;
		Thread.sleep(6000);
		multiTouchMethod();
		Thread.sleep(6000);
		multiTouchMethod();
	}
	
	public void multiTouchMethod() {
		Dimension totalSize=driver.manage().window().getSize();
		int totalHeight=totalSize.getHeight();
		int totalWidth=totalSize.getWidth();
		//1st Touch
		int firstYCoOrdinateStart=(int)(totalHeight*0.5);
		int firstXCoOrdinateStart=(int)(totalWidth*0.6);
		
		int firstYCoOrdinateEnd=(int)(totalHeight*0.3);
		int firstXCoOrdinateEnd=(int)(totalWidth*0.7);
		//2nd Touch
		int secondYCoOrdinateStart=(int)(totalHeight * 0.6);
		int secondXCoOrdinateStart=(int)(totalWidth*0.4);
		int secondYCoOrdinateEnd=(int)(totalHeight *0.75);
		int secondXCoOrdinateEnd=(int)(totalWidth*0.3);
	
		TouchAction touch1=new TouchAction<>(driver);
		touch1.longPress(PointOption.point(firstXCoOrdinateStart,firstYCoOrdinateStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(firstXCoOrdinateEnd,firstYCoOrdinateEnd))
				;
		TouchAction touch2=new TouchAction<>(driver);
		touch2.longPress(PointOption.point(secondXCoOrdinateStart,secondYCoOrdinateStart))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(secondXCoOrdinateEnd,secondYCoOrdinateEnd))
				;
		
		/*
		 * To perform multiple touch actions, we go for MultiTouchAction class, where we
		 * need to mention touchaction class
		 * This class very useful when comes to performing zoom action
		 */
		
		MultiTouchAction multiTouch=new MultiTouchAction(driver);
		multiTouch.add(touch1).add(touch2).perform();
	}
}
