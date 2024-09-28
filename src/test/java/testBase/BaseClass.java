package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.testng.annotations.Parameters;

public class BaseClass {
public static WebDriver driver;
public Logger logger;    // we have to make a logger class to access logs that we create 
public Properties p;     //special class to load properties file 

	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})    
	public void setup(String os, String br) throws IOException, URISyntaxException
	{
		//loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");        //(./)-will provide the current project location
		p=new Properties();
		p.load(file);                                        //this command will load the properties file
		
		logger=LogManager.getLogger(this.getClass());    //LogManagger is a predefind class from that getlogger is a method in that we have to assign the classes dynamically so use this keyword as this repersent classes and then get class. this method will get the class name for that particular it will get the logger and store into the variable.
	
		//if execution environment is remote then this will trigger 
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))       //as we are taking environment value from property file but still we are taking the operation system value and browser name from xml file  
		{
			
			DesiredCapabilities capabilities=new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows"))       //as we are passing the os and browser from xml so if you pass windows from xml file same windows will be trigger 
			{
				capabilities.setPlatform(Platform.WIN11);    //if operating system is windows get windows
			}
			
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);    //if operating system is mac get maac 
			}
			else
			{
				System.out.println("no matching os");    //if no match then return an exit 
				return;
			}
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("no matching browser");	return;	}
		
			
		 driver=new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(),capabilities);
		 
				
	    //launch the grid environment and this url is genrated from cmd prompt and first url is in string format so we convert in URL type of object and pass in remoteWebDriver as this is parent class of all driver and pass url as one parameter and capabilities variable as second parameter 
				
		}
		//if execution environment is local then this will trigger
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase())                               //this particulat switch case block will decide which browser we have to launch based on the parameter we pass i.e String os,String br
			{
			case "chrome" :driver=new ChromeDriver(); break;
			case "edge" :new EdgeDriver(); break;
			case "firefox": new FirefoxDriver(); break;
			default :System.out.println("Invalid browser.."); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));      //reading url from properties file
		//driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}

	
	//user defined methods to generate random no,alphabets 
	public String randomString()                       //when you call this method internally it will generate the random string and just return it i.e 5 character random string it will return 5 or any no we have to specify that 
	{
		@SuppressWarnings("deprecation")
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	
	public String randomNumber()                       //when you call this method internally it will generate the random number and just return it i.e 10 
	{
		String generatenumber=RandomStringUtils.randomNumeric(10);     //it will return string which contain 10 numbers
		return generatenumber;
	}
	
	
	public String randomAlphaNumeric()                       //when you call this method internally it will generate the random number and just return it i.e 10 
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		String generatenumber=RandomStringUtils.randomNumeric(10);
		return (generatedstring+"*"+generatenumber);
	}
	
	
	public String captureScreen(String tname)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takesreenshot=(TakesScreenshot) driver;
		File sourceFile= takesreenshot.getScreenshotAs(OutputType.FILE);
		
	    String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
	    File targetFile=new File(targetFilePath);
	    
	    sourceFile.renameTo(targetFile);
	    
	    return targetFilePath;
	    
	    
	    
	}
	
	
	
	
	
}
