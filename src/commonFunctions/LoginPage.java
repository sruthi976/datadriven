package commonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
// define repository for login
	
	@FindBy(name="txtUsername")
	WebElement username;
	@FindBy(id="txtPassword")
	WebElement password;
	@FindBy(name="Submit")
	WebElement loginbtn;
	//method for login
	public void verifyLogin(String enterusername,String enterpassword)
	{
		username.sendKeys(enterusername);
		password.sendKeys(enterpassword);
		loginbtn.click();
	}
}
