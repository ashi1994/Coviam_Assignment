package com.operations;

/*
 * Base class in the main class which will take care of Browser setup,
 * loading configuration file and other reusable methods like screenshot,
 * handling sync issues and many more.
 * Reuse code,Save time and efforts.
 */



import org.testng.annotations.BeforeClass;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;


public class BasePageOperation {
	public static WebDriver driver;
	public static WebElement element=null;
	
	

  @BeforeClass
  public void applicationStart() {
	  Reporter.log("=====Browser Session Started=====", true);

		   System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"/"+"resource/geckodriver.exe");
		      driver = new FirefoxDriver();//Version-60.0
		       driver.manage().window().maximize();
			   driver.get("http://coviam.com/index.html");
				WebDriverWait wait=new WebDriverWait(driver,50);
				wait.until(ExpectedConditions.urlContains("http://coviam.com/"));  
	 
	

         Reporter.log("=====Application Started======================", true);
	  
	  
  }

  @AfterClass
  public void afterClass() {
	  driver.quit();
	  driver=null;
	  Reporter.log("=====Browser Session End Successfully=====", true);
  }
  
  
  @AfterMethod
  public void writeResult(ITestResult result)
  {
      try
      {
          if(result.getStatus() == ITestResult.SUCCESS)
          {

             // Reporter.log("Test case executed successfully",true);
        
          }
          else if(result.getStatus() == ITestResult.FAILURE)
        	  
          {
        	  Reporter.log("Test Case Fail,name is "+result.getName() ,true);
        		try{
    				// To create reference of TakesScreenshot
    				TakesScreenshot screenshot=(TakesScreenshot)driver;
    				// Call method to capture screenshot
    				File src=screenshot.getScreenshotAs(OutputType.FILE);
    				// Copy files to specific location 
    				// result.getName() will return name of test case so that screenshot name will be same as test case name
    				//File f1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    				FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/"+"FailedTestsScreenshots"+"/" +result.getName()+".png"));
    				System.out.println("Successfully captured a screenshot and Saved in Folder FailedTestsScreenshots and name is "+result.getName()+".png");
    			}
    			catch (Exception e){
    				System.out.println("Exception while taking screenshot "+e.getMessage());
    			}
        		

          }
          else if(result.getStatus() == ITestResult.SKIP)
          {
              System.out.println("Log Message::@AfterMethod: Method-"+result.getName()+"- has Skipped");
              

          }
      }
      catch(Exception e)
      {
          System.out.println("\nLog Message::@AfterMethod: Exception caught");
          e.printStackTrace();
      }


  
  
}
  @AfterSuite
  public void openReport(){
	  File htmlFile = new File(System.getProperty("user.dir")+"/"+"/test-output/html/index.html");
	  try {
		Desktop.getDesktop().browse(htmlFile.toURI());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  
  
  }