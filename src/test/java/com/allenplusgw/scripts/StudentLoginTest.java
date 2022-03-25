package com.allenplusgw.scripts;

import org.testng.annotations.Test;
import com.allenplusgw.genric.WebsitePage;
import com.allenplusgw.pom.StudentLoginPage;
import com.allenplusgw.pom.StudentSchedulePage;
import com.allenplusgw.genric.BaseLib;


@Test

public class StudentLoginTest extends BaseLib{

		
		public void validstudentLoginMPass()
		{
			loggerE=extent.startTest("Student Login Test");

			WebsitePage wp=new WebsitePage(driver);
			wp.OpenWebsitePage(driver, "QA", "student");
			
			StudentLoginPage slp=new StudentLoginPage(driver);
			slp.studentLogin(driver, "QA", "MasterPassword", loggerE, 1);
										
		}
		
	/*
		public void validstudentLoginSPass()
		{
			
			WebsitePage wp=new WebsitePage(driver);
			wp.OpenWebsitePage(driver, "QA", "student");
			
			StudentLoginPage slp=new StudentLoginPage(driver);
			slp.studentLogin(driver, "QA", "StudentPassword", loggerE, 1);
			
			StudentSchedulePage ssp=new StudentSchedulePage(driver);
			ssp.verifySchedulePageTitle(driver, loggerE);
													
		}
		
		
		
		public void validstudentLoginInPass()
		{
			
			WebsitePage wp=new WebsitePage(driver);
			wp.OpenWebsitePage(driver, "QA", "student");
			
			StudentLoginPage slp=new StudentLoginPage(driver);
			slp.studentLogin(driver, "QA", "InvalidPassword", loggerE, 1);
			
			StudentSchedulePage ssp=new StudentSchedulePage(driver);
			ssp.verifySchedulePageTitle(driver, loggerE);
										
		}
		
		
		public void validstudentLoginNoBatch()
		{
			
			WebsitePage wp=new WebsitePage(driver);
			wp.OpenWebsitePage(driver, "QA", "student");
			
			StudentLoginPage slp=new StudentLoginPage(driver);
			slp.studentLogin(driver, "QA", "StudentPassword", loggerE, 1);
			
			StudentSchedulePage ssp=new StudentSchedulePage(driver);
			ssp.verifySchedulePageTitle(driver, loggerE);
										
		}
		*/
	}

