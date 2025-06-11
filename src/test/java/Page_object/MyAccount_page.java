package Page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount_page extends parent_constructor_page {
	
	
	public MyAccount_page(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']") 
	WebElement logout_btn;
	
	public void click_logout() {
		
		logout_btn.click();
	}

	
	
		
}


