package utilities;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


import TestCases.comman_methods;

public class ExtentReportManager implements ITestListener {
	

public ExtentSparkReporter sparkReporter;
public ExtentReports extent;
public ExtentTest test;


String repName;


public void onStart(ITestContext testContext) {

	SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.s$");
	Date dt=new Date();
	String currentdatetimestamp=df.format(dt);
	
	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	repName = "Test-Report -"+  timeStamp + ".html";
	sparkReporter = new ExtentSparkReporter("\\reports\\" + repName);


	sparkReporter.config().setDocumentTitle("opencart Automation Report"); 
	sparkReporter.config().setReportName( "opencart Functional Testing"); 
	sparkReporter.config().setTheme(Theme.DARK);


	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Application", "opencart");
	extent.setSystemInfo("Module","Abhin");
	extent.setSystemInfo("Sub Module","Customers");
	extent.setSystemInfo("User Name",System.getProperty("user.name"));
	extent.setSystemInfo("Environemnt","QA");


	String os = testContext.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operating System", os);


	String browser = testContext.getCurrentXmlTest().getParameter("browser");
	extent.setSystemInfo("Browser", browser);


	List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroups.isEmpty())
	{
	extent.setSystemInfo("Groups", includedGroups.toString());
	}
}
	
public void onTestSuccess(ITestResult result) {

		test=extent.createTest(result.getTestClass().getName());
		
		test.assignCategory(result.getMethod().getGroups()); // to display gro
		test.log(Status.PASS,result.getName()+" got successfully executed");
  }
public void onTestFailure(ITestResult result) {
	
	test = extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	
	
	test.log(Status.FAIL,result.getName()+"_got failed");
	test.log(Status.INFO, result.getThrowable().getMessage());
	
	
	try {
	String imgPath = new comman_methods().captureScreen(result.getName());
	test.addScreenCaptureFromPath(imgPath);
	
	} catch (IOException e1) {
	e1.printStackTrace();
	}
  }
public void onTestSkipped(ITestResult result) {
	test = extent.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.SKIP, result.getName()+" got skipped");
	test.log(Status.INFO, result.getThrowable().getMessage());
	}

public void onFinish(ITestContext testContext) {


	extent.flush();


	String pathOfExtentReport = System.getProperty("user.dir")+"I\reports\\"+repName;
	File extentReport = new File(pathOfExtentReport);
	
	
	try {
		Desktop.getDesktop().browse(extentReport.toURI());
	} 
	catch (IOException e) {	
		e.printStackTrace();
	}
	
	try { // this is to if you want to send the report to your emial id
		
	URL url = new URL("file:///"+System.getProperty("user.dir")+"I\reports\\"+repName);


	// Create the email message
	ImageHtmlEmail email= new ImageHtmlEmail();
	email.setDataSourceResolver(new DataSourceUrlResolver(url));
	email.setHostName("smtp.googlemail.com");
	email.setSmtpPort(465);
	email.setAuthenticator(new DefaultAuthenticator("adityaadhi1431@gmail.com","password"));
	email.setSSLOnConnect(true);
	email.setFrom("adityaadhi1431@gmail.com");
	email.setSubject("Test Results");
	email.setMsg("Please find Attached Report....");
	email.addTo("pavankumar.busyqa@gmail.com"); //Receiver
	email.attach(url, "extent report", "please check report...");
	email.send(); // send the email
	}
	catch(Exception e) {
		e.printStackTrace();
     }
  }

}

