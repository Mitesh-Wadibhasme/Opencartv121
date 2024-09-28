package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

//Extent report 5.x...//version

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;         //report name

	public void onStart(ITestContext testContext) {
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);     //this particular  will return date in a string format. To gernate time stamp we are doing this step.

		*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report

		sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report
		sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");     //when we execute this on start method it will capture the textContext and it is nothing but what which test method we executed and that test method details we store into testContext variable.
        extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");   //getcurrentxmltest method  will return the xml file i.e whichever xml file we run that xml file will return
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();   //getIncludeGroups will capture the groups which you specified in include section and display in the report and storing into list collection varable
		if(!includedGroups.isEmpty())                                    //if it is not empty  i.e some value iss there then print them 
		{                                                             
		extent.setSystemInfo("Groups", includedGroups.toString());      //and from this list collection variable with tostring method we are printing the groups 
		}
	}

	public void onTestSuccess(ITestResult result) {            //Whenever ontestsuccess method triggers result will capture all result related information from test methods
	
		test = extent.createTest(result.getTestClass().getName());   //from the result wwe are getting the class i.e which class we executed from that we are getting name of class 
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS,result.getName()+" got successfully executed");
		
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());    //we created a object for base class and without storing it in variable we call capturescreen method and from the result obejct we are getting the name and that name we have pass in capture screenshot method and this will reurn the location of the image and that image we have to attached to the report
			test.addScreenCaptureFromPath(imgPath);     //this will go and attached the ss to the report
			
		} catch (Exception e1) {                         //if the ss is not avaiable stillit will try to attched ss to file in that case we get file not foundd execption so we will put in try catch block
			e1.printStackTrace();                         //capturing the ss
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
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());     //this method will open the extentreport on the browser automatically
		} catch (IOException e) {
			e.printStackTrace();
		}

		//sending reports to email automatically
		/*  try {
		 URL url = new  URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);
		  
		  // Create the email message 
		  ImageHtmlEmail email = new ImageHtmlEmail();
		  email.setDataSourceResolver(new DataSourceUrlResolver(url));
		  email.setHostName("smtp.googlemail.com"); 
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com","password")); 
		  email.setSSLOnConnect(true);
		  email.setFrom("pavanoltraining@gmail.com"); //Sender
		  email.setSubject("Test Results");
		  email.setMsg("Please find Attached Report....");
		  email.addTo("pavankumar.busyqa@gmail.com"); //Receiver 
		  email.attach(url, "extent report", "please check report..."); 
		  email.send(); // send the email 
		  }
		  catch(Exception e) 
		  { 
			  e.printStackTrace(); 
			  }
		 */ 
		 
	}

}

