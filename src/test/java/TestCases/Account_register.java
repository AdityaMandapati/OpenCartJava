package TestCases;

import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import org.testng.annotations.Test;

import Page_object.home_page;
import Page_object.registration_page;
import Page_object.parent_constructor_page;


public class Account_register extends comman_methods{
	
	public EdgeDriver driver;
	
	@Test(groups = {"Regression","Master"})
	void test_account_register() {
		
		try {
			
			logger.info("TC Account Registration Started");
			
			home_page hp = new home_page(driver);
			
			logger.info("Logged into HomePage");
			
			hp.click_myacc();
			hp.click_register();
			
			logger.info("Navigating to Registration page");
			
			registration_page rp = new registration_page(driver);
			
			logger.info("Entering New Coustomer Details");
			
			rp.give_firstname(randomString().toUpperCase());
			rp.give_lastname(randomString().toUpperCase());
			rp.give_email(randomString()+"@gmail.com");
			rp.give_telephone(randomNumber());
			
			String passw = randomAlphaNum();
			System.out.println(passw);
			rp.create_newpass(passw);
			rp.confirm_newpass(passw);
			
			rp.click_policy();
			rp.click_continue();
			
			if(rp.Confirm_registrtaion().equals("Your Account Has Been Created!")) {
				
				Assert.assertTrue(true);
				logger.info("Validation Completed and got as Expected");
			}
			else {
				Assert.assertTrue(false);
				logger.debug("debug logs..");
				logger.error("Validation Completed and not got as Expected");
			}	
		}
		
		catch(Exception e) {
			logger.info(e.getMessage());
			logger.debug("debug logs..");
			Assert.assertFalse(true);
		}	
		
		logger.info("TC Account Registration Completed");
		
	}
	
	
	
}
