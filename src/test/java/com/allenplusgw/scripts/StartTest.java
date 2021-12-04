package com.allenplusgw.scripts;

import org.testng.annotations.Test;

import com.allenplusgw.genric.BaseLib;
import com.allenplusgw.genric.WebsitePage;
import com.allenplusgw.pom.StudentLoginPage;
import com.allenplusgw.pom.StudentSchedulePage;
import com.allenplusgw.pom.StudentTestPage;

public class StartTest extends BaseLib{

	@Test
	public void TestStartTest()
	{
		loggerE=extent.startTest("Student Start Test");
		
		WebsitePage wp=new WebsitePage(driver);
		wp.OpenWebsitePage(driver, "QA", "student");
		
		StudentLoginPage slp=new StudentLoginPage(driver);
		slp.studentLogin(driver, "QA", "MasterPassword", loggerE, 1);

		StudentSchedulePage ssp1=new StudentSchedulePage(driver);
		ssp1.ClickOnTestModule(driver, loggerE);
		
		StudentTestPage stp1=new StudentTestPage(driver);
		stp1.StartTest(driver, loggerE);
	}
	
	}


