package com.operations;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.commons.Helper;
import com.constants.Constants;


/**
 * TODO Put here a description of what this class does.
 *
 * @author ashiwani.
 *         Created Jun 6, 2018.
 */
public class TestOperations {
	WebDriver driver=null;
	
	public TestOperations(WebDriver driver){
		this.driver=driver;
	}
	
	public void clickTab(String tabname){
		WebElement tab=driver.findElement(By.xpath("//a[contains(text(),'%s')]".replaceAll("%s",tabname)));
		Helper.waitTillElementIsVisible(tab,driver);
		tab.click();
}
	public void clickCareerandJob(){
		WebElement career=driver.findElement(By.xpath("//div[@data-toggle='dropdown']"));
		career.click();
		Helper.sleep(3);
		WebElement jobs=career.findElement(By.xpath("//a[@href='http://talent.coviam.com/']"));
		 jobs.click();
	}
	
	public void typeLocation(String loc){
		WebElement location=driver.findElement(By.xpath("//input[@id='search_location']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", location);
		location.sendKeys(loc);
		location.sendKeys(Keys.ENTER);
		Helper.sleep(3);
	}
	
	public int getJobCount(){
		List<WebElement> li=driver.findElements(By.xpath("//div[@class='position']"));
		int match=li.size();
		return match;	
	}
	
	public String getSearchResult(){
		WebElement result=driver.findElement(By.xpath("//li[@class='no_job_listings_found']"));
		Helper.waitTillElementIsVisible(result,driver);
		String actualResult= result.getText();
		return actualResult;
	}
	
	public String getJobName(String id){
		WebElement jobname=driver.findElement(By.xpath("(//div[@class='position']//h3)[%s]".replaceAll("%s",id)));
		String job=jobname.getText();
		return job;
	}
	
	 public static boolean returnResults(List<Boolean> results) {
			return results.contains(false) ? false : true;
	}
	
	
	public boolean verifyNavigation(){
		List<Boolean> results=new ArrayList<>();
		int count=Constants.tabList.size();
		for(int i=1;i<count;i++){
			String tabName=Constants.tabList.get(i);
			String expectedTitle=Constants.title.get(i);
			clickTab(tabName);
			System.out.println("Sucessfully traverse "+tabName);
			String actualTitle=driver.getTitle().trim();
			if(actualTitle.equals(expectedTitle))
				results.add(true);
			else
				assertTrue(false);
		}
		System.out.println("Result "+results);
		return returnResults(results);
	}
	

	public boolean verifyCareer(){
		boolean flag=false;
		List<String> openJobs=new ArrayList<>();
		String actualResult="";
		int count=0;
			clickCareerandJob();
			Helper.sleep(4);
			Set<String> childWins=driver.getWindowHandles();
			for(String s:childWins){
				driver.switchTo().window(s);
				String actualTi=driver.getTitle();
				if(actualTi.equals(Constants.jobsTitel)){
					typeLocation("Bangalore");
					count=getJobCount();
					
					
					if(count>0){
						for(int i=1;i<=count;i++){
							String id=Integer.toString(i);
							String designation=getJobName(id);
							openJobs.add(designation);
						}
						flag=true;
						System.out.println("Open Jobs are "+openJobs);
					}else{
						actualResult=getSearchResult();
						if(actualResult.equals(Constants.expectedResult))
							flag=true;			
						System.out.println("There is not jobs");
					}
				}
			}			

		return flag;
	}
	
	

}
