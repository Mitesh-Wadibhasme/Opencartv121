package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;



public class TC003_LoginDDT extends BaseClass {
   
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class, groups="Datadriven")    //we have add one more parameter as data provider is not belonging to same class so to access that we have to mention the class name also explicitly.
	public void verify_loginDDT(String email, String pwd, String exp)
	{
		logger.info("***Starting TC_003_LoginDDT****");
		try
		{
		//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
						
		//LoginPage	
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);                     //we should get the email and password from property file and we already make variable to access and we should not hard code this value
		lp.setPassword(pwd);		
		lp.clickLogin();
				
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		/* data is valid - login success - test pass- logout
		 * data is valid - login failed -test fail
		 * 
		 * data is invalid - login success - test fail- logout
		 * data is invalid - login failed - test pass
		 */
		
		if(exp.equalsIgnoreCase("Valid"))      //data is valid   //we can use equals method but if sometimes we do case sensitive stuff like v instead of V and that's why use case ignore method
		{
			if(targetPage==true)               //login is successful
			{
				macc.clickLogout();            //click on logout   this statement is first below the assertion statement but Assertion statement will come at the last as after assertion statement are generally not executable
				Assert.assertTrue(true);      //my test is passed 
				
			}
			else                                 //data is valid target page is false then test is failed 
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))    //if the data is invaild login is successful means test is failed and logout from applications
		{
			if(targetPage==true)               
			{
				macc.clickLogout();            
				Assert.assertTrue(false);      
				
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
		}catch(Exception e)
		{
			Assert.fail();
		}
			logger.info("***Finished TC-003_LoginDDT***");	
	}
	
}
