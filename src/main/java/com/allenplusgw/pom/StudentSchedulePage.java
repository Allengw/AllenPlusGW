package com.allenplusgw.pom;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class StudentSchedulePage {
	
Logger logger=Logger.getLogger("StudentSchedulePage");
	
	
	public WebDriverWait wait;
	
	private String actSSPTitle;

	
	@FindBy(xpath="//a[text()=' Test & Report']")
	private WebElement testReport;
	
	@FindBy(xpath="//h2[text()='Schedule ']")
	private WebElement schedulePage;
	
	@FindBy(xpath="//span[@class='close_popup crossiconss']")
	private List<WebElement> popupClose;
	
	
	public StudentSchedulePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		wait=new WebDriverWait(driver, 30);
		Reporter.log(actSSPTitle);
		
	}
	
	public void ClickOnTestModule(WebDriver driver,ExtentTest loggerE)
	{
		if(popupClose.isEmpty())
		{
			
		}else {
			popupClose.get(0).click();
		}
		//wait.until(ExpectedConditions.invisibilityOf(testReport));
		testReport.click();
		loggerE.log(LogStatus.INFO, "Clicked on Test & Report");
		logger.info("Clicked on Test & Report");

	}
	
	public void verifySchedulePageTitle(WebDriver driver,ExtentTest loggerE)
	{
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expSSPTitle="https://allenqa.thinkexam.com/schedule";
		actSSPTitle=driver.getCurrentUrl();
		 logger.info("Actual Result : "+actSSPTitle);
		 logger.info("Expected Result : "+expSSPTitle);
	    Assert.assertEquals(actSSPTitle, expSSPTitle, "Student Schedule Page is not Verified");
	    loggerE.log(LogStatus.PASS,"Student Schedule Page is Verified : ' "+actSSPTitle+" '");
	    logger.info("Student Schedule Page is Verified : ' "+actSSPTitle+" '");
	    loggerE.log(LogStatus.PASS, "Student Logged In Successfully");
	    logger.info("Student Logged In Successfully");
	}

}
