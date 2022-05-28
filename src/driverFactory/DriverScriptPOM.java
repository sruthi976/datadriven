package driverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonFunctions.AddUserPage;
import commonFunctions.LoginPage;
import utilities.ExcelFileUtil;

public class DriverScriptPOM {
WebDriver driver;
String inputpath="D:\\Automation_Selenium\\DDT_Framework\\TestInput\\UserData.xlsx";
String outputpath="D:\\Automation_Selenium\\DDT_Framework\\TestOutput\\UserResults.xlsx";

@BeforeTest
public void adminLogin()
{
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://www.orangehrm.qedgetech.com/");
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void validateUser() throws Throwable
{
	AddUserPage user = PageFactory.initElements(driver, AddUserPage.class);
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	int rc=xl.rowCount("AddUser");
	int cc=xl.cellCount("AddUser");
	Reporter.log(rc+"  "+cc,true);
	for(int i=1;i<=rc;i++)
	{
		String userRole = xl.getCellData("AddUser", i, 0);
		String empName = xl.getCellData("AddUser", i, 1);
		String userName = xl.getCellData("AddUser", i, 2);
		String pswd = xl.getCellData("AddUser", i, 3);
		String cpswd = xl.getCellData("AddUser", i, 4);
		boolean res = user.verifyAddUser(userRole, empName, userName, pswd, cpswd);
		if(res)
		{
			xl.setCellData("AddUser", i, 5, "Pass", outputpath);
		}
		else
		{
			xl.setCellData("AddUser", i, 5, "Fail", outputpath);
		}
	}
}

@AfterTest
public void tearDown()
{
	driver.close();
}
}
