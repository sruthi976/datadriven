package driverFactory;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtil;

public class DriverScript extends AppUtil{
String inputPath="D:\\Automation_Selenium\\DDT_Framework\\TestInput\\LoginData.xlsx";
String outputPath="D:\\Automation_Selenium\\DDT_Framework\\TestOutput\\DataDrivenResults.xlsx";
String Results="";
@Test
public void validateLogin() throws Throwable
{
	//create object for excelfileutil class
	ExcelFileUtil xl = new ExcelFileUtil(inputPath);
	//no of rows in a sheet
	int rc=xl.rowCount("Login");
	//count no of cells in first row
	int cc=xl.cellCount("Login");
	Reporter.log("no of rows are :"+rc+"  "+"no of cells in first row :"+cc,true);
	//iterate all rows
	for(int i=1;i<=rc;i++)
	{
		driver.get(config.getProperty("Url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//read username cell
		String user =  xl.getCellData("Login",i,0);
		String pass = xl.getCellData("Login",i,1);
		boolean res=FunctionLibrary.verifyLogin(user,pass);
	if(res)
	{
		xl.setCellData("Login",i,2,"Login Success",outputPath);
		xl.setCellData("Login",i,3,"Pass",outputPath);
	}
	else
	{
		//take screenshot whenevr 
		File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("./Screens/Iteration"+i+"LoginPage.png"));
		xl.setCellData("Login",i,2,"Login Failed",outputPath);
		xl.setCellData("Login",i,3,"Fail",outputPath);
	}
	}
}

}
