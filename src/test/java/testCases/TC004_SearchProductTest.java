package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchPage;
import testBase.BaseClass;

public class TC004_SearchProductTest extends BaseClass
{

	@Test(groups="Master")
	public void verify_productsearch()
	{
		logger.info("***Starting of TC004_SearchProductTest***");
		try {
		HomePage hm=new HomePage(driver);
		hm.enterProductName("mac");
		
		hm.clickSearch();
		
		SearchPage sp=new SearchPage(driver);
		sp.isProductExist("MacBook");
		
		Assert.assertEquals(sp.isProductExist("MacBook"), true);
		
		}catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***Ending of TC004_SearchProductTest***");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
