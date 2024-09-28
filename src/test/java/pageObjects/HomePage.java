package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{                     //we are extending the locator from BasePage to achieve reuseability
	
	
	public HomePage(WebDriver driver)                        
	{
		super(driver);                                      //from the child class i.e HomePage we can invoke the parent class variable,methods and constructor as well with super keyword
	}
	
	
	
	@FindBy(xpath="//span[normalize-space()='My Account']") 
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']") 
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
	WebElement linkLogin;
	
	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}

	public void clickLogin()
	{
		linkLogin.click();
	}
}
