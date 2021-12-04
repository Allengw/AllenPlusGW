package com.allenplusgw.pom;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class StudentSchedulePage {
	
Logger logger=Logger.getLogger("StudentSchedulePage");
	
	
	public WebDriverWait wait;
	
	private String actSSPTitle;

	
	@FindBy(xpath="//a[text()=' Test & Report']")
	private WebElement testReport;
	
	
	public StudentSchedulePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		actSSPTitle=driver.getPageSource();
		wait=new WebDriverWait(driver, 30);
		
	}
	
	public void ClickOnTestModule(WebDriver driver,ExtentTest loggerE)
	{
		//wait.until(ExpectedConditions.invisibilityOf(testReport));
		testReport.click();
		loggerE.log(LogStatus.INFO, "Clicked on Test & Report");
	}
	
	public void verifySchedulePageTitle(WebDriver driver,ExtentTest loggerE)
	{
		String expSSPTitle="https://allenqa.thinkexam.com/schedule";
		System.out.printf(actSSPTitle);
		
	    Assert.assertEquals(actSSPTitle, expSSPTitle, "Student Schedule Page is not Verified");
	    loggerE.log(LogStatus.PASS,"Student Schedule Page is Verified : ' "+actSSPTitle+" '");
	    loggerE.log(LogStatus.PASS, "Student Logged In Successfully");
	}

}
