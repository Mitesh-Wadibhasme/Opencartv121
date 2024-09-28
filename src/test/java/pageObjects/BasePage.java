package pageObjects;     //so we have created one common constructor to access to every class without writing in every class separately
                         //and we make this as public so any class can access
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	WebDriver driver;
	
	public BasePage(WebDriver driver)          
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
