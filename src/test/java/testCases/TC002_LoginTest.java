package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"Sanity","Master"})
	public void verify_Login()
	{
		logger.info("***Starting TC_002_LoginTest****");
		try
	    {
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
				
		//LoginPage	
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));                     //we should get the email and password from property file and we already make variable to access and we should not hard code this value
		lp.setPassword(p.getProperty("password"));		
		lp.clickLogin();
		
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		//Assert.assertEquals(targetPage, true);
		Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***Finished TC_002_LoginTest****");
	}
	
}
