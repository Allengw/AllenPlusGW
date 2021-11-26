package com.allenplus.scripts;

import org.testng.annotations.Test;
import com.allenplus.pom.StudentLoginPage;
import com.allenplus.genric.WebsitePage;
import com.allenplus.genric.BaseLib;

public class StudentLoginTest extends BaseLib{


		@Test
		public void studentLogin()
		{
			loggerE=extent.startTest("Student Login Test");
			
			WebsitePage wp=new WebsitePage(driver);
			wp.OpenWebsitePage(driver, "QA", "student");
			
			StudentLoginPage slp=new StudentLoginPage(driver);
			slp.studentLogin(driver, "QA", "MasterPassword", loggerE, 1);
			
			slp.verifyHomePageTitle(driver, loggerE);
			
				
		}
		
	}

