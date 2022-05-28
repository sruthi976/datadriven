package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import commonFunctions.AddEmpPage;
import commonFunctions.AddUserPage;
import commonFunctions.LoginPage;

public class TestScript {
WebDriver driver;

@BeforeMethod
public void adminLogin()
{
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://www.orangehrm.qedgetech.com/");
	//call login page class
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void validateEmp() throws Throwable
{
	AddEmpPage emp=PageFactory.initElements(driver, AddEmpPage.class);
	boolean results = emp.verifyAddEmp("Sruthi", "Jayaraj", "Vineet");
	System.out.println(results);
}
@Test
public void validateUser() throws Throwable
{
	AddUserPage user=PageFactory.initElements(driver, AddUserPage.class);
	boolean res = user.verifyAddUser("ESS", "amit demo", "Testing123", "Testing123456@!678", "Testing123456@!678");
	System.out.println(res);
}

@AfterMethod
public void tearDown()
{
	driver.close();
}
}
