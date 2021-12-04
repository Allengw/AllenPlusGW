package com.allenplusgw.pom;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class StudentTestPage {
	
	Logger logger=Logger.getLogger("StudentSchedulePage");

	public WebDriverWait wait;
	
	private String actSTPTitle;
	
	@FindBy(xpath="//div[@class='threeline ng-binding']")
	private List<WebElement> testNAme;
	
	@FindBy(xpath="//div[@class='ng-binding']")
	private List<WebElement> testStatus;
	
	@FindBy(xpath=" //button[text()='Not Now']")
	private WebElement notNowBtn;
	
	@FindBy(xpath="//input[@id='termcondition']")
	private WebElement instChkbx;
	
	@FindBy(xpath="//p[@id='next_ins']")
	private WebElement nextBtn;
	
	@FindBy(xpath="(//a[@class='new_btn_css'])[1]")
	private WebElement saveBtn;
	
	public StudentTestPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		actSTPTitle=driver.getCurrentUrl();
		wait=new WebDriverWait(driver, 30);
	}

	public void StartTest(WebDriver driver, ExtentTest loggerE)
	{
				
		if(testNAme.isEmpty())
		{
			loggerE.log(LogStatus.INFO, "No Active Test is Assigned to Student");
		}
		else
		{
			try {
				//wait.until(ExpectedConditions.invisibilityOfAllElements(testNAme));
				
				for(int i=0;i<testNAme.size();i++)
				{
					//System.out.print(testNAme.get(i).getText()+"----------------\n");
					if(testStatus.get(i).getText().equalsIgnoreCase("Start Test") || testStatus.get(i).getText().equalsIgnoreCase("Resume Test"))
					{
						notNowBtn.click();

						String winHandleBefore = driver.getWindowHandle();

						// Perform the click operation that opens new window
						testStatus.get(i).click();
						loggerE.log(LogStatus.PASS, "Test : [ "+testNAme.get(i).getText()+" ] Started Successfully");
						
						// Switch to new window opened
						for(String winHandle : driver.getWindowHandles()){
						    driver.switchTo().window(winHandle);
						}
						// Perform the actions on new window
						wait.until(ExpectedConditions.visibilityOf(instChkbx));

						instChkbx.click();
						loggerE.log(LogStatus.INFO, "Test Instruction Page Opened");

						Select objSelect =new Select(driver.findElement(By.xpath("//select[@class='sel_lang']")));
						objSelect.selectByVisibleText("English");
						
						nextBtn.click();
						loggerE.log(LogStatus.INFO, "Test Page Opened");
						wait.until(ExpectedConditions.visibilityOf(saveBtn));
						saveBtn.click();
						loggerE.log(LogStatus.INFO, "Test Page Closed");

						// Close the new window, if that window no more required
						driver.close();

						// Switch back to original browser (first window)
						driver.switchTo().window(winHandleBefore);

						// Continue with original browser (first window	

					}
				}
			}catch(ElementNotVisibleException e)
			
			{
				e.printStackTrace();
				loggerE.log(LogStatus.ERROR, "Element Not Visible Exception");
				
			}catch(ElementClickInterceptedException e)
			{
				e.printStackTrace();
				loggerE.log(LogStatus.ERROR, "Element Click Intercepted Exception");
				
			}catch(TimeoutException e)
			{
				e.printStackTrace();
				loggerE.log(LogStatus.ERROR, "Timeout Exception");
			}catch(NoSuchElementException e)
			{
				e.printStackTrace();
				loggerE.log(LogStatus.ERROR, "NoSuchElementException");
			}
		}
	}
}
