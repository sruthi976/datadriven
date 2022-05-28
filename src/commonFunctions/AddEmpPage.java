package commonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddEmpPage {
	WebDriver driver;
	
	public AddEmpPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(id="menu_pim_viewPimModule")
	WebElement clickPIM;
	@FindBy(id="btnAdd")
	WebElement clickAddBtn;
	@FindBy(id="firstName")
	WebElement fName;
	@FindBy(id="middleName")
	WebElement mName;
	@FindBy(id="lastName")
	WebElement lName;
	@FindBy(id="employeeId")
	WebElement empId;
	@FindBy(id="btnSave")
	WebElement clickSaveBtn;
	@FindBy(id="personal_txtEmployeeId")
	WebElement empIdAfterSave;
	public boolean verifyAddEmp(String enterFName,String enterMName,String enterLName) throws Throwable
	{
		Actions ac=new Actions(driver);
		ac.moveToElement(clickPIM).click().perform();
		Thread.sleep(2000);
		ac.moveToElement(clickAddBtn).click().perform();
		Thread.sleep(2000);
		fName.sendKeys(enterFName);
		mName.sendKeys(enterMName);
		lName.sendKeys(enterLName);
		//clickSaveBtn.click();
		Thread.sleep(4000);
		//capture empId
		String beforeEmpId=empId.getAttribute("value");
		ac.moveToElement(clickSaveBtn).click().perform();
		String afterEmpId=empIdAfterSave.getAttribute("value");
		if(beforeEmpId.equals(afterEmpId))
		{
			Reporter.log("adding employee was a sucess",true);
			return true;
		}
		else
		{
			Reporter.log("adding employee was not possible",true);
			return false;
		}
	}
}
