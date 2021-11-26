package com.allenplus.genric;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import com.allenplus.genric.ScreenShotLib;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseLib {
	
	/**
	 * @description This Method is used To Launch Browsers
	 */
	public ExtentReports extent;
	public ExtentTest loggerE;
	public WebDriver driver;
	public WaitStatementLib wLib;
	Logger logger=Logger.getLogger("BaseLib");
	
	
	@BeforeMethod
	@Parameters(value="browser")
	public void preCondition(String browser)
	{
		
		 PropertyConfigurator.configure("log4j.properties");
		 extent=new ExtentReports("C:/Users/ginger/git/AllenDSAT/log/report.html");
		 loggerE=extent.startTest(":::::: Launch Browser ::::::");
		 
		 
		 if(browser.equalsIgnoreCase("firefox"))
		 { 
			 //System.setProperty("webdriver.gecko.driver","C:/Users/ginger/git/AllenDSAT/driverfiles/geckodriver.exe");
			 WebDriverManager.firefoxdriver().setup();
			 driver=new FirefoxDriver();
			 System.out.println("\n");
			 logger.info("FireFox is Launched");
			 Reporter.log("FireFox is Launched");
			 loggerE.log(LogStatus.PASS,"FireFox is Launched");
		 }
		 else 
		 if(browser.equalsIgnoreCase("chrome"))
		 { 
			//System.setProperty("webdriver.chrome.driver","C:/Users/ginger/git/AllenDSAT/driverfiles/chromedriver.exe");
			 WebDriverManager.chromedriver().setup();
			 driver=new ChromeDriver();
			 System.out.println("\n");
			 logger.info("Chrome Browser is Launched");
			 Reporter.log("Chrome Browser is Launched");
			 loggerE.log(LogStatus.PASS,"Chrome Browser is Launched");
		 }
		/* else
		 if(browser.equalsIgnoreCase("ie"))
		 {
			 System.setProperty("webdriver.ie.driver","./driverfiles/InternetExplorerdriver");
			 driver=new InternetExplorerDriver();
			 Reporter.log("Internet Explorer is Launched",true);
		 }*/
		 else
		 {
			 driver=new FirefoxDriver();
			 Reporter.log("FireFox Browser is Launched",true);
		 }
	wLib=new WaitStatementLib();
	wLib.implicitWaitForSecond(driver, 1);
	}
	
	/**
	 * @description This method is used close Browser
	 */
	@AfterMethod
	public void postcondition(ITestResult result) {
		
		loggerE=extent.startTest(":::::: End Test ::::::");
		if(result.isSuccess()) {
			
		}
		else
		{
			String fileName=result.getMethod().getMethodName();
			ScreenShotLib sLib=new ScreenShotLib();
			sLib.getScreenShot(driver,fileName);
			logger.info("ScreenShot has been Taken");
			loggerE.log(LogStatus.INFO,"ScreenShot has been Taken");
			Reporter.log("\n=============================\n  ScreenShot has been Taken\n=============================\n",true);
			String screeshot_path=sLib.getScreenShot(driver, result.getName());
			String image=loggerE.addScreenCapture(screeshot_path);
			loggerE.log(LogStatus.INFO, "Test Failure",image);
			loggerE.log(LogStatus.FAIL, "Test Failure");
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		loggerE.log(LogStatus.INFO, "Test Ended");
		extent.endTest(loggerE);
		extent.flush();
		//extent.close();
		driver.get("file:C:/Users/ginger/git/AllenDSAT/log/report.html");
		//driver.close();
		//logger.info("Browser is closed");
		//Reporter.log("Browser is closed");
	}
}
