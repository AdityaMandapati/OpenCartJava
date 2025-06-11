package Page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class parent_constructor_page {
	
	public WebDriver driver;
	
	public parent_constructor_page(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
