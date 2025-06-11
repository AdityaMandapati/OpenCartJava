package Page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class registration_page extends parent_constructor_page {
	
	//constructor
	
	public registration_page(WebDriver driver) {
		
		super(driver);
	}
	
//element variable
	
@FindBy(xpath="//input[@id='input-firstname']") WebElement firstname;

@FindBy(xpath="//input[@id='input-lastname']") WebElement lastname;

@FindBy(xpath="//input[@id='input-email']") WebElement email;

@FindBy(xpath="//input[@id='input-telephone']") WebElement telephone;

@FindBy(xpath="//input[@id='input-password']") WebElement create_password;

@FindBy(xpath="//input[@id='input-confirm']") WebElement confirm_password;

@FindBy(xpath="//input[@name='agree']") WebElement policy_agree;

@FindBy(xpath="//input[@value='Continue']") WebElement continue_button;

@FindBy(xpath="//div[@id=\"content\"]/h1[contains(text(),'Your Account Has Been Created!')]") WebElement register_conf;

//action_menthods

public void give_firstname(String fname) 
{
	firstname.sendKeys(fname);
}

public void give_lastname(String lname)
{
	lastname.sendKeys(lname);
}

public void give_email(String enteremail) 
{
	email.sendKeys(enteremail);
}

public void give_telephone(String enternumber)
{
	telephone.sendKeys(enternumber);
}

public void create_newpass(String pass)
{
	create_password.sendKeys(pass);
}

public void confirm_newpass(String cpass)
{
	confirm_password.sendKeys(cpass);
}

public void click_policy()
{
	policy_agree.click();
}

public void click_continue() 
{
	continue_button.click();
}

public String Confirm_registrtaion()

{
	try {
		return register_conf.getText();
	}
	catch(Exception e){
		return e.getMessage();
	}
	
}

}
