package com.allenplus.pom;

import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.allenplus.genric.ExcelLib;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class StudentLoginPage{
	
	Logger logger=Logger.getLogger("StudentLoginPage");
	
	
	public WebDriverWait wait;
	
	@FindBy(xpath="//p[@id='error']")
	private List<WebElement> loginError;
	
	@FindBy(xpath="//img[@src='student/images/login-close-icon.gif']")
	private List<WebElement> emailSkip;
	
	@FindBy(xpath="//a[@onclick='closepop();']")
	private List<WebElement> popUp;
	
	@FindBy(xpath="//select[@id='session_name1']//option")
	private List<WebElement> session;
	
	@FindBy(xpath="//input[@id='email']")
	private WebElement formID;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(xpath="//input[@class='login']")
	private WebElement loginBtn;
	
	private String actSLPTitle;
	
	public StudentLoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		actSLPTitle=driver.getTitle();
		wait=new WebDriverWait(driver, 30);
	}
	
	/*
	 * @description This Login Method includes 2 Condition
	 * @1.  Login using Student Password
	 * @2.  Login using Master Password
	 */
	public void studentLogin(WebDriver driver,String environment,String passType,ExtentTest loggerE,int i)
	{
		try 
		{
			Thread.sleep(2000);
			if(popUp.isEmpty())
			{
				//Do Nothing	
			}
			else
			{
				popUp.get(0).click();
			}
		} catch(InterruptedException e) 
		{
			e.printStackTrace();
			loggerE.log(LogStatus.ERROR, "Interrupted Exception");

		}catch(NoSuchElementException e)
		{
			e.printStackTrace();
			loggerE.log(LogStatus.ERROR, "No Such Element Exception");

		}catch(ElementClickInterceptedException e)
		{
			e.printStackTrace();
			loggerE.log(LogStatus.ERROR, "Element Click Intercepted Exception");

		}
			try
			{
				String sheet="sheet1";
				int row=i;

				String sSession=ExcelLib.readExcelData(sheet, row, 1);
				String sFormID=ExcelLib.readExcelData(sheet, row, 2);
				String sPassword=ExcelLib.readExcelData(sheet, row, 3);
				String mPassword=ExcelLib.readExcelData(sheet, row, 4);
				
				if(environment.equalsIgnoreCase("local"))
				{
					wait.until(ExpectedConditions.visibilityOf(formID));
					formID.sendKeys(sFormID);
					loggerE.log(LogStatus.INFO, "Student FormID : "+sFormID);
					
					wait.until(ExpectedConditions.visibilityOf(password));
					if(passType.equalsIgnoreCase("StudentPassword"))
					{
						password.sendKeys(sPassword);
						loggerE.log(LogStatus.INFO, "Student Password : "+sPassword);
						
					}else if(passType.equalsIgnoreCase("MasterPassword"))
					{
						password.sendKeys(mPassword);
						loggerE.log(LogStatus.INFO, "Master Password : "+mPassword); 
					}
					
					wait.until(ExpectedConditions.visibilityOf(loginBtn));
					loginBtn.click();
				
					if(loginError.isEmpty())
					{
						loggerE.log(LogStatus.PASS, "Student Logged In");
					}
					else
					{
						loggerE.log(LogStatus.FAIL, "Student Login Fail : "+loginError.get(0).getText());
						((WebDriver) driver.switchTo()).get("file:///home/ginger/git/AllenDSAT/log/report.html");
					}
					try 
					{
						if(emailSkip.isEmpty())
						{
							//Do Nothing
						}
						else
						{
							emailSkip.get(0).click();
						}
					}catch(NoSuchElementException e)
					{
						e.printStackTrace();
						loggerE.log(LogStatus.ERROR, "No Such Element Exception");
	
					}		
	
				}else if(environment.equalsIgnoreCase("Live"))
				{
					wait.until(ExpectedConditions.visibilityOfAllElements(session));
					for(WebElement wb:session)
					{
						if(wb.getText().contains(sSession))
						{
							wb.click();
							loggerE.log(LogStatus.INFO,sSession);
							break;
						}
					}
					wait.until(ExpectedConditions.visibilityOf(formID));
					formID.sendKeys(sFormID);
					loggerE.log(LogStatus.INFO, "Student FormID : "+sFormID);
					
					wait.until(ExpectedConditions.visibilityOf(password));
					
					if(passType.equalsIgnoreCase("StudentPassword"))
					{
						password.sendKeys(sPassword);
						loggerE.log(LogStatus.INFO, "Student Password : "+sPassword);
						
					}else if(passType.equalsIgnoreCase("MasterPassword"))
					{
						password.sendKeys(mPassword);
						loggerE.log(LogStatus.INFO, "Master Password : "+mPassword);
					}
					
					wait.until(ExpectedConditions.visibilityOf(loginBtn));
					loginBtn.click();
					
					if(loginError.isEmpty())
					{
						loggerE.log(LogStatus.PASS, "Student Logged In");
					}
					else
					{
						loggerE.log(LogStatus.FAIL, "Student Login Fail : "+loginError.get(0).getText());
						((WebDriver) driver.switchTo()).get("file:///home/ginger/git/AllenDSAT/log/report.html");
					}
					
					try 
					{
						if(emailSkip.isEmpty())
						{
							//Do Nothing
						}
						else
						{
							emailSkip.get(0).click();
						}
					}catch(NoSuchElementException e)
					{
						e.printStackTrace();
						loggerE.log(LogStatus.ERROR, "No Such Element Exception");
					}
				}
				
			}catch(IndexOutOfBoundsException e)
			{
				e.printStackTrace();
				loggerE.log(LogStatus.ERROR, "Index Out Of Bounds Exception");
				
			}catch(NullPointerException e)
			{
				e.printStackTrace();
				loggerE.log(LogStatus.ERROR, "Null Pointer Exception");
				
			}catch(TimeoutException e)
			{
				e.printStackTrace();
				loggerE.log(LogStatus.ERROR, "Timeout Exception");
	
			}catch(ElementClickInterceptedException e)
			{
				e.printStackTrace();
				loggerE.log(LogStatus.ERROR, "Element Click Intercepted Exception Exception");
			}
	}

	public void verifyHomePageTitle(WebDriver driver,ExtentTest loggerE)
	{
		String expSLPTitle="ALLEN Career Institute, Kota Coaching - JEE Advanced (IIT JEE) | JEE Main (AIEEE) | NEET (Pre Medical) | Pre-Nurture Coaching";
		
	    Assert.assertEquals(actSLPTitle, expSLPTitle, "Student Login Page Title is not Verified");
	    loggerE.log(LogStatus.PASS,"Student Login Page Title is not Verified : ' "+actSLPTitle+" '");
	}
}
