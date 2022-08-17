package com.appiumbasics.swaglabs;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

public class BasicGestures {
	DesiredCapabilities dc;
	static AndroidDriver<WebElement> driver;
	@BeforeClass
	public void setup() {
		try {
			dc = new DesiredCapabilities();
			dc.setCapability("platformName", "Android");
			dc.setCapability("platformVersion", "8.0");
			dc.setCapability("deviceName", "Android Emulator");
			dc.setCapability("appPackage", "com.swaglabsmobileapp");
			dc.setCapability("appActivity", "com.swaglabsmobileapp.MainActivity");
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver=new AndroidDriver<WebElement>(url,dc);
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//loginTest();
		} catch (Exception e) {

		}
	}
	
	public void scrollDown() {
		Dimension totalSize=driver.manage().window().getSize();
		Double scrollStartHeight=totalSize.getHeight()*0.5;
		int scrollStart=scrollStartHeight.intValue();
		
		Double scrollEndHeight=totalSize.getHeight()*0.1;
		int scrollEnd=scrollEndHeight.intValue();
		
		new TouchAction(driver)
		.press(PointOption.point(0,scrollStart))
		.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
		.moveTo(PointOption.point(0,scrollEnd))
		.release().perform()
		;
	}
	
	@Test
	public void scrollTillFinalUser() {
		loginTest();
		/*
		 * Logic:
		 * getElement()
		 * Instead finding an element with findElement use findElements, so that we'll get
		 * the list size. this is also done to avoid noSuchElementException i.e. using findElement
		 * results in finding that particular but inside a list, it will try to find the element
		 * in that list
		 * scrollDown()
		 * 1. Get the totalsize of the screen
		 * 2. Figure out what to be starting point. Here I have used 0.5 (midpoint of the screen)
		 * 3. Similarily do that for endpoint (Used 0.2 here)
		 * 4. In a nutshell TouchAction works like, we're placing a virtually finger
		 * in the middle of the screen and moving it upward to 20 % of the screen
		 * 
		 * While loop
		 * While loop will execute the scrollDown() until the size is not equal to 1
		 * Inside this loop, there's one If condition. If we're able to find the 
		 * element i.e. size greater than 1, we're clicking the said element 
		 * breaking out of the loop.
		 */
		System.out.println(getElement().size());
		while(getElement().size()==0) {
			scrollDown();
			if(getElement().size()>0) {
				getElement().get(0).click();
				 break;
			}
		}
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actualText=getElement().get(0).getText();
		Assert.assertEquals("Sauce Labs Onesie", actualText);
	}
	
	public List<WebElement> getElement() {
		return driver.findElements(By.xpath("(//android.widget.TextView[@text=\"Sauce Labs Onesie\"])"));
	}
	public void loginTest() {
		driver.findElementByAccessibilityId("test-Username").sendKeys("standard_user");
		driver.findElementByAccessibilityId("test-Password").sendKeys("secret_sauce");
		driver.findElementByAccessibilityId("test-LOGIN").click();
		driver.manage().window().getSize();
	}
	
	@Test
	public void swipeFromRightToLeftTest() {
		loginTest();
		WebElement bagPackElement=driver.findElement(By.xpath("(//android.widget.TextView[@text='Sauce Labs Backpack'])[1]"));
		bagPackElement.click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollDown();
		
		WebElement addToCart=driver.findElementByAccessibilityId("test-ADD TO CART");
		addToCart.click();
		
		driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"test-Cart\"]")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		swipeRToL();
	}
	
	
	
	public void swipeRToL() {
		Dimension totalSize=driver.manage().window().getSize();
		int anchor=totalSize.getHeight() / 2;
		Double swipeStartDouble=totalSize.getWidth()*0.7;
		int swipeStart=swipeStartDouble.intValue();
		Double swipeEndDouble=totalSize.getWidth()*0.2;
		int swipeEnd=swipeEndDouble.intValue();
		
		new TouchAction<>(driver)
			.press(PointOption.point(swipeStart,anchor))
			.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
			.moveTo(PointOption.point(swipeEnd,anchor))
			.release().perform();
	}
	
	
}
