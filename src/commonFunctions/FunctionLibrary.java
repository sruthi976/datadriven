package commonFunctions;

import org.openqa.selenium.By;
import org.testng.Reporter;

import constant.AppUtil;

public class FunctionLibrary extends AppUtil{
	
	public static boolean verifyLogin(String username,String password)
	{
		driver.findElement(By.xpath(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("ObjLoginBtn"))).click();
		String expected ="dashboard";
		String actual = driver.getCurrentUrl();
		if (actual.contains(expected))
		{
			Reporter.log("login success :"+expected+"   "+actual,true);
			return true;
		}
		else
		{
			//capture error message
			String errorMessage = driver.findElement(By.xpath(config.getProperty("ObjMessage"))).getText();
			Reporter.log("Failed:"+expected+"   "+actual+"   "+errorMessage);
			return false;
		}
	}

}
