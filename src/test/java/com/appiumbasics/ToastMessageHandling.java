package com.appiumbasics;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class ToastMessageHandling 
{
	DesiredCapabilities dc;
	static AndroidDriver<WebElement> driver;
	static final String PACKAGE_NAME="io.appium.android.apis:id/";
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
	public void toastMessageTest() throws InterruptedException {
		driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().text(\"Views\")")).click();
		
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Spinner\"))")).click();
		Thread.sleep(5000);
		driver.findElement(By.id(PACKAGE_NAME+"spinner1")).click();
		Thread.sleep(5000);
		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"blue\")").click();;
		WebDriverWait waitForToast = new WebDriverWait(driver,25);

		waitForToast.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/hierarchy/android.widget.Toast")));

		String toastMessage = driver.findElement(By.xpath("/hierarchy/android.widget.Toast")).getText();

		System.out.println(toastMessage);
	}
}
