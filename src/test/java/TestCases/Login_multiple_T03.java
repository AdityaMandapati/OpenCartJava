package TestCases;
import utilities.dataProviderUtility;

import org.testng.Assert;
import org.testng.annotations.Test;

import Page_object.MyAccount_page;
import Page_object.home_page;
import Page_object.login_page;

public class Login_multiple_T03 extends comman_methods {
	
	@Test(dataProvider="excel_data", dataProviderClass=dataProviderUtility.class, groups="DataDriven")
	public void test_multiple_login(String email,String pass, String res) {
		
		boolean target_value;
		
		try {
		//HomePage
		home_page hp=new home_page(driver);
		hp.click_myacc();
		hp.click_login();

		//Login

		login_page lp=new login_page(driver);
		lp.give_gmail(email);
		lp.give_loginpass(pass);
		lp.enter_login();
		if(lp.cnf_login().isEmpty()) {
			target_value = false;
		}
		else {
			target_value = true;
		}
		
		MyAccount_page mp = new MyAccount_page(driver);
		
		if(res.equalsIgnoreCase("Valid")) {
			
			if(target_value==true) {
				
			mp.click_logout();
			Assert.assertTrue(true);
			}
			else {
			Assert.assertTrue(false);
			}

		if(res.equalsIgnoreCase("Invalid")) {
			
			if(target_value=true) {
				
				mp.click_logout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
		
	}
}
		catch(Exception e) {
			Assert.fail();
		}
	}
}


