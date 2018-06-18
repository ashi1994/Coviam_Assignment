package com.testcase;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.operations.BasePageOperation;
import com.operations.TestOperations;

/**
 * TODO Put here a description of what this class does.
 *
 * @author ashiwani.
 *         Created Jun 7, 2018.
 */
public class Test_Case extends BasePageOperation {


@Test
public static void Test_Meth(){
	boolean isTestPassed=false;
	TestOperations operations=new TestOperations(driver);
	Reporter.log("--------Navigating over Tabs---------",true);
	isTestPassed=operations.verifyNavigation();
	Reporter.log("-----Verifying Job Seach for Bangalore------",true);
	isTestPassed=operations.verifyCareer();
	System.out.println("Is Test Passed "+isTestPassed);	
}
}
