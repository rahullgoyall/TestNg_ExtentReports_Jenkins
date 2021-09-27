package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import reports.ExtentLogger;
import reports.ExtentManager;
import utility.Init;

public class LoginTest extends Init {
	
	@Test
	public void login1(){
		System.out.println("Lgin 1 ");
		System.out.println(driver.getTitle());
		Assert.assertTrue(false);
	}
	
	
	@Test
	public void login2(){
		System.out.println("login 2");
		System.out.println(driver.getTitle());
		
	}
	

}
