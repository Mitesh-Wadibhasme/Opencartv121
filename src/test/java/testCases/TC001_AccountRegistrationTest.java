package testCases;    //everything in this class will be public so it can be access thought anywhere in the project
                     //every test case must be extends from BaseClass
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {

	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("***Starting TC001_AccountRegistrationTest ***");
		try {
		
		HomePage hp=new HomePage(driver);            //to access the action method from HomePage we have to make first object for HomePage and we have to pass driver as driver is invoking at action class
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		
		hp.clickRegister();
		logger.info("Clicked on Register Link");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		//regpage.setFirstName("john");
		logger.info("Providing customer details..");
		regpage.setFirstName(randomString().toUpperCase());     //first letter in name always capital so touppercase
		
		regpage.setLastName(randomString().toUpperCase());
		
		//regpage.setEmail("abcjohndavid@gmail.com");
		regpage.setEmail(randomString()+"@gmail.com");      //we are generating random email
		
		
		//regpage.setTelephone("1234567890");
		regpage.setTelephone(randomNumber());
		
		String password=randomAlphaNumeric();     //we need to just one value aat the both the places ass it password and confirm password field if we put random in both place it will give two differnt strings thats why we are randomly storing the value in variable and that variable we are passing in the both the field
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message..");
		String confmsg=regpage.getConfirmationMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		else 
		{
			logger.error("Test failed");
			Assert.assertTrue(false);
			
		}
		//Assert.assertEquals(regpage, "Your Account Has Been Created!");
		}catch(Exception e)                   //if assert block fail then catch block will handle and then error and debug logs we want
		{
			Assert.fail();
		}
		logger.info("Finished TC001_AccountRegistrationTest");
	}
	
}
