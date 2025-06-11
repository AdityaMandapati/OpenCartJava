package TestCases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import Page_object.home_page;
import Page_object.login_page;

public class Login_test extends comman_methods {
	
	WebDriver driver;
	
	@Test(groups={"Sanity","Master"})
	void loginTest() {
		
		try {
		
		home_page hp = new home_page(driver);
		hp.click_myacc();
		hp.click_login();
		
		login_page lp = new login_page(driver);
		
		lp.give_gmail(p.getProperty("email"));
		lp.give_loginpass(p.getProperty("password"));
		lp.enter_login();
		if (lp.cnf_login().equals("My Account")){
			
			Assert.assertTrue(true);
			
		}
		else {
			Assert.assertFalse(true);
		}
		
	}
		
		catch(Exception e) {
			e.getMessage();
		}
}

}
