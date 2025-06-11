package Test_base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

//for log4j2
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//for radomString classes
import org.apache.commons.lang3.RandomStringUtils;

public class base_class {
	
	public WebDriver driver;
	public Logger logger;
	
	
	@BeforeClass
	public void setup() {
		
		logger = LogManager.getLogger(this.getClass());
		
		driver = new EdgeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass
	public void teardown() {
		
		driver.quit();
	}
	
	public String randomString() {
		
		return RandomStringUtils.randomAlphabetic(5);
	}
	
	public String randomNumber() {
		
		return RandomStringUtils.randomNumeric(10);
	}
	
	public String randomAlphaNum() {
		return RandomStringUtils.randomAlphanumeric(5);
	}


}

