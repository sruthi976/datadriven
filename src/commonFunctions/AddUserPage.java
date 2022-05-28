package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

public class AddUserPage {
	WebDriver driver;
	//constructor to invoke webdriver methods
	
	public AddUserPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(id ="menu_admin_viewAdminModule")
	WebElement mousetoAdmin;
	@FindBy(id ="menu_admin_UserManagement")
	WebElement mousetousermgnt;
	@FindBy(id ="menu_admin_viewSystemUsers")
	WebElement clickUsers;
	@FindBy(id ="btnAdd")
	WebElement clickAddbtn;
	@FindBy(name="systemUser[userType]")
	WebElement Selectuserrole;
	@FindBy(name="systemUser[employeeName][empName]")
	WebElement Ename;
	@FindBy(name="systemUser[userName]")
	WebElement username;
	@FindBy(name="systemUser[password]")
	WebElement password;
	@FindBy(name="systemUser[confirmPassword]")
	WebElement cpassword;
	@FindBy(name="btnSave")
	WebElement clickSavebtn;

	public boolean verifyAddUser(String selectRole, String enterEname, String enterUser, String enterPass, String enterCPass) throws Throwable
	{
		Actions ac=new Actions(driver);
		ac.moveToElement(mousetoAdmin).perform();
		Thread.sleep(2000);
		ac.moveToElement(mousetousermgnt).perform();
		Thread.sleep(2000);
		ac.moveToElement(clickUsers).click().perform();
		Thread.sleep(2000);
		ac.moveToElement(clickAddbtn).click().perform();
		Thread.sleep(2000);
		new Select(Selectuserrole).selectByVisibleText(selectRole);
		Ename.sendKeys(enterEname);
		username.sendKeys(enterUser);
		password.sendKeys(enterPass);
		Thread.sleep(2000);
		cpassword.sendKeys(enterCPass);
		Thread.sleep(2000);
		clickSavebtn.click();
		Thread.sleep(2000);
		String expected = "";
		String actual = driver.getCurrentUrl();
		if(actual.contains(expected)) {
			Reporter.log("user added",true);
			return true;
		}
		else {
			Reporter.log("user not added",true);
			return false;
		}
	}
	
}
