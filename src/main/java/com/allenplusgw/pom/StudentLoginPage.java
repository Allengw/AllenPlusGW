package com.allenplusgw.pom;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.allenplusgw.genric.ExcelLib;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class StudentLoginPage{
	
	Logger logger=Logger.getLogger("StudentLoginPage");
	
	
	public WebDriverWait wait;
	
	@FindBy(xpath="//input[@id='emailId']")
	private WebElement formID;
	
	@FindBy(xpath="//input[@id='passwordl']")
	private WebElement password;
	
	@FindBy(xpath="//input[@class='inpt btnin btneffect']")
	private WebElement loginBtn;
	
	@FindBy(xpath="//span[@class='close_popup crossiconss']")
	private List<WebElement> popupClose;
	
	private String actSLPTitle;
	
	public StudentLoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		actSLPTitle=driver.getCurrentUrl();
		wait=new WebDriverWait(driver, 30);
	}
	
	/*
	 * @description This Login Method includes 2 Condition
	 * @1.  Login using Student Password
	 * @2.  Login using Master Password
	 */
	public void studentLogin(WebDriver driver,String environment,String passType,ExtentTest loggerE,int i)
	{
		verifyHomePageTitle(driver, loggerE);
		
			try
			{
				String sheet="sheet1";
				int row=i;

				String sFormID=ExcelLib.readExcelData(sheet, row, 2);
				String sPassword=ExcelLib.readExcelData(sheet, row, 3);
				String mPassword=ExcelLib.readExcelData(sheet, row, 4);
				String InPassword=ExcelLib.readExcelData(sheet, row, 1);
				
				if(environment.equalsIgnoreCase("QA"))
				{
					wait.until(ExpectedConditions.visibilityOf(formID));
					formID.sendKeys(sFormID);
					loggerE.log(LogStatus.INFO, "Student FormID : "+sFormID);
					 logger.info("Student FormID : "+sFormID);

					
					wait.until(ExpectedConditions.visibilityOf(password));
					if(passType.equalsIgnoreCase("StudentPassword"))
					{
						password.sendKeys(sPassword);
						loggerE.log(LogStatus.INFO, "Student Password : "+sPassword);
						 logger.info("Student Password : "+sPassword);

						
					}else if(passType.equalsIgnoreCase("MasterPassword"))
					{
						password.sendKeys(mPassword);
						loggerE.log(LogStatus.INFO, "Master Password : "+mPassword); 
						 logger.info("Master Password : "+mPassword);

					}
					else if(passType.equalsIgnoreCase("InvalidPassword"))
					{
						password.sendKeys(InPassword);
						loggerE.log(LogStatus.INFO, "Invalid Password : "+InPassword); 
						 logger.info("Invalid Password : "+InPassword);

					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					wait.until(ExpectedConditions.visibilityOf(loginBtn));
					loginBtn.click(); 
					
				}
				else if(environment.equalsIgnoreCase("Live"))
				{
					
					wait.until(ExpectedConditions.visibilityOf(formID));
					formID.sendKeys(sFormID);
					loggerE.log(LogStatus.INFO, "Student FormID : "+sFormID);
					 logger.info("Student FormID : "+sFormID);

					
					wait.until(ExpectedConditions.visibilityOf(password));
					
					if(passType.equalsIgnoreCase("StudentPassword"))
					{
						password.sendKeys(sPassword);
						loggerE.log(LogStatus.INFO, "Student Password : "+sPassword);
						 logger.info("Student Password : "+sPassword);

						
					}else if(passType.equalsIgnoreCase("MasterPassword"))
					{
						password.sendKeys(mPassword);
						loggerE.log(LogStatus.INFO, "Master Password : "+mPassword);
						 logger.info("Master Password : "+mPassword);

					}
					else if(passType.equalsIgnoreCase("InvalidPassword"))
					{
						password.sendKeys(mPassword);
						loggerE.log(LogStatus.INFO, "Invalid Password : "+InPassword); 
						 logger.info("Invalid Password : "+InPassword);

					}
					
					wait.until(ExpectedConditions.visibilityOf(loginBtn));
					loginBtn.click();
					
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
		String expSLPTitle="https://allenqa.thinkexam.com/login";
	    Assert.assertEquals(actSLPTitle, expSLPTitle, "Student Login Page is not Verified");
	    loggerE.log(LogStatus.PASS,"Student Login Page is  Verified : ' "+actSLPTitle+" '");
	}
}
