package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SearchPage extends BasePage
 {

	public SearchPage(WebDriver driver)
	{
		super(driver);
	}	

	@FindBy(xpath="//*[@id='content']/div[3]//img")
	List<WebElement> searchProducts;
	
	@FindBy(name="quantity")
	WebElement txtquantity;

	@FindBy(xpath="(//button[@type='button'])[10]")      //add to cart button
	WebElement  btnaddToCart;
	
	@FindBy(xpath="//div[contains(text(),'Success: You have added')]")
	WebElement cnfMsg;

	
	public boolean isProductExist( String productName)
	{
		for (WebElement product:searchProducts)
		{
			if(product.getAttribute("Title").equals(productName))
			{
				Assert.assertTrue(true);
				break;
			}
		}
		return true;
	}
	
	public void selectProduct(String productName)
	{
		for (WebElement product:searchProducts)
		{
			if(product.getAttribute("Title").equals(productName))
			{
				product.click();
			}
		}
	}
	
	public void addToCart()
	{
		btnaddToCart.click();
	}
	
	public boolean checkConfMsg()
	{
		try {
		Assert.assertEquals(cnfMsg.isDisplayed(),true); 
		  Assert.assertTrue(true);
		}catch(Exception e)
		{
			Assert.fail();
		}
		return true;
	}
}
