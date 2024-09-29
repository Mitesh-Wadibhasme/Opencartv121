package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC005_AddToCartPageTest extends BaseClass{

	@Test(groups="Master")
	public void verify_addToCart() 
	{
		logger.info("***Starting TC_005_AddToCartPageTest***");
		
		HomePage hp=new HomePage(driver);
		hp.enterProductName("iPhone");
		hp.clickSearch();
		
		SearchPage sp=new SearchPage(driver);
		try {
		if(sp.isProductExist("iPhone"))
		{
			sp.selectProduct("iPhone");
			sp.addToCart();
			
		}
		
		Assert.assertEquals(sp.checkConfMsg(),true);

		}catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
