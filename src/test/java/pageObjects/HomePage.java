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
	
	@FindBy(xpath="//input[@placeholder='Search']")   //for search product
	WebElement txtSearchBox;
	
	@FindBy(xpath="//i[@class='fa fa-search']")   //for search product
	WebElement btnSearch;
	
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
	
	public void enterProductName(String pName)
	{
		txtSearchBox.sendKeys(pName);
	}
	
	public void clickSearch()
	{
		btnSearch.click();
	}
}
