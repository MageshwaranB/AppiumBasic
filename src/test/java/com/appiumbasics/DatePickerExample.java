package com.appiumbasics;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class DatePickerExample
{
	DesiredCapabilities dc;
	static AndroidDriver<WebElement> driver;
	private static final String API_DEMOS_PACKAGE_NAME="io.appium.android.apis:id/";
	WebDriverWait wait;
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
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			wait=new WebDriverWait(driver, 5);
		} catch (Exception e) {

		}
	}
	
	@Test
	public void pickerTest() {
		WebElement viewsEntity=driver.findElements(By.id("android:id/text1")).get(11);
		viewsEntity.click();
		WebElement scrollToParticularElement=driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"android:id/decor_content_parent\")).getChildByText("
				+"new UiSelector().className(\"android.widget.TextView\"),\"Picker\")"));
		scrollToParticularElement.click();
		//android.widget.LinearLayout
//		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().className(\"android.widget.LinearLayout\")).getChildByText("
//				+"new UiSelector().className(\"android.widget.TextView\"),\"kupima\")"));
		driver.findElementByClassName("android.widget.NumberPicker").sendKeys("kupima");
		String valueString=driver.findElementById("io.appium.android.apis:id/textView1").getText();
		System.out.println(valueString);
	}

}
