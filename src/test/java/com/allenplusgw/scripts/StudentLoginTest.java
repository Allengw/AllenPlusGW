package com.allenplusgw.scripts;

import org.testng.annotations.Test;

import com.allenplusgw.genric.WebsitePage;
import com.allenplusgw.pom.StudentLoginPage;
import com.allenplusgw.genric.BaseLib;

public class StudentLoginTest extends BaseLib{


		@Test
		public void studentLogin()
		{
			loggerE=extent.startTest("Student Login Test");
			
			WebsitePage wp=new WebsitePage(driver);
			wp.OpenWebsitePage(driver, "QA", "student");
			
			StudentLoginPage slp=new StudentLoginPage(driver);
			slp.studentLogin(driver, "QA", "MasterPassword", loggerE, 1);
							
		}
		
	}

