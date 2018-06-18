package com.commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
	
	public static void waitTillElementIsVisible(WebElement element,WebDriver driver){
		  new WebDriverWait(driver,1500)
		  .until(ExpectedConditions.visibilityOf(element));
	 }
	
	
	public static void sleep(int second){
		try {
			Thread.sleep(1000*second);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch-block stub.
			e.printStackTrace();
		}
	}

}
