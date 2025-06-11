package TestCases;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.testng.annotations.*;
import Page_object.home_page;
import Page_object.parent_constructor_page;
import Page_object.registration_page;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

//for log4j2
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//for radomString classes
import org.apache.commons.lang3.RandomStringUtils;

public class comman_methods {
	
	public WebDriver driver;
	public Logger logger;
	public Properties p;
	
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException {
		
		p = new Properties();
		FileReader input = new FileReader("./src//test//resources//property_file");
		p.load(input);
		
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("exec_env").equalsIgnoreCase("remote")) {
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			switch (os.toLowerCase()) {
			
			case "windows": capabilities.setPlatform(Platform.WIN11);break;
			case "mac": capabilities.setPlatform(Platform.MAC);break;
			case "linux": capabilities.setPlatform(Platform.LINUX);break;
			default: System.out.println("no desired os available");return;
			}
			
			switch(br.toLowerCase()) {
			
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
			default: System.out.println("no desired browser available");return;
			}
			
			driver = new RemoteWebDriver(new URL("https://localhost:4444/wd/hub"),capabilities);
		}
		
		if(p.getProperty("exec_env").equalsIgnoreCase("local")) {
		
			switch(br.toLowerCase()) 
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default : System.out.println("no such browser existed"); return;
			}
		}
		
		driver.get(p.getProperty("app_url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
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
	public String captureScreen(String tname) throws IOException {


			String timeStamp = new SimpleDateFormat ("yyyyMMddhhmmss").format(new Date());


			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs (OutputType.FILE);


			String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile=new File(targetFilePath);

			sourceFile.renameTo(targetFile);

			return targetFilePath;
			
	}
	
	


}

