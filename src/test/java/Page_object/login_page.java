package Page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class login_page extends parent_constructor_page {
	
	public login_page(WebDriver driver) {
		
			super(driver);
		}
	
	@FindBy(xpath="//input[@id='input-email']") WebElement enter_email;
	
	@FindBy(xpath="//input[@id='input-password']") WebElement enter_pass;
	
	@FindBy(xpath="//input[@value='Login']") WebElement login_btn;
	
	@FindBy(xpath="//title[normalize-space()='My Account']") WebElement cnf_login;
	
	
	
	public void give_gmail(String email) {
		
		enter_email.sendKeys(email);
		
	}
	
	public void give_loginpass(String pass) {
		
		enter_pass.sendKeys(pass);
		
	}
	
	public void enter_login() {
		
		login_btn.click();
	}
	
	public String cnf_login() {
		
		try {
			return cnf_login.getText();
		}
		catch(Exception e) {
			
			return e.getMessage();		
		}
	}
	
	
	

	
	
}
