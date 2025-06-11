package Page_object;
import TestCases.comman_methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class home_page extends parent_constructor_page {
	
	
	public home_page(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		super(driver);
	}

	//element variable
	@FindBy(xpath="//ul[@class=\"list-inline\"]//span[contains(text(),'My Account')]") WebElement myacc_button;
	
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement register_button;
	
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement login_button;
	
	public void click_myacc() {
		
		myacc_button.click();
		
	}
	
	public void click_register() {
		
		register_button.click();
	}
	
	public void click_login() {
		
		login_button.click();
	}

}
